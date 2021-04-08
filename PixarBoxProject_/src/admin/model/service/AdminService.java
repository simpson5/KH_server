package admin.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import admin.model.dao.AdminDAO;
import member.model.vo.Member;
import movie.model.vo.Movie;
import movie.model.vo.MovieNow;

public class AdminService {

	public List<Member> selectMemberList(int cPage, int numPerPage) {
		 Connection conn = getConnection();
	        List<Member> list= new AdminDAO().selectMemberList(conn, cPage, numPerPage);
	        close(conn);
	        return list;
	}

	public int selectTotalContent() {
		Connection conn = getConnection();
		int totalContent = new AdminDAO().selectTotalContent(conn);
		close(conn);
		return totalContent;
	}

	public List<Member> selectMemberByMemberId(String searchKeyword, int cPage, int numPerPage) {
		List<Member> list = null;
		Connection conn = getConnection();
		list = new AdminDAO().selectMemberByMemberId(conn, searchKeyword, cPage, numPerPage);
		close(conn);
		return list;
	}

	public List<Member> selectMemberByMemberName(String searchKeyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Member> list = new AdminDAO().selectMemberByMemberName(conn, searchKeyword, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int selectTotalContentByMemberId(String searchKeyword) {
		Connection conn = getConnection();
		int totalContent = new AdminDAO().selectTotalContentByMemberId(conn, searchKeyword);
		close(conn);
		return totalContent;
	}

	public int selectTotalContentByMemberName(String searchKeyword) {
		Connection conn = getConnection();
		int totalContent = new AdminDAO().selectTotalContentByMemberName(conn, searchKeyword);
		close(conn);
		return totalContent;
	}

	public List<Member> selectMemberByMemberPhone(String searchKeyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Member> list = new AdminDAO().selectMemberByMemberPhone(conn, searchKeyword, cPage, numPerPage);
		close(conn);
		return list;
	}

	public List<Member> selectMemberByMemberEmail(String searchKeyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Member> list = new AdminDAO().selectMemberByMemberEmail(conn, searchKeyword, cPage, numPerPage);
		close(conn);
		return list;
	}
	
	public List<Member> selectMemberByMemberGender(String searchKeyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Member> list = new AdminDAO().selectMemberByMemberGender(conn, searchKeyword, cPage, numPerPage);
		close(conn);
		return list;
	}
	
	public List<Member> selectMemberByMemberAge(String searchKeyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Member> list = new AdminDAO().selectMemberByMemberAge(conn, searchKeyword, cPage, numPerPage);
		close(conn);
		return list;
	}

	public List<Member> selectMemberByMemberPoint(int searchKeyword1, int searchKeyword2, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Member> list = new AdminDAO().selectMemberByMemberPoint(conn, searchKeyword1, searchKeyword2, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int selectTotalContentByMemberEmail(String searchKeyword) {
		Connection conn = getConnection();
		int totalContent = new AdminDAO().selectTotalContentByMemberEmail(conn, searchKeyword);
		close(conn);
		return totalContent;
	}

	public int selectTotalContentByMemberPhone(String searchKeyword) {
		Connection conn = getConnection();
		int totalContent = new AdminDAO().selectTotalContentByMemberPhone(conn, searchKeyword);
		close(conn);
		return totalContent;
	}
	
	public int selectTotalContentByMemberGender(String searchKeyword) {
		Connection conn = getConnection();
		int totalContent = new AdminDAO().selectTotalContentByMemberGender(conn, searchKeyword);
		close(conn);
		return totalContent;
	}
	
	public int selectTotalContentByMemberAge(String searchKeyword) {
		Connection conn = getConnection();
		int totalContent = new AdminDAO().selectTotalContentByMemberAge(conn, searchKeyword);
		close(conn);
		return totalContent;
	}

	public int selectTotalContentByMemberPoint(int searchKeyword1, int searchKeyword2) {
		Connection conn = getConnection();
		int totalContent = new AdminDAO().selectTotalContentByMemberPoint(conn, searchKeyword1, searchKeyword2);
		close(conn);
		return totalContent;
	}
	
	public int updateMemberFromAdmin(Member m) {
		Connection conn = getConnection();
		int result = new AdminDAO().updateMemberFromAdmin(conn, m);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	public int deleteMemberFromAdmin(String memberId) {
		Connection conn = getConnection();
		int result = new AdminDAO().deleteMemberFromAdmin(conn, memberId);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	public int insertMovie(Movie m) {
		Connection conn = getConnection();
		int result = new AdminDAO().insertMovie(conn, m);
		//트랜잭션 처리
		if(result>0) {
			commit(conn);
		}
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public int updateMovie(Movie m) {
		Connection conn = getConnection();
		int result = new AdminDAO().updateMovie(conn, m);
		//트랜잭션 처리
		if(result>0) {
			commit(conn);
		}
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public int deleteMovie(String movieCode) {
		Connection conn = getConnection();
		int result = new AdminDAO().deleteMovie(conn, movieCode);
		//트랜잭션 처리
		if(result>0) {
			commit(conn);
		}
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public int insertMovieNow(MovieNow movieNow) {
		Connection conn = getConnection();
		int result = new AdminDAO().insertMovieNow(conn, movieNow);
		if(result>0) {
			commit(conn);
		}
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public int deleteMovieNow(String movieCode) {
		Connection conn = getConnection();
		int result = new AdminDAO().deleteMovieNow(conn, movieCode);
		if(result>0) {
			commit(conn);
		}
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public List<String> selectByMovieName(String srchMovie) {
		Connection conn = getConnection();
		List<String> movieList = new AdminDAO().selectByMovieName(conn, srchMovie);
		close(conn);
		return movieList ;
	}

	public String selectMovieCode(String movieTitle) {
		Connection conn = getConnection();
		String movieCode = new AdminDAO().selectMovieCode(conn, movieTitle);
		close(conn);
		return movieCode;
	}

	public Map<Integer, Integer> selectMonthSales(int year) {
		Connection conn = getConnection();
		Map<Integer, Integer> list = new AdminDAO().selectMonthSales(conn, year);
		//트랜잭션 처리
	
		close(conn);
		return list;
	}


}
