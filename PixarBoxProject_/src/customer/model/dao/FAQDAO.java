package customer.model.dao;

import static common.JDBCTemplate.*;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import customer.model.vo.FAQ;

public class FAQDAO {
	
	private Properties prop = new Properties();
	
	public FAQDAO() {
		String fileName = FAQDAO.class.getResource("/sql/customer/customer-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<FAQ> selectAllFaq(Connection conn, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectAllFaq");
		List<FAQ> list = new ArrayList<FAQ>();
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				FAQ f = new FAQ();
				
				f.setFaqCode(rset.getString("faq_code"));
				f.setFaqType(rset.getString("faq_type"));
				f.setFaqTitle(rset.getString("faq_title"));
				f.setFaqContent(rset.getString("faq_content"));
				
				list.add(f);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return list;
	}

	public int selectFaqCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectFaqCount");
		int totalContent = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			
			if(rset.next())
				totalContent = rset.getInt("cnt");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContent;
	}

	public int deleteFaq(Connection conn, String faqCode) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("deleteFaq");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, faqCode);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public FAQ selectFAQ(Connection conn, String faqCode) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectFaq");
		
		FAQ f = new FAQ();
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, faqCode);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				f.setFaqCode(faqCode);
				f.setFaqType(rset.getString("faq_type"));
				f.setFaqTitle(rset.getString("faq_title"));
				f.setFaqContent(rset.getString("faq_content"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return f;
	}

	public int updateFAQ(Connection conn, FAQ f) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updateFaq");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, f.getFaqType());
			pstmt.setString(2, f.getFaqTitle());
			pstmt.setString(3, f.getFaqContent());
			pstmt.setString(4, f.getFaqCode());
			
			result = pstmt.executeUpdate();
			
//			System.out.println("result@FAQDAO="+result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public int insertFAQ(Connection conn, FAQ f) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertFaq");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, f.getFaqCode());
			pstmt.setString(2, f.getFaqType());
			pstmt.setString(3, f.getFaqTitle());
			pstmt.setString(4, f.getFaqContent());
			
			result = pstmt.executeUpdate();
			
//			System.out.println("result@FAQDAO="+result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

}
