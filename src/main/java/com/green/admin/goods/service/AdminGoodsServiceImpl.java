package com.green.admin.goods.service;

import com.green.admin.goods.dao.AdminGoodsDAO;
import com.green.goods.vo.GoodsVO;
import com.green.goods.vo.ImageFileVO;
import com.green.order.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("adminGoodsService")
@Transactional(propagation=Propagation.REQUIRED)
public class AdminGoodsServiceImpl implements AdminGoodsService {
	@Autowired
	private AdminGoodsDAO adminGoodsDAO;
	
	@Override
	public int addNewGoods(Map newGoodsMap) throws Exception{
		int goods_id = adminGoodsDAO.insertNewGoods(newGoodsMap);	//상품 정보를 테이블에 추가
		ArrayList<ImageFileVO> imageFileList = (ArrayList)newGoodsMap.get("imageFileList");
		for(ImageFileVO imageFileVO : imageFileList) {	//각 이미지 정보에 상품 정보를 설정
			imageFileVO.setGoods_id(goods_id);
		}
		adminGoodsDAO.insertGoodsImageFile(imageFileList);	//이미지 정보를 이미지 테이블에 추가
		return goods_id;
	}
	
	@Override
	public List<GoodsVO> listNewGoods(Map condMap) throws Exception{
		return adminGoodsDAO.selectNewGoodsList(condMap);
	}
	@Override
	public Map goodsDetail(int goods_id) throws Exception {
		Map goodsMap = new HashMap();
		GoodsVO goodsVO=adminGoodsDAO.selectGoodsDetail(goods_id);	//상품 1개 상세 정보
		List imageFileList =adminGoodsDAO.selectGoodsImageFileList(goods_id);  //상품 1개 이미지 상세 정보
		goodsMap.put("goods", goodsVO);
		goodsMap.put("imageFileList", imageFileList);
		return goodsMap;
	}
	@Override
	public List goodsImageFile(int goods_id) throws Exception{
		List imageList =adminGoodsDAO.selectGoodsImageFileList(goods_id);
		return imageList;
	}
	
	@Override
	public void modifyGoodsInfo(Map goodsMap) throws Exception{
		adminGoodsDAO.updateGoodsInfo(goodsMap);
		
	}	
	@Override
	public void modifyGoodsImage(List<ImageFileVO> imageFileList) throws Exception{
		adminGoodsDAO.updateGoodsImage(imageFileList); 
	}
	
	@Override
	public List<OrderVO> listOrderGoods(Map condMap) throws Exception{
		return adminGoodsDAO.selectOrderGoodsList(condMap);
	}
	@Override
	public void modifyOrderGoods(Map orderMap) throws Exception{
		adminGoodsDAO.updateOrderGoods(orderMap);
	}
	
	@Override
	public void removeGoodsImage(int image_id) throws Exception{
		adminGoodsDAO.deleteGoodsImage(image_id);
	}
	
	@Override
	public void addNewGoodsImage(List imageFileList) throws Exception{
		adminGoodsDAO.insertGoodsImageFile(imageFileList);
	}

	@Override
	public void removeGoods(String goods_id) throws Exception {
		adminGoodsDAO.removeGoods(goods_id);
	}
}
