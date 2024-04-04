package com.green.goods.controller;

import com.green.common.base.BaseController;
import com.green.goods.service.GoodsService;
import com.green.goods.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller("goodsController")
@RequestMapping(value="/goods")
public class GoodsControllerImpl extends BaseController implements GoodsController {
    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value="/goodsDetail.do" ,method = RequestMethod.GET)
    public ModelAndView goodsDetail(@RequestParam("goods_id") String goods_id,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {
        String viewName = getViewName(request);
        HttpSession session=request.getSession();

        Map goodsMap=goodsService.goodsDetail(goods_id);

        ModelAndView mav = new ModelAndView(viewName);
        mav.addObject("goodsMap", goodsMap);

        GoodsVO goodsVO=(GoodsVO)goodsMap.get("goodsVO");
        addGoodsInQuick(goods_id,goodsVO,session);

        return mav;

    }

    private void addGoodsInQuick(String goods_id,GoodsVO goodsVO,HttpSession session){
        boolean already_existed=false;
        List<GoodsVO> quickGoodsList; //최근 본 상품 저장 ArrayList
        quickGoodsList=(ArrayList<GoodsVO>)session.getAttribute("quickGoodsList");

        if(quickGoodsList!=null){
            if(quickGoodsList.size() < 5){ //미리본 상품 리스트에 상품개수가 다섯 개 이하인 경우
                //이미 존재하는 상품인지 비교, 존재하면 already_existed=true;
                for(int i=0; i<quickGoodsList.size();i++){
                    GoodsVO goodsBean=(GoodsVO)quickGoodsList.get(i);
                    if(goods_id.equals(goodsBean.getGoods_id())){
                        already_existed=true;
                        break;
                    }
                }
                //상품 정보를 목록에 저장
                if(already_existed==false){
                    quickGoodsList.add(goodsVO);
                }
            }

//            최근 본 상품 목록이 없으면 생성하여 상품 정보를 저장
        }else{
            quickGoodsList =new ArrayList<GoodsVO>();
            quickGoodsList.add(goodsVO);

        }
        session.setAttribute("quickGoodsList",quickGoodsList);
        session.setAttribute("quickGoodsListNum", quickGoodsList.size());
    }





    private String getViewName(HttpServletRequest request) throws Exception {
        String contextPath = request.getContextPath();
        String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
        if (uri == null || uri.trim().equals("")) {
            uri = request.getRequestURI();
        }

        int begin = 0;
        if (!((contextPath == null) || ("".equals(contextPath)))) {
            begin = contextPath.length();
        }

        int end;
        if (uri.indexOf(";") != -1) {
            end = uri.indexOf(";");
        } else if (uri.indexOf("?") != -1) {
            end = uri.indexOf("?");
        } else {
            end = uri.length();
        }

        String fileName = uri.substring(begin, end);
        if (fileName.indexOf(".") != -1) {
            fileName = fileName.substring(0, fileName.lastIndexOf("."));
        }
        if (fileName.lastIndexOf("/") != -1) {
            fileName = fileName.substring(fileName.lastIndexOf("/",1), fileName.length());
        }
        return fileName;
    }
}