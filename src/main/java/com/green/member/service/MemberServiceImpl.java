package com.green.member.service;

import com.green.member.dao.MemberDAO;
import com.green.member.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

    @Service("memberService")
    @Transactional(propagation= Propagation.REQUIRED)
    public class MemberServiceImpl implements MemberService {
        @Autowired
        private MemberDAO memberDAO;

        @Override
        public MemberVO login(Map loginMap) throws Exception{
            return memberDAO.login(loginMap);
        }

        @Override
        public String overlapped(String id) throws Exception{
            return memberDAO.selectOverlappedID(id);
        }

        @Override
        public void addMember(MemberVO memberVO) throws Exception{
            memberDAO.insertNewMember(memberVO);
        }
}

