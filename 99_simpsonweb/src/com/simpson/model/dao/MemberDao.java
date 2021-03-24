package com.simpson.model.dao;

import static common.JDBCTemplate.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.simpson.model.exception.MemberException;
import com.simpson.model.vo.Member;

public class MemberDao {
	private Properties prop = new Properties();
	
	public MemberDao() {
		String fileName = "member-query.properties";
		String path = this.getClass().getResource(fileName).getPath();
		try {
			prop.load(new FileReader(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//회원 가입
		public int insertMember(Connection conn, Member member) {
			PreparedStatement pstmt = null;
			int result = 0;
			String sql = prop.getProperty("insertMember");
//			String sql = "insert into member values(?, ?, ?, ?, ?, ?, ?, default)";
			try {
				//3. PreparedStatement 객체 생성 (미완성 쿼리)
				//3.1 ? 값 대임
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, member.getMemberId());
				pstmt.setString(2, member.getPassword());
				pstmt.setString(3, member.getName());
				pstmt.setString(4, member.getSsn());
				pstmt.setString(5, member.getPhone());
				pstmt.setString(6, member.getEmail());
				pstmt.setString(7, member.getAdress());
				
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
