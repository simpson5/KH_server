package com.simp.member.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.simp.member.model.vo.Member;

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

	public int enrollMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("memberEnroll");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMember_id());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getMember_name());
			pstmt.setString(4, member.getMember_ssn());
			pstmt.setString(5, member.getEmail());
			pstmt.setString(6, member.getPhone());
			pstmt.setString(7, member.getAddress());
			pstmt.setString(8, member.getPreference_genre());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public Member selectOne(Connection conn, String memberId) {
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("selectOne");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				String member_id = rset.getString("member_id");
				String password = rset.getString("password");
				String member_name = rset.getString("member_name");
				String member_ssn = rset.getString("member_ssn");
				String email = rset.getString("email");
				String phone = rset.getString("phone");
				String address = rset.getString("address");
				String preference_genre = rset.getString("preference_genre");
				String member_status = rset.getString("member_status");
				String member_grade = rset.getString("member_grade");
				Date enroll_date = rset.getDate("enroll_date");
				int point = rset.getInt("point");
				
				member = new Member(member_id, password, member_name, member_ssn, email, phone, address, preference_genre, member_status, member_grade, enroll_date, point);

				System.out.println(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return member;
	}


	public int updateMember(Connection conn, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateMember");

		try {
			// 미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);

			
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getMember_name());
			pstmt.setString(3, member.getMember_ssn());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getPhone());
			pstmt.setString(6, member.getAddress());
			pstmt.setString(7, member.getPreference_genre());
			pstmt.setString(8, member.getMember_grade());
			pstmt.setString(9, member.getMember_id());

			// 쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			// DML은 executeUpdate()
			result = pstmt.executeUpdate();
			
			System.out.println("result@Dao = " +  result);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int deleteMember(Connection conn, String memberId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteMember");

		try {
			// 미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			// 쿼리문미완성
			pstmt.setString(1, memberId);

			// 쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			// DML은 executeUpdate()
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public List<String> listMemberId(Connection conn) {
		List<String> list = new ArrayList<String>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from member";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(rset.getString("member_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public String FindInfo(Connection conn, String memberName, String email) {
		String member_id = "";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("findMamberId");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setNString(1, memberName);
			pstmt.setNString(2, email);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				member_id = rset.getString("member_id");		
			}	
			
			System.out.println("FindMember@Dao = "+ member_id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return member_id;
	
	}

	public String FindPassword(Connection conn, String memberName, String memberId) {
		String password = "";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("findPassword");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setNString(1,memberId);
			pstmt.setNString(2, memberName);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				password = rset.getString("password");		
			}	
			
			System.out.println("FindMember@Dao = "+password);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return password;
	}

}
