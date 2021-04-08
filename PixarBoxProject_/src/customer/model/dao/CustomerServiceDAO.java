package customer.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import customer.model.vo.CustomerService;
import customer.model.vo.FAQ;

public class CustomerServiceDAO {

	private Properties prop = new Properties();
	
	public CustomerServiceDAO() {
		String fileName = CustomerServiceDAO.class.getResource("/sql/customer/customer-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public int insertCustomerService(Connection conn, CustomerService cs) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertCustomerService");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, cs.getMemberId());
			pstmt.setString(2, cs.getBoardTitle());
			pstmt.setString(3, cs.getBoardContent());
			pstmt.setString(4, cs.getpublicYN());
			pstmt.setString(5, cs.getOriginalFileName());
			pstmt.setString(6, cs.getRenamedFileName());
			pstmt.setString(7, cs.getBoardType());
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public List<CustomerService> selectAllCustomerService(Connection conn, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectAllCustomerService");
		List<CustomerService> list = new ArrayList<CustomerService>();
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				CustomerService cs = new CustomerService();
				
				cs.setBoardNo(rset.getInt("board_no"));
				cs.setMemberId(rset.getString("memberid"));
				cs.setBoardTitle(rset.getString("board_title"));
				cs.setBoardDate(rset.getDate("board_date"));
				cs.setBoardContent(rset.getString("board_content"));
				cs.setpublicYN(rset.getString("public_yn"));
				cs.setOriginalFileName(rset.getString("originalfilename"));
				cs.setRenamedFileName(rset.getString("renamedFileName"));
				cs.setBoardType(rset.getString("boardType"));
				
				
				list.add(cs);
			}
			
			System.out.println("Dao="+list);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return list;
	}

	public int selectCustomerServiceCnt(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectCustomerServiceCnt");
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

	public CustomerService selectCustomerServiceByBoardNo(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectCustomerServiceByBoardNo");
		CustomerService cs = new CustomerService();
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				cs.setBoardNo(rset.getInt("board_no"));
				cs.setMemberId(rset.getString("memberid"));
				cs.setBoardTitle(rset.getString("board_title"));
				cs.setBoardDate(rset.getDate("board_date"));
				cs.setBoardContent(rset.getString("board_content"));
				cs.setpublicYN(rset.getString("public_yn"));
				cs.setOriginalFileName(rset.getString("originalfilename"));
				cs.setRenamedFileName(rset.getString("renamedFileName"));
				cs.setBoardType(rset.getString("boardType"));
				
			}
				
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return cs;
	}

	public int updateCustomerService(Connection conn, CustomerService c) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updateCustomerService");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, c.getBoardTitle());
			pstmt.setString(2, c.getBoardContent());
			pstmt.setString(3, c.getpublicYN());
			pstmt.setString(4, c.getOriginalFileName());
			pstmt.setString(5, c.getRenamedFileName());
			pstmt.setString(6, c.getBoardType());
			pstmt.setInt(7, c.getBoardNo());
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public int deleteCustomerService(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("deleteCustomerService");
		
		try {
			pstmt = conn.prepareStatement(query);
		

			pstmt.setInt(1,boardNo);
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}
	
	
	
}
