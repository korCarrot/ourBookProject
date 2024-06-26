package com.green.admin.member.dao;

import com.green.member.vo.MemberVO;
import org.springframework.dao.DataAccessException;

import java.util.ArrayList;
import java.util.HashMap;

public interface AdminMemberDAO {
	public ArrayList<MemberVO> listMember(HashMap condMap) throws DataAccessException;
	public MemberVO memberDetail(String member_id) throws DataAccessException;
	public void modifyMemberInfo(HashMap memberMap) throws DataAccessException;
}
