package com.green.mypage.dao;

import com.green.member.vo.MemberVO;
import com.green.order.vo.OrderVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("myPageDAO")
public class MyPageDAOImpl implements MyPageDAO {
    @Autowired
    private SqlSession sqlSession;

//    주문상품리스트 조회
    public List<OrderVO> selectMyOrderGoodsList(String member_id) throws DataAccessException {
        List<OrderVO> orderGoodsList = sqlSession.selectList("mapper.mypage.selectMyOrderGoodsList", member_id);
        return orderGoodsList;
    }

    //    주문 상품 상세 조회
    public List selectMyOrderInfo(String order_id) throws DataAccessException{
        List myOrderList=(List)sqlSession.selectList("mapper.mypage.selectMyOrderInfo",order_id);
        return myOrderList;
    }

    public List<OrderVO> selectMyOrderHistoryList(Map dateMap) throws DataAccessException{
        List<OrderVO> myOrderHistList=(List)sqlSession.selectList("mapper.mypage.selectMyOrderHistoryList",dateMap);
        return myOrderHistList;
    }

    public void updateMyInfo(Map memberMap) throws DataAccessException{
        sqlSession.update("mapper.mypage.updateMyInfo",memberMap);
    }

    public MemberVO selectMyDetailInfo(String member_id) throws DataAccessException{
        MemberVO memberVO=(MemberVO)sqlSession.selectOne("mapper.mypage.selectMyDetailInfo",member_id);
        return memberVO;

    }

    public void updateMyOrderCancel(String order_id) throws DataAccessException{
        sqlSession.update("mapper.mypage.updateMyOrderCancel",order_id);
    }
}
