package com.simpson.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;

import com.simpson.model.dao.MemberDao;
import com.simpson.model.vo.Member;

public class MemberService {
	private MemberDao memberDao = new MemberDao();
	
	//회원가입
	public int insertMember(Member member) {
		Connection conn = getConnection();
		int result = memberDao.insertMember(conn, member);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

}
