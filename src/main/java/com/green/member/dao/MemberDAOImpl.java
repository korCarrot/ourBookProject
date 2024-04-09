package com.green.member.dao;

import com.green.member.vo.MemberVO;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Map;

    @Repository("memberDAO")
    @Log4j2
    public class MemberDAOImpl  implements MemberDAO{
        @Autowired
        private SqlSession sqlSession;

        //로그인
        @Override
        public MemberVO login(Map loginMap) throws DataAccessException {
            MemberVO member=sqlSession.selectOne("mapper.member.login",loginMap);
            return member;
        }

        //아이디 중복검사
        @Override
        public String selectOverlappedID(String id) throws DataAccessException {
            String result =  sqlSession.selectOne("mapper.member.selectOverlappedID",id);
            return result;
        }

        //회원 가입
        @Override
        public void insertNewMember(MemberVO memberVO) throws DataAccessException{
            System.out.println("회원가입 하러 이동 - dao");
            sqlSession.insert("mapper.member.insertNewMember",memberVO);
        }
}
