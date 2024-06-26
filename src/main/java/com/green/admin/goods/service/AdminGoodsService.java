package com.green.admin.goods.service;

import com.green.goods.vo.GoodsVO;
import com.green.goods.vo.ImageFileVO;
import com.green.order.vo.OrderVO;

import java.util.List;
import java.util.Map;

public interface AdminGoodsService {
	public int  addNewGoods(Map newGoodsMap) throws Exception;
	public List<GoodsVO> listNewGoods(Map condMap) throws Exception;
	public Map goodsDetail(int goods_id) throws Exception;
	public List goodsImageFile(int goods_id) throws Exception;
	public void modifyGoodsInfo(Map goodsMap) throws Exception;
	public void modifyGoodsImage(List<ImageFileVO> imageFileList) throws Exception;
	public List<OrderVO> listOrderGoods(Map condMap) throws Exception;
	public void modifyOrderGoods(Map orderMap) throws Exception;
	public void removeGoodsImage(int image_id) throws Exception;
	public void addNewGoodsImage(List imageFileList) throws Exception;
	public void removeGoods(String goods_id) throws Exception;
	
}
