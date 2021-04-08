package customer.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import customer.model.dao.NoticeDAO;
import customer.model.vo.Notice;

public class NoticeService {

	public List<Notice> selectNoticeList(int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Notice> list = new NoticeDAO().selectNoticeList(conn, cPage, numPerPage);
		
		close(conn);
		
		return list;
	}

	public int selectNoticeCount() {
		Connection conn = getConnection();
		int totalContent = new NoticeDAO().selectNoticeCount(conn);
		
		close(conn);
		
		return totalContent;
	}

	public Notice selectNotice(int noticeNo) {
		Connection conn = getConnection();
		Notice n = new NoticeDAO().selectNotice(conn, noticeNo);
		
		close(conn);
		
		return n;
	}

	public int insertNotice(Notice n) {
		Connection conn = getConnection();
		int result = new NoticeDAO().insertNotice(conn, n);
		
		if(result>0) {
			
			n.setNoticeNo(new NoticeDAO().selectLastSeq(conn));
			
			commit(conn);
		}
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	public int updateNotice(Notice n) {
		Connection conn = getConnection();
		int result = new NoticeDAO().updateNotice(conn, n);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	public int deleteNotice(int noticeNo) {
		Connection conn = getConnection();
		int result = new NoticeDAO().deleteNotice(conn, noticeNo);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

}
