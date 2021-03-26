package member.model.service;

import java.sql.Connection;

import member.model.dao.MemberDao;
import member.model.vo.Member;

import static common.JDBCTemplate.*;

public class MemberService {
	
	private MemberDao memberDao = new MemberDao();
	
	public static final String MEMBER_ROLE = "U";
	public static final String ADMIN_ROLE = "A";

	public Member selectOne(String memberId, String password) {
		Connection conn = getConnection();
		Member member = memberDao.selectOne(conn, memberId, password);
		close(conn);
		return member;
	}

	public int memberEnroll(Member member) {
		Connection conn = getConnection();
		int result = memberDao.memberEnroll(conn, member);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
}
