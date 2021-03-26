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

	public Member selectOne(Connection conn, String memberId, String password) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOne");
		Member member = null;

		try {
			// 3. PreparedStatement 객체 생성 (미완성 쿼리)
			// 3.1 ? 값 대임
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setString(2, password);
			// 4. 실행 : DML(executeUpdate) -> int, DQL(excuteQuery) -> ResultSet
			rset = pstmt.executeQuery();
			// 4.1 ResultSet -> Java 객체 옮겨담기
			while (rset.next()) {
				String memberName = rset.getString("member_name");
				String memberRole = rset.getString("member_role");
				String gender = rset.getString("gender");
				Date birthday = rset.getDate("birthday");
				String email = rset.getString("email");
				String phone = rset.getString("phone");
				String address = rset.getString("address");
				String hobby = rset.getString("hobby");
				Date enrolldate = rset.getDate("enroll_date");

				member = new Member(memberId, password, memberName, memberRole, gender, birthday, email, phone, address,
						hobby, enrolldate);
			}
		} catch (SQLException e) {
			throw new MemberException("로그인", e);
		} finally {
			// 5. 자원반납(생성역순 rset - pstmt)
			close(rset);
			close(pstmt);
		}

		return member;
	}

	// 회원 가입
	public int insertMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertMember");
		try {
			// 3. PreparedStatement 객체 생성 (미완성 쿼리)
			// 3.1 ? 값 대임
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberID());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getGender());
			pstmt.setDate(5, member.getBirthday());
			pstmt.setString(6, member.getEmail());
			pstmt.setString(7, member.getPhone());
			pstmt.setString(8, member.getAddress());
			pstmt.setString(9, member.getHobby());
			// 4. 실행 : DML(executeUpdate) -> int, DQL(excuteQuery) -> ResultSet
			// 4.1 ResultSet -> Java 객체 옮겨담기
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberException("회원 가입", e);
		} finally {
			// 5. 자원반납(생성역순 rset - pstmt)
			close(pstmt);
		}
		return result;
	}

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

}
