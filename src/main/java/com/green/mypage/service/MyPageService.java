package com.green.mypage.service;

import com.green.member.vo.MemberVO;
import com.green.order.vo.OrderVO;

import java.util.List;
import java.util.Map;

public interface MyPageService {
    public List<OrderVO> listMyOrderGoods(String member_id) throws Exception;
    public List findMyOrderInfo(String order_id) throws Exception;
    public List<OrderVO> listMyOrderHistory(Map dateMap) throws Exception;
    public MemberVO modifyMyInfo(Map memberMap) throws Exception;
    public void cancelOrder(String order_id) throws Exception;
    public MemberVO myDetailInfo(String member_id) throws Exception;
}
