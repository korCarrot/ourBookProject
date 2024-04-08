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

    //상품 상세 조회
    @Override   //작동안하면 sqlSession앞에 (GoodsVO)넣기. / addGoodsInQuick을 위해 GoodsVO를 반환.
    public GoodsVO selectGoodsDetail(String goods_id) throws DataAccessException {
        GoodsVO goodsVO=sqlSession.selectOne("mapper.goods.selectGoodsDetail", goods_id);
        return goodsVO;
    }

    //상품 상세 조회(이미지 정보)
    @Override   //작동안하면 sqlSession앞에 (ArrayList)
    public List<ImageFileVO> selectGoodsDetailImage(String goods_id) throws DataAccessException{
        List<ImageFileVO> imageList=sqlSession.selectList("mapper.goods.selectGoodsDetailImage",goods_id);
        return imageList;
    }

    //검색창에서 가져온 키워드가 포함된 상품 제목 조회
    @Override
    public List<String> selectKeywordSearch(String keyword) throws DataAccessException {
        List<String> list=sqlSession.selectList("mapper.goods.selectKeywordSearch",keyword);
        return list;
    }

    //검색창에서 가져온 단어가 포함된 상품들 조회
    @Override
    public List<GoodsVO> selectGoodsBySearchWord(String searchWord) throws DataAccessException{
        List list=sqlSession.selectList("mapper.goods.selectGoodsBySearchWord",searchWord);
        return list;
    }

}
