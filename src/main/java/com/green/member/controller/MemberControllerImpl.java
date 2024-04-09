package com.green.member.controller;

import com.green.common.base.BaseController;
import com.green.member.service.MemberService;
import com.green.member.vo.MemberVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Log4j2
@Controller("memberController")
@RequestMapping(value="/member")
public class MemberControllerImpl extends BaseController implements MemberController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberVO memberVO;

    @Override
    @RequestMapping(value="/login.do" ,method = RequestMethod.POST)
    public ModelAndView login(@RequestParam Map<String, String> loginMap,
                              HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();
        memberVO = memberService.login(loginMap);
        log.info("로그인 메서드");
        //로그인 정보 값이 있다면 세션에 저장
        if(memberVO!= null && memberVO.getMember_id()!=null){
            HttpSession session=request.getSession();
            session.setAttribute("isLogOn", true);
            session.setAttribute("memberInfo",memberVO);

            //상품 주문 과정에서 로그인 시 다시 주문 화면으로
            String action=(String)session.getAttribute("action");
            if(action!=null && action.equals("/order/orderEachGoods.do")){
                mav.setViewName("forward:"+action); //****타일즈에서 어떻게 할지 모르는 상태. 잘 보자. 어떻게 받아서 어디로 가는지
            }
            //그 외에는 메인페이지로 이동
            else{
                mav.setViewName("redirect:/main/main.do");
            }

            //로그인 정보가 없을 시 로그인 창으로
        }else{
            String message="아이디나  비밀번호가 틀립니다. 다시 로그인해주세요";
            mav.addObject("message", message);
            mav.setViewName("/member/loginForm");
        }

    return mav;
    }

    //로그아웃
    @Override
    @RequestMapping(value="/logout.do" ,method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();
        HttpSession session=request.getSession();
        session.setAttribute("isLogOn", false); //로그인 상태 변경
        session.removeAttribute("memberInfo");        //로그인 정보 세션에서 삭제
        mav.setViewName("redirect:/main/main.do");
        return mav;
    }


    //로그인폼으로 이동
    @RequestMapping(value="/loginForm.do" ,method = RequestMethod.GET)
    public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response)throws Exception{
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/member/loginForm");
        return mav;
    }

    //회원가입폼으로 이동
    @RequestMapping(value="/memberForm.do" ,method = RequestMethod.GET)
    public ModelAndView memberForm(HttpServletRequest request, HttpServletResponse response)throws Exception{
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/member/memberForm");
        return mav;
    }


//회원가입 기능
@Override
@RequestMapping(value="/addMember.do" ,method = RequestMethod.POST)
public ResponseEntity addMember(@ModelAttribute("memberVO") MemberVO memberVO,
                                HttpServletRequest request, HttpServletResponse response) throws Exception {

    String message = null;
    ResponseEntity resEntity = null;
    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.add("Content-Type","text/html; charset=utf-8");
    try {
        //location.href를 통해 알림을 띄우고 페이지 이동.
        System.out.println("memberVO : "+memberVO);
        System.out.println("회원가입 하러 이동 - controller");
        memberService.addMember(memberVO);
        message  = "<script>";
        message +=" alert('회원 가입을 마쳤습니다.로그인창으로 이동합니다.');";
        message += " location.href='"+request.getContextPath()+"/member/loginForm.do';";
        message += " </script>";

    }catch(Exception e) {
        System.out.println("회원가입 중 에러");
        message  = "<script>";
        message +=" alert('작업 중 오류가 발생했습니다. 다시 시도해 주세요');";
        message += " location.href='"+request.getContextPath()+"/member/memberForm.do';";
        message += " </script>";

    }
//    message: 응답 본문에 해당하는 문자열입니다. 클라이언트에게 전달할 메시지나 데이터를 포함. 클라이언트 측에서 실행될 스크립트 코드.
//    responseHeaders: 클라이언트에게 전달할 응답의 헤더 정보
//    HttpStatus.OK: 응답의 HTTP 상태 코드
    resEntity =new ResponseEntity(message, responseHeaders, HttpStatus.OK);
    return resEntity;
}

//ID중복 확인 기능    (memberForm.jsp에서 아이디 중복 검사하는 ajax에서 id값 받아옴)
    @Override
    @RequestMapping(value="/overlapped.do" ,method = RequestMethod.POST)
    public ResponseEntity overlapped(@RequestParam("id") String id,HttpServletRequest request, HttpServletResponse response) throws Exception{
        ResponseEntity resEntity = null;
        String result = memberService.overlapped(id);   //아이디 중복 검사.    decode함수로 true, false를 문자열로 반환.
        resEntity =new ResponseEntity(result, HttpStatus.OK);
        return resEntity;
    }

}