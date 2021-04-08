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

import customer.model.vo.Notice;

public class NoticeDAO {
	
	private Properties prop = new Properties();
	
	public NoticeDAO() {
		String fileName = NoticeDAO.class.getResource("/sql/customer/customer-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public List<Notice> selectNoticeList(Connection conn, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectNoticeList");
		List<Notice> list = new ArrayList<Notice>();
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Notice n = new Notice();
				
				n.setNoticeNo(rset.getInt("notice_no"));
				n.setNoticeTitle(rset.getString("notice_title"));
				n.setNoticeContent(rset.getString("notice_content"));
				n.setNoticeDate(rset.getDate("notice_date"));
				
				list.add(n);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
				
		return list;
	}

	public int selectNoticeCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectNoticeCount");
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

	public Notice selectNotice(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectNotice");
		Notice n = new Notice();
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, noticeNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				n.setNoticeNo(noticeNo);
				n.setNoticeTitle(rset.getString("notice_title"));
				n.setNoticeContent(rset.getString("notice_content"));
				n.setNoticeOriginalFileName(rset.getString("notice_original_filename"));
				n.setNoticeRenamedFileName(rset.getString("notice_renamed_filename"));
				n.setNoticeDate(rset.getDate("notice_date"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return n;
	}

	public int insertNotice(Connection conn, Notice n) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertNotice");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeContent());
			pstmt.setString(3, n.getNoticeOriginalFileName());
			pstmt.setString(4, n.getNoticeRenamedFileName());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateNotice(Connection conn, Notice n) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updateNotice");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeContent());
			pstmt.setString(3, n.getNoticeOriginalFileName());
			pstmt.setString(4, n.getNoticeRenamedFileName());
			pstmt.setInt(5, n.getNoticeNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public int deleteNotice(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("deleteNotice");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, noticeNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int selectLastSeq(Connection conn) {
		int noticeNo = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectLastSeq");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				noticeNo = rset.getInt("currval");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return noticeNo;
	}

}
