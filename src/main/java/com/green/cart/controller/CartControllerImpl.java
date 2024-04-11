package com.green.cart.controller;

import com.green.cart.service.CartService;
import com.green.cart.vo.CartVO;
import com.green.common.base.BaseController;
import com.green.member.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller("cartController")
@RequestMapping(value="/cart")
public class CartControllerImpl extends BaseController implements CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private CartVO cartVO;
    @Autowired
    private MemberVO memberVO;

    //장바구니 추가
    @ResponseBody       //상품상세조회 페이지에서 ajax로 goods_id 받기 + 세션에서 member_id 받기.
    @RequestMapping(value = "/addGoodsInCart.do", method = RequestMethod.POST, produces = "application/text; charset=utf8")
    public String addGoodsInCart(@RequestParam("goods_id") int goods_id,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("addGoodsInCart.do입니다.");
        HttpSession session = request.getSession();
        memberVO = (MemberVO) session.getAttribute("memberInfo");  //로그인 정보 가져오기
        String member_id = memberVO.getMember_id();   //사용자 id

        System.out.println("상품 id, 사용자 id : "+ goods_id + member_id ) ;

        cartVO.setGoods_id(goods_id);
        cartVO.setMember_id(member_id);

        //카트 등록전에 이미 등록된 제품인지 판별한다.
        boolean isAreadyExisted = cartService.findCartGoods(cartVO);
        System.out.println("isAreadyExisted:" + isAreadyExisted);
        if (isAreadyExisted == true) {
            System.out.println("이미 장바구니에 존재합니다. already_existed");
            return "already_existed";       //ajax로 반환(data)
        } else {
//           기존에 등록되지 않은 상품이면 장바구니에 추가
            int result =  cartService.addGoodsInCart(cartVO);
            System.out.println("장바구니 추가 한 값 : " + result);
            System.out.println("addGoodsInCart 실행! add_success");
            return "add_success";           //ajax로 반환(data)
        }
    }

    @RequestMapping(value="/myCartList.do" ,method = RequestMethod.GET)
    public ModelAndView myCartMain(HttpServletRequest request, HttpServletResponse response)  throws Exception {
    String viewName = getViewName(request);
        ModelAndView mav = new ModelAndView(viewName);
        HttpSession session=request.getSession();

        MemberVO memberVO=(MemberVO)session.getAttribute("memberInfo");
        String member_id=memberVO.getMember_id();
        cartVO.setMember_id(member_id);

        //장바구니 페이지에 표시할 상품 정보를 조회   [Map<String , List> 1. 장바구니 정보  2. 상품 정보]
        Map<String , List> cartMap=cartService.myCartList(cartVO);
        //장바구니 목록 화면에서 상품 주문 시 사용하기 위해서 장바구니 목록을 세션에 저장한다.
        session.setAttribute("cartMap", cartMap);
        return mav;
    }



    //getViewName
    private String getViewName(HttpServletRequest request) throws Exception {
        String contextPath = request.getContextPath();
        String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
        if (uri == null || uri.trim().equals("")) {
            uri = request.getRequestURI();
        }

        int begin = 0;
        if (!((contextPath == null) || ("".equals(contextPath)))) {
            begin = contextPath.length();
        }

        int end;
        if (uri.indexOf(";") != -1) {
            end = uri.indexOf(";");
        } else if (uri.indexOf("?") != -1) {
            end = uri.indexOf("?");
        } else {
            end = uri.length();
        }

        String fileName = uri.substring(begin, end);
        if (fileName.indexOf(".") != -1) {
            fileName = fileName.substring(0, fileName.lastIndexOf("."));
        }
        if (fileName.lastIndexOf("/") != -1) {
            fileName = fileName.substring(fileName.lastIndexOf("/",1), fileName.length());
        }
        return fileName;
    }
}
