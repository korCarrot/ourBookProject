package com.green.cart.service;

import com.green.cart.dao.CartDAO;
import com.green.cart.vo.CartVO;
import com.green.goods.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("cartService")
@Transactional(propagation= Propagation.REQUIRED)   //@Transactional(propagation = Propagation.REQUIRED)을 사용하면 해당 메서드는 항상 트랜잭션 내에서 실행되며, 호출 스택에 이미 트랜잭션이 있는 경우에는 그 트랜잭션을 사용하고, 트랜잭션이 없는 경우에는 새로운 트랜잭션을 시작
public class CartServiceImpl  implements CartService {
    @Autowired
    private CartDAO cartDAO;

    //동일 상품 유무 조회
    public boolean findCartGoods(CartVO cartVO) throws Exception {
        return cartDAO.selectCountInCart(cartVO);
    }

    //장바구니 추가
    public int addGoodsInCart(CartVO cartVO) throws Exception {
        return cartDAO.insertGoodsInCart(cartVO);
    }

    //장바구니 정보와 상품 정보를 반환
    public Map<String, List> myCartList(CartVO cartVO) throws Exception {
        Map<String, List> cartMap = new HashMap<String, List>();

        //장바구니 모든 정보
        List<CartVO> myCartList=cartDAO.selectCartList(cartVO);
        if(myCartList.size()==0){ //카트에 저장된 상품이없는 경우
            return null;
        }
        //상품 상세 정보                               (장바구니 모든 정보)-상품 id만 필요.
        List<GoodsVO> myGoodsList=cartDAO.selectGoodsList(myCartList);

        cartMap.put("myCartList", myCartList);  //장바구니 정보
        cartMap.put("myGoodsList",myGoodsList); //상품 정보

        return cartMap;
    }
}
