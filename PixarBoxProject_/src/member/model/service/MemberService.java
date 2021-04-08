package member.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import member.model.vo.PointList;
import member.model.dao.MemberDAO;
import member.model.vo.Member;

public class MemberService {

	public int insertMember(Member m) {
		Connection conn = getConnection();
		int result = new MemberDAO().insertMember(conn, m);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	public Member selectOne(String memberId) {
		Connection conn = getConnection();
		Member m = new MemberDAO().selectOne(conn, memberId);
		
		close(conn);
		
		return m;
	}

	public int updateMember(Member m) {
		Connection conn = getConnection();
		int result = new MemberDAO().updateMember(conn, m);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	public int deleteMember(String memberId) {
		Connection conn = getConnection();
		int result = new MemberDAO().deleteMember(conn, memberId);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	public int updatePassword(Member m) {
		Connection conn = getConnection();
		int result = new MemberDAO().updatePassword(conn, m);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	public List<PointList> selectAddPointBymemberId(String memberId) {
		Connection conn = getConnection();
		List<PointList> list = new MemberDAO().selectAddPointBymemberId(conn, memberId);
		
		close(conn);
		
		return list;
	}

	public List<PointList> selectUsePointBymemberId(String memberId) {
		Connection conn = getConnection();
		List<PointList> list = new MemberDAO().selectUsePointBymemberId(conn, memberId);
		
		close(conn);
		
		return list;
	}

	public int updateCancelYN_Y(String ticketNo) {
		Connection conn = getConnection();
		int result = new MemberDAO().updateCancelYN_Y(conn, ticketNo);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	public int updateReservationYN_N(int screenCode, String seatNo) {
		Connection conn = getConnection();
		int result = new MemberDAO().updateReservationYN_N(conn, screenCode, seatNo);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	public int deletePayment(String ticketNo) {
		Connection conn = getConnection();
		int result = new MemberDAO().deletePayment(conn, ticketNo);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	public PointList selectPointByPaymentCode(String ticketNo, String status) {
		Connection conn = getConnection();
		PointList pl = new MemberDAO().selectPointByPaymentCode(conn, ticketNo, status);
		
		close(conn);
		
		return pl;
	}


}
