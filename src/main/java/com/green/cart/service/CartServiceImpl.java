package com.green.cart.service;

import com.green.cart.dao.CartDAO;
import com.green.cart.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("cartService")
@Transactional(propagation= Propagation.REQUIRED)   //@Transactional(propagation = Propagation.REQUIRED)을 사용하면 해당 메서드는 항상 트랜잭션 내에서 실행되며, 호출 스택에 이미 트랜잭션이 있는 경우에는 그 트랜잭션을 사용하고, 트랜잭션이 없는 경우에는 새로운 트랜잭션을 시작
public class CartServiceImpl  implements CartService{
    @Autowired
    private CartDAO cartDAO;

    //동일 상품 유무 조회
    public boolean findCartGoods(CartVO cartVO) throws Exception {
        return cartDAO.selectCountInCart(cartVO);
    }

    //장바구니 추가
    public void addGoodsInCart(CartVO cartVO) throws Exception{
        cartDAO.insertGoodsInCart(cartVO);
    }
}
