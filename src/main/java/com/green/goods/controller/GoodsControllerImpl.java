package com.green.goods.controller;

import com.green.common.base.BaseController;
import com.green.goods.service.GoodsService;
import com.green.goods.vo.GoodsVO;
import lombok.extern.log4j.Log4j2;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log4j2
@Controller("goodsController")
@RequestMapping(value="/goods")
public class GoodsControllerImpl extends BaseController implements GoodsController {
    @Autowired
    private GoodsService goodsService;

    //상품 상세 조회 + 최근 본 목록 상품
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

// 최근 본 목록 상품
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


    //검색창에서 가져온 키워드가 포함된 상품 제목 조회. *관련검색어 기능.   //produces, 브라우저로 전송하는 JSON데이터의 한글 인코딩 지정
    @RequestMapping(value="/keywordSearch.do",method = RequestMethod.GET,produces = "application/text; charset=utf8")
    @ResponseBody
    public String  keywordSearch(@RequestParam("keyword") String keyword,
                                               HttpServletRequest request, HttpServletResponse response) throws Exception{
        log.info("keyword : "+keyword);
        if(keyword == null || keyword.equals("")){
            return null;
        }

        List<String> keywordList =goodsService.keywordSearch(keyword);
        // 최종 완성될 JSONObject 선언(전체)
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("keyword", keywordList);

        String jsonInfo = jsonObject.toString();
        log.info("jsonInfo : " + jsonInfo);
        return jsonInfo ;
    }

    //검색창에서 가져온 단어가 포함된 상품들 조회. *검색버튼을 누른 후 상품들 나타나는 것.
    @RequestMapping(value="/searchGoods.do" ,method = RequestMethod.GET)
    public ModelAndView searchGoods(@RequestParam("searchWord") String searchWord,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception{

        String viewName=getViewName(request);
        List<GoodsVO> goodsList=goodsService.searchGoods(searchWord);
        ModelAndView mav = new ModelAndView(viewName);
        mav.addObject("goodsList", goodsList);
        return mav;

    }




//getViewName
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