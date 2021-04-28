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
	
	public List<Member> selectList(Connection conn) {
		List<Member> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("selectList");
		try {
			// 미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			// 쿼리문실행
			rset = pstmt.executeQuery();

			list = new ArrayList<>();
			while (rset.next()) {
				Member member = new Member();
				member.setMember_id(rset.getString("MEMBER_ID"));
				member.setPassword(rset.getString("PASSWORD"));
				member.setMember_name(rset.getString("MEMBER_NAME"));
				member.setMember_ssn(rset.getString("MEMBER_SSN"));
				member.setEmail(rset.getString("EMAIL"));
				member.setPhone(rset.getString("PHONE"));
				member.setAddress(rset.getString("ADDRESS"));
				member.setMember_status(rset.getString("MEMBER_STATUS"));
				member.setMember_grade(rset.getString("MEMBER_GRADE"));
				member.setEnroll_date(rset.getDate("ENROLL_DATE"));
				member.setPoint(rset.getInt("POINT"));
				
				list.add(member);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public List<Member> selectList(Connection conn, int start, int end) {
		List<Member> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectPagedList");
		try {
			// 미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			// 쿼리문실행
			rset = pstmt.executeQuery();

			list = new ArrayList<>();
			while (rset.next()) {
				Member member = new Member();
				member.setMember_id(rset.getString("MEMBER_ID"));
				member.setPassword(rset.getString("PASSWORD"));
				member.setMember_name(rset.getString("MEMBER_NAME"));
				member.setMember_ssn(rset.getString("MEMBER_SSN"));
				member.setEmail(rset.getString("EMAIL"));
				member.setPhone(rset.getString("PHONE"));
				member.setAddress(rset.getString("ADDRESS"));
				member.setPreference_genre(rset.getString("PREFERENCEGENRE"));
				member.setMember_status(rset.getString("MEMBER_STATUS"));
				member.setMember_grade(rset.getString("MEMBER_GRADE"));
				member.setEnroll_date(rset.getDate("ENROLL_DATE"));
				member.setPoint(rset.getInt("POINT"));
				list.add(member);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
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

	public int updateMemberStatus(Connection conn, String memberId, String memberStatus) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateMemberStatus");

		try {
			// 미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);

			
			pstmt.setString(1, memberStatus);
			pstmt.setString(2, memberId);
			

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

}
