package com.green.order.service;

import com.green.order.dao.OrderDAO;
import com.green.order.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderDAO orderDAO;

    public void addNewOrder(List<OrderVO> myOrderList) throws Exception{
        //주문 상품 목록을 테이블에 추가
        orderDAO.insertNewOrder(myOrderList);
        //장바구니에서 주문한 경우 해당 상품을 장바구니에서 삭제
        orderDAO.removeGoodsFromCart(myOrderList);
    }

}
