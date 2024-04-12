package com.green.order.controller;

import com.green.common.base.BaseController;
import com.green.member.vo.MemberVO;
import com.green.order.service.OrderService;
import com.green.order.vo.OrderVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log4j2
@Controller("orderController")
@RequestMapping(value="/order")
public class OrderControllerImpl extends BaseController implements OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderVO orderVO;

    //  상품 상세 페이지나 장바구니에서 한 개의 상품을 주문   //orderVO로 넘어온 값 : 상품id, 수량, 제목, 가격, 이미지 이름 (자바스크립트에서는 i_fileName식으로 들어오고 OrderVO는 goods_fileName으로 되어있는데 괜찮나? 그리고 GOODS테이블에 있던 정보들을 ORDER_VO로 받넹)
    @RequestMapping(value = "/orderEachGoods.do", method = RequestMethod.POST)
    public ModelAndView orderEachGoods(@ModelAttribute("orderVO") OrderVO _orderVO,
                                       HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println("orderEachGoods 에 들어옴");

        HttpSession session = request.getSession();
        Boolean isLogOn = (Boolean) session.getAttribute("isLogOn");   //로그인 상태
        String action = (String) session.getAttribute("action");       //주문하기 전 보고 있던 상품 페이지
        System.out.println("isLogOn : " + isLogOn);
        System.out.println("action : " + action);   //상품상세조회에서 구매하기 누르면 null로 나옴.

        //로그인 여부 체크
        //이전에 로그인 상태인 경우는 주문과정 진행
        //로그아웃 상태인 경우 로그인 화면으로 이동 (지금 보고 있는 상품 정보와 페이지 세션에 저장 후 이동임)
        if (isLogOn == null || isLogOn == false) {
            session.setAttribute("orderInfo", _orderVO);
            session.setAttribute("action", "/order/orderEachGoods.do");
            return new ModelAndView("redirect:/member/loginForm.do");
        }else{
            //세션에서 주문 정보를 가져와 바로 주문창으로 이동(로그인 되어있는 상태)
            //상품을 주문하는 자바스크립트 작동시 action에 /order/orderEachGoods.do가 있음.
            if(action!=null && action.equals("/order/orderEachGoods.do")){
                orderVO=(OrderVO)session.getAttribute("orderInfo");
                System.out.println("orderVO : " + orderVO);
                session.removeAttribute("action");
            }else {
//              바로 주문처리     (여기는 이해가 잘 안됨. 직접 실행해보고 확인하자.)
                orderVO=_orderVO;
                System.out.println("orderVO: "+orderVO);    //orderVO들어오는거 확인(상품상세조회 페이지에서)
            }
        }

        List myOrderList=new ArrayList<OrderVO>();
        myOrderList.add(orderVO);   //리스트에 주문 정보 저장
        System.out.println("myOrderList : " + myOrderList);
        MemberVO memberInfo=(MemberVO)session.getAttribute("memberInfo");
        System.out.println("memberInfo : "+memberInfo);                                 //세션에 저장된 로그인 정보도 존재.
        session.setAttribute("myOrderList", myOrderList);   //세션에 주문 정보 리스트
        session.setAttribute("orderer", memberInfo);        //세션에 주문자 정보 저장

        String viewName=getViewName(request);
        System.out.println("viewName : " + viewName);
        ModelAndView mav = new ModelAndView(viewName);

        return mav;
    }

    @Override
    public ModelAndView payToOrderGoods(Map<String, String> orderMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
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