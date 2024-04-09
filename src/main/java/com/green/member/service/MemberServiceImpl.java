package com.green.member.service;

import com.green.member.dao.MemberDAO;
import com.green.member.vo.MemberVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

    @Service("memberService")
    @Transactional(propagation= Propagation.REQUIRED)
    @Log4j2
    public class MemberServiceImpl implements MemberService {
        @Autowired
        private MemberDAO memberDAO;

        //로그인 기능
        @Override
        public MemberVO login(Map loginMap) throws Exception{
            return memberDAO.login(loginMap);
        }

        //ID중복확인
        @Override
        public String overlapped(String id) throws Exception{
            return memberDAO.selectOverlappedID(id);
        }

        //회원가입
        @Override
        public void addMember(MemberVO memberVO) throws Exception{
            System.out.println("회원가입 하러 이동 - service");
            memberDAO.insertNewMember(memberVO);
        }
}

