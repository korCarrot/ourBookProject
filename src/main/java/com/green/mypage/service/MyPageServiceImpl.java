package com.green.mypage.service;

import com.green.member.vo.MemberVO;
import com.green.mypage.dao.MyPageDAO;
import com.green.order.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("myPageService")
    @Transactional(propagation= Propagation.REQUIRED)
    public class MyPageServiceImpl  implements MyPageService {
        @Autowired
        private MyPageDAO myPageDAO;

        public List<OrderVO> listMyOrderGoods(String member_id) throws Exception {
            return myPageDAO.selectMyOrderGoodsList(member_id);
        }

        public List findMyOrderInfo(String order_id) throws Exception{
            return myPageDAO.selectMyOrderInfo(order_id);
        }

        public List<OrderVO> listMyOrderHistory(Map dateMap) throws Exception{
            return myPageDAO.selectMyOrderHistoryList(dateMap);
        }

//        회원정보가져오기 (id와 수정된 회원정보가 담긴 Map)
        public MemberVO  modifyMyInfo(Map memberMap) throws Exception{
            String member_id=(String)memberMap.get("member_id");
            myPageDAO.updateMyInfo(memberMap);  //회원정보 업데이트
            return myPageDAO.selectMyDetailInfo(member_id); //업데이트 된 회원정보 가져오기
        }

        public void cancelOrder(String order_id) throws Exception{
            myPageDAO.updateMyOrderCancel(order_id);
        }
        public MemberVO myDetailInfo(String member_id) throws Exception{
            return myPageDAO.selectMyDetailInfo(member_id);
        }

    }

