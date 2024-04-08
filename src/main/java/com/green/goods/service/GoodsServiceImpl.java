package com.green.goods.service;

import com.green.goods.dao.GoodsDAO;
import com.green.goods.vo.GoodsVO;
import com.green.goods.vo.ImageFileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("goodsService")
@Transactional(propagation=Propagation.REQUIRED)
public class GoodsServiceImpl implements GoodsService{
    @Autowired
    private GoodsDAO goodsDAO;

    public Map<String,List<GoodsVO>> listGoods() throws Exception {
        Map<String,List<GoodsVO>> goodsMap=new HashMap<String,List<GoodsVO>>();

        List<GoodsVO> goodsList=goodsDAO.selectGoodsList("bestseller");
        goodsMap.put("bestseller",goodsList);

        goodsList=goodsDAO.selectGoodsList("newbook");
        goodsMap.put("newbook",goodsList);

        goodsList=goodsDAO.selectGoodsList("steadyseller");
        goodsMap.put("steadyseller",goodsList);

        return goodsMap;
    }

//상품 상세 조회
    public Map goodsDetail(String goods_id) throws Exception {
        Map goodsMap=new HashMap();
        GoodsVO goodsVO = goodsDAO.selectGoodsDetail(goods_id);
        goodsMap.put("goodsVO", goodsVO);

        List<ImageFileVO> imageList =goodsDAO.selectGoodsDetailImage(goods_id);
        goodsMap.put("imageList", imageList);

        return goodsMap;
    }

    //검색창에서 가져온 키워드가 포함된 상품 제목 조회
    public List<String> keywordSearch(String keyword) throws Exception{
        List<String> list=goodsDAO.selectKeywordSearch(keyword);
        return list;
    }


    //검색창에서 가져온 단어가 포함된 상품들 조회
    public List<GoodsVO> searchGoods(String searchWord) throws Exception{
        List goodsList=goodsDAO.selectGoodsBySearchWord(searchWord);
        return goodsList;
    }

}
