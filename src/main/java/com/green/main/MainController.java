package com.green.main;

import com.green.common.base.BaseController;
import com.green.goods.service.GoodsService;
import com.green.goods.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller("mainController")
@EnableAspectJAutoProxy
public class MainController extends BaseController {
    @Autowired
    private GoodsService goodsService;

    //메인페이지 ("/ourbook"으로 해도 적용 안됨)
//    @RequestMapping(value= {"/ourbook", "/main/main.do"} ,method={RequestMethod.POST, RequestMethod.GET})
    @RequestMapping(value=  "/main/main.do" ,method={RequestMethod.POST, RequestMethod.GET})
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception{
        HttpSession session;
        ModelAndView mav=new ModelAndView();
        String viewName=getViewName(request);
        mav.setViewName(viewName);

        session=request.getSession();
        session.setAttribute("side_menu", "user");
        Map<String, List<GoodsVO>> goodsMap=goodsService.listGoods();
        mav.addObject("goodsMap", goodsMap);
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
