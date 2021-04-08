package customer.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;

import customer.model.dao.CsCommentDAO;
import customer.model.dao.CustomerServiceDAO;
import customer.model.vo.CsComment;
import customer.model.vo.CustomerService;

public class CsCommentService {

	public int insertCsComment(CsComment c) {
		Connection conn = getConnection();
		int result = new CsCommentDAO().insertCsComment(conn, c);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	public CsComment selectCsComment(int boardNo) {
		Connection conn = getConnection();
		CsComment cc = new CsCommentDAO().selectCsComment(conn, boardNo);
		
		close(conn);
		
		return cc;
	}

	public int deleteCsComment(int csCommentNo) {
		Connection conn = getConnection();
		int result = new CsCommentDAO().deleteCsComment(conn, csCommentNo);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

}
