package com.green.goods.dao;

import com.green.goods.vo.GoodsVO;
import com.green.goods.vo.ImageFileVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("goodsDAO")
public class GoodsDAOImpl  implements GoodsDAO{
    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<GoodsVO> selectGoodsList(String goodsStatus) throws DataAccessException {
        List<GoodsVO> goodsList=(ArrayList)sqlSession.selectList("mapper.goods.selectGoodsList",goodsStatus);
        return goodsList;
    }

    @Override   //작동안하면 sqlSession앞에 (GoodsVO)넣기. / addGoodsInQuick을 위해 GoodsVO를 반환.
    public GoodsVO selectGoodsDetail(String goods_id) throws DataAccessException {
        GoodsVO goodsVO=sqlSession.selectOne("mapper.goods.selectGoodsDetail", goods_id);
        return goodsVO;
    }

    @Override   //작동안하면 sqlSession앞에 (ArrayList)
    public List<ImageFileVO> selectGoodsDetailImage(String goods_id) throws DataAccessException{
        List<ImageFileVO> imageList=sqlSession.selectList("mapper.goods.selectGoodsDetailImage",goods_id);
        return imageList;
    }

}
