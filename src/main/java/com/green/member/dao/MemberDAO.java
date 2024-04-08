package com.green.member.dao;

import com.green.member.vo.MemberVO;
import org.springframework.dao.DataAccessException;

import java.util.Map;

public interface MemberDAO {
    public MemberVO login(Map loginMap) throws DataAccessException;
    public void insertNewMember(MemberVO memberVO) throws DataAccessException;
    public String selectOverlappedID(String id) throws DataAccessException;
}
