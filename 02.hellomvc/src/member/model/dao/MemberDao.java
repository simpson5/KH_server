package member.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import member.model.exception.MemberException;
import member.model.vo.Member;

import static common.JDBCTemplate.*;

public class MemberDao {

	private Properties prop = new Properties();

	/**
	 * 1. MemberDao객체 생성시(최초 1회) member-query.properties의 내용을 읽어다 prop에 저장한다.
	 * 
	 * 2. dao메소드 호출시마다 prop으로부터 query를 가져다 사용한다.
	 */
	public MemberDao() {
		String fileName = this.getClass() // 클래스 객체
							  .getResource("/sql/member/member-query.properties") // Url 객체
							  .getPath(); // String 객체 : 절대경로
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Member selectOne(Connection conn, String memberId) {
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectOne");
		
		try{
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//미완성 쿼리문 값대입
			pstmt.setString(1, memberId);
			//쿼리문실행
			rset = pstmt.executeQuery();

			if(rset.next()){
				member = new Member();
				member.setMemberID(rset.getString("member_id"));
				member.setPassword(rset.getString("password"));
				member.setMemberName(rset.getString("member_name"));
				member.setMemberRole(rset.getString("member_role"));
				member.setGender(rset.getString("gender"));
				member.setBirthday(rset.getDate("birthday"));
				member.setEmail(rset.getString("email"));
				member.setPhone(rset.getString("phone"));
				member.setAddress(rset.getString("address"));
				member.setHobby(rset.getString("hobby"));
				member.setEnrolldate(rset.getDate("enroll_date"));
				
				System.out.println(member);
			}
		}catch (SQLException e) {
			throw new MemberException("회원 확인", e);
		}finally{
			close(rset);
			close(pstmt);
		}
		return member;

	}

	// 회원 가입
	public int memberEnroll(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("memberEnroll");
		try {
			//3. PreparedStatement 객체 생성 (미완성 쿼리)
			//3.1 ? 값 대임
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberID());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMemberRole());
			pstmt.setString(5, member.getGender());
			pstmt.setDate(6, member.getBirthday());
			pstmt.setString(7, member.getEmail());
			pstmt.setString(8, member.getPhone());
			pstmt.setString(9, member.getAddress());
			pstmt.setString(10, member.getHobby());
			pstmt.setDate(11, member.getEnrolldate());
			
			//4. 실행 : DML(executeUpdate) -> int, DQL(excuteQuery) -> ResultSet
			//4.1 ResultSet -> Java 객체 옮겨담기
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberException("회원 가입", e);
		} finally {
			//5. 자원반납(생성역순 rset - pstmt)
			close(pstmt);
		}
		return result;
	}

	//회원 탈퇴
	public int deleteMember(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteMember");
		try {
			// 3. PreparedStatement 객체 생성 (미완성 쿼리)
			// 3.1 ? 값 대임
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			// 4. 실행 : DML(executeUpdate) -> int, DQL(excuteQuery) -> ResultSet
			// 4.1 ResultSet -> Java 객체 옮겨담기
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberException("회원 탈퇴", e);
		} finally {
			// 5. 자원반납(생성역순 rset - pstmt)
			close(pstmt);
		}
		return result;
	}

	public int memberUpdate(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("memberUpdate");
		try {
			//3. PreparedStatement 객체 생성 (미완성 쿼리)
			//3.1 ? 값 대임
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberName());
			pstmt.setString(2, member.getGender());
			pstmt.setDate(3, member.getBirthday());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getPhone());
			pstmt.setString(6, member.getAddress());
			pstmt.setString(7, member.getHobby());
			pstmt.setString(8, member.getMemberID());
			
			//4. 실행 : DML(executeUpdate) -> int, DQL(excuteQuery) -> ResultSet
			//4.1 ResultSet -> Java 객체 옮겨담기
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberException("회원 수정", e);
		} finally {
			//5. 자원반납(생성역순 rset - pstmt)
			close(pstmt);
		}
		return result;
	}

	public int updatePassword(Connection conn, String memberId, String newPassword) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updatePassword");
		try {
			//3. PreparedStatement 객체 생성 (미완성 쿼리)
			//3.1 ? 값 대임
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPassword);
			pstmt.setString(2, memberId);
			
			//4. 실행 : DML(executeUpdate) -> int, DQL(excuteQuery) -> ResultSet
			//4.1 ResultSet -> Java 객체 옮겨담기
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberException("비밀번호 수정", e);
		} finally {
			//5. 자원반납(생성역순 rset - pstmt)
			close(pstmt);
		}
		return result;
	}

}
