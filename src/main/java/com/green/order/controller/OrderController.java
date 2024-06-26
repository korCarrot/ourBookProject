package com.green.order.controller;

import com.green.order.vo.OrderVO;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface OrderController {
    public ModelAndView orderEachGoods(@ModelAttribute("orderVO") OrderVO _orderVO, HttpServletRequest request, HttpServletResponse response)  throws Exception;
    public ModelAndView orderAllCartGoods(@RequestParam String[] cart_goods_qty, HttpServletRequest request, HttpServletResponse response)  throws Exception;
    public ModelAndView payToOrderGoods(@RequestParam Map<String, String> receiverMap , HttpServletRequest request, HttpServletResponse response)  throws Exception;
}
