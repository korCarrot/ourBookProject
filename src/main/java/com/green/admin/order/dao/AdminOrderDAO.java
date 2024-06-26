package com.green.admin.order.dao;

import com.green.member.vo.MemberVO;
import com.green.order.vo.OrderVO;
import org.springframework.dao.DataAccessException;

import java.util.ArrayList;
import java.util.Map;

public interface AdminOrderDAO {
	public ArrayList<OrderVO> selectNewOrderList(Map condMap) throws DataAccessException;
	public void  updateDeliveryState(Map deliveryMap) throws DataAccessException;
	public ArrayList<OrderVO> selectOrderDetail(int order_id) throws DataAccessException;
	public MemberVO selectOrderer(String member_id) throws DataAccessException;
}
