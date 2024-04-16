package com.green.order.controller;

import com.green.common.base.BaseController;
import com.green.goods.vo.GoodsVO;
import com.green.member.vo.MemberVO;
import com.green.order.service.OrderService;
import com.green.order.vo.OrderVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
//              바로 주문처리
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
        session.setAttribute("orderer", memberInfo);        //세션에 주문자 정보 저장 - 주석안하면 화면이 안나옴..

        String viewName=getViewName(request);
        System.out.println("viewName : " + viewName);
        ModelAndView mav = new ModelAndView(viewName);

        return mav;
    }


    //최종 결제
    @Override                       //주문창에서 입력한 상품 수령자 정보와 배송지 정보를 Map에 바로 저장
    @RequestMapping(value="/payToOrderGoods.do" ,method = RequestMethod.POST)
    public ModelAndView payToOrderGoods(Map<String, String> receiverMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
    String viewName = getViewName(request);
    System.out.println("receiverMap: " +receiverMap);   //*오류 : receiverMap이 현재 비어있다.
        ModelAndView mav = new ModelAndView(viewName);
        HttpSession session=request.getSession();

        MemberVO memberVO=(MemberVO)session.getAttribute("orderer");
        String member_id=memberVO.getMember_id();       //주문자 아이디
        String orderer_name=memberVO.getMember_name();  //주문자 이름
        String orderer_hp = memberVO.getHp1()+"-"+memberVO.getHp2()+"-"+memberVO.getHp3();  //핸드폰 번호
        List<OrderVO> myOrderList=(List<OrderVO>)session.getAttribute("myOrderList");   //주문 정보

        //주문창에서 입력한 수령자 정보, 배송지 정보를 주문 상품 정보 목록과 합침
        for(int i=0; i<myOrderList.size();i++){
            OrderVO orderVO=(OrderVO)myOrderList.get(i);    //주문 정보 / 밑에는 주문자 정보 세팅
            orderVO.setMember_id(member_id);
            orderVO.setOrderer_name(orderer_name);
            orderVO.setOrderer_hp(orderer_hp);

            orderVO.setReceiver_name(receiverMap.get("receiver_name"));
            orderVO.setReceiver_hp1(receiverMap.get("receiver_hp1"));
            orderVO.setReceiver_hp2(receiverMap.get("receiver_hp2"));
            orderVO.setReceiver_hp3(receiverMap.get("receiver_hp3"));
            orderVO.setReceiver_tel1(receiverMap.get("receiver_tel1"));
            orderVO.setReceiver_tel2(receiverMap.get("receiver_tel2"));
            orderVO.setReceiver_tel3(receiverMap.get("receiver_tel3"));

            orderVO.setDelivery_address(receiverMap.get("delivery_address"));
            orderVO.setDelivery_message(receiverMap.get("delivery_message"));
            orderVO.setDelivery_method(receiverMap.get("delivery_method"));
            orderVO.setGift_wrapping(receiverMap.get("gift_wrapping"));
            orderVO.setPay_method(receiverMap.get("pay_method"));
            orderVO.setCard_com_name(receiverMap.get("card_com_name"));
            orderVO.setCard_pay_month(receiverMap.get("card_pay_month"));
            orderVO.setPay_orderer_hp_num(receiverMap.get("pay_orderer_hp_num"));

            myOrderList.set(i, orderVO); //각 orderVO에 주문자 정보를 세팅한 후 다시 myOrderList에 저장한다.
        }//end for
        orderService.addNewOrder(myOrderList);
        mav.addObject("myOrderInfo", receiverMap);  //상품 수령자와 배송지 정보
        mav.addObject("myOrderList", myOrderList);  //주문 상품 정보 목록
        return mav;
    }

    //장바구니 상품 주문하기
    @RequestMapping(value="/orderAllCartGoods.do" ,method = RequestMethod.POST) //선택한 상품 수량을 배열로 받음
    public ModelAndView orderAllCartGoods( @RequestParam("cart_goods_qty")  String[] cart_goods_qty,
                                           HttpServletRequest request, HttpServletResponse response)  throws Exception{
        String viewName=getViewName(request);
        ModelAndView mav = new ModelAndView(viewName);
        HttpSession session=request.getSession();

//        세션에 미리 저장한 장바구니 상품 목록
        Map cartMap=(Map)session.getAttribute("cartMap");
        List myOrderList=new ArrayList<OrderVO>();
        List<GoodsVO> myGoodsList=(List<GoodsVO>)cartMap.get("myGoodsList");
        MemberVO memberVO=(MemberVO)session.getAttribute("memberInfo");

        for(int i=0; i<cart_goods_qty.length;i++){  //장바구니 상품 개수만큼 반복
            String[] cart_goods=cart_goods_qty[i].split(":");   //문자열로 결합되어 전송된 상품 번호와 주문 수량을 분리

            for(int j = 0; j< myGoodsList.size();j++) {
                GoodsVO goodsVO = myGoodsList.get(j);
                int goods_id = goodsVO.getGoods_id();

                if(goods_id==Integer.parseInt(cart_goods[0])) { //전송된 상품번호와 GoodsVO의 상품 번호가 같으면 주문하는 상품 -> OrderVO객체를 생성한 후 상품 정보를 OrderVO에 설정
                    OrderVO _orderVO=new OrderVO();
                    String goods_title=goodsVO.getGoods_title();
                    int goods_sales_price=goodsVO.getGoods_sales_price();
                    String goods_fileName=goodsVO.getFileName();
                    _orderVO.setGoods_id(goods_id);
                    _orderVO.setGoods_title(goods_title);
                    _orderVO.setGoods_sales_price(goods_sales_price);
                    _orderVO.setGoods_fileName(goods_fileName);
                    _orderVO.setOrder_goods_qty(Integer.parseInt(cart_goods[1]));
                    myOrderList.add(_orderVO);
                    break;
                }
            }
        }
        session.setAttribute("myOrderList", myOrderList);   //장바구니 목록에서 주문하기 위해 선택한 상품만 myOrderList에 저장한 후 세션에 바인딩
        session.setAttribute("orderer", memberVO);
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