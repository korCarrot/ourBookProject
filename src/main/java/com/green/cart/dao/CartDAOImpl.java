package com.green.cart.dao;

import com.green.cart.vo.CartVO;
import com.green.goods.vo.GoodsVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("cartDAO")
public class CartDAOImpl  implements  CartDAO{
    @Autowired
    private SqlSession sqlSession;

    //동일 상품 유무 조회
    public boolean selectCountInCart(CartVO cartVO) throws DataAccessException {
        String result =sqlSession.selectOne("mapper.cart.selectCountInCart",cartVO);
        return Boolean.parseBoolean(result);
    }

    //장바구니 추가 (삭제 유무, 추가한 시간대, 수량은 기본 값 있음)
    public int insertGoodsInCart(CartVO cartVO) throws DataAccessException{
        int cart_id=selectMaxCartId();
        System.out.println("cart_id : " + cart_id);
        cartVO.setCart_id(cart_id); //장바구니 아이디 설정.
        System.out.println("cartVO getCart_id : " + cartVO.getCart_id());
        System.out.println("cartVO getGoods_id : " + cartVO.getGoods_id());
        System.out.println("cartVO getMember_id : " + cartVO.getMember_id());
        return sqlSession.insert("mapper.cart.insertGoodsInCart",cartVO);
//        System.out.println("insert완료");
    }

//장바구니 번호 만들기
    private int selectMaxCartId() throws DataAccessException{
        int cart_id =sqlSession.selectOne("mapper.cart.selectMaxCartId");
        System.out.println("cart_id : " + cart_id);
        return cart_id;
    }

    //장바구니 정보를 반환
    public List<CartVO> selectCartList(CartVO cartVO) throws DataAccessException {
        List<CartVO> cartList =sqlSession.selectList("mapper.cart.selectCartList",cartVO);
        return cartList;
    }

    //상품 정보를 반환
    public List<GoodsVO> selectGoodsList(List<CartVO> cartList) throws DataAccessException {
        List<GoodsVO> myGoodsList = sqlSession.selectList("mapper.cart.selectGoodsList",cartList);
        return myGoodsList;
    }

    //장바구니 수정(수량)
    public void updateCartGoodsQty(CartVO cartVO) throws DataAccessException{
        sqlSession.insert("mapper.cart.updateCartGoodsQty",cartVO);
    }

    //장바구니 삭제
    public void deleteCartGoods(int cart_id) throws DataAccessException{
        sqlSession.delete("mapper.cart.deleteCartGoods",cart_id);
    }


}
