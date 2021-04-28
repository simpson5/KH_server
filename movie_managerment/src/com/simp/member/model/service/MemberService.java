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
	
	public Member selectOne(String memberId) {
		Connection conn = getConnection();
		Member member = memberDao.selectOne(conn, memberId);
		close(conn);
		return member;
	}
	
	public List<Member> selectList() {
		Connection conn = getConnection();
		List<Member> list = memberDao.selectList(conn);
		close(conn);
		return list;
	}
	
	public List<Member> selectList(int start, int end) {
		Connection conn = getConnection();
		List<Member> list = memberDao.selectList(conn, start, end);
		close(conn);
		return list;
	}
	
	public int enrollMember(Member member) {
		Connection conn = getConnection();
		int result = memberDao.enrollMember(conn, member);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
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

	public int updateMemberStatus(String memberId, String memberStatus) {
		Connection conn = getConnection(); 
		int result = 0;
		try {
			//1.board update
			result = memberDao.updateMemberStatus(conn, memberId, memberStatus);
			//2.attachment insert
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		}
		return result;
	
	}

}
