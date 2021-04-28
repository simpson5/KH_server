package com.simp.member.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.simp.member.model.dao.MemberDao;
import com.simp.member.model.vo.Member;

public class MemberService {
	
	MemberDao memberDao = new MemberDao();

	public int enrollMember(Member member) {
		Connection conn = getConnection();
		int result = memberDao.enrollMember(conn, member);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public Member selectOne(String memberId) {
		Connection conn = getConnection();
		Member member = memberDao.selectOne(conn, memberId);
		close(conn);
		return member;
	}

	public int updateMember(Member member) {
		Connection conn = getConnection(); 
		int result = 0;
		try {
			//1.board update
			result = memberDao.updateMember(conn, member);
			//2.attachment insert
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		}
		return result;
	}

	public int deleteMember(String memberId) {
		Connection conn = getConnection();
		int result = memberDao.deleteMember(conn, memberId);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public List<String> listMemberId() {
		Connection conn = getConnection();
		List<String> lsit = memberDao.listMemberId(conn);
		close(conn);
		return lsit;
	}
	
	public String FindInfo(String memberName, String email) {
		Connection conn = getConnection();
		String member_id = memberDao.FindInfo(conn,memberName,email);
		close(conn);
		return member_id;
	}

	public String FindPassword(String memberName, String memberId) {
		
		Connection conn = getConnection();
		String password = memberDao. FindPassword(conn,memberName,memberId);
		close(conn);
		return password;
	}

}
