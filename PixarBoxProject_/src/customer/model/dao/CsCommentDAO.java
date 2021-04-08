package customer.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import customer.model.vo.CsComment;
import customer.model.vo.FAQ;

public class CsCommentDAO {
	
	private Properties prop = new Properties();
	
	public CsCommentDAO() {
		String fileName = CsCommentDAO.class.getResource("/sql/customer/customer-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public int insertCsComment(Connection conn, CsComment c) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertCsComment");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1,c.getBoardNo());
			pstmt.setString(2,c.getCscomment());
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public CsComment selectCsComment(Connection conn, int boardNo) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectCsComment");
		
		CsComment cs = new CsComment();
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				cs.setCscommentNo(rset.getInt("cscomment_no"));
				cs.setBoardNo(rset.getInt("board_no"));
				cs.setCscomment(rset.getString("cscomment"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return cs;
	}

	public int deleteCsComment(Connection conn, int csCommentNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("deleteCsComment");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1,csCommentNo);
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}
	
	

}
