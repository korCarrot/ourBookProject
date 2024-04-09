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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller("cartController")
@RequestMapping(value="/cart")
public class CartControllerImpl extends BaseController implements CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private CartVO cartVO;
    @Autowired
    private MemberVO memberVO;

    @ResponseBody
    @RequestMapping(value = "/addGoodsInCart.do", method = RequestMethod.POST, produces = "application/text; charset=utf8")
    public String addGoodsInCart(@RequestParam("goods_id") int goods_id,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        memberVO = (MemberVO) session.getAttribute("memberInfo");  //로그인 정보 가져오기
        String member_id = memberVO.getMember_id();   //사용자 id

        cartVO.setGoods_id(goods_id);
        cartVO.setMember_id(member_id);

        //카트 등록전에 이미 등록된 제품인지 판별한다.
        boolean isAreadyExisted = cartService.findCartGoods(cartVO);
        System.out.println("isAreadyExisted:" + isAreadyExisted);
        if (isAreadyExisted == true) {
            return "already_existed";
        } else {
//           기존에 등록되지 않은 상품이면 장바구니에 추가
            cartService.addGoodsInCart(cartVO);
            return "add_success";
        }
    }
}
