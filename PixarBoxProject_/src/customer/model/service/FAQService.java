package customer.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import customer.model.dao.FAQDAO;
import customer.model.vo.FAQ;

public class FAQService {

	public List<FAQ> selectAllFaq(int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<FAQ> list = new FAQDAO().selectAllFaq(conn, cPage, numPerPage);
		
		close(conn);
		
		return list;
	}

	public int selectFaqCount() {
		Connection conn = getConnection();
		int totalContent = new FAQDAO().selectFaqCount(conn);
		
		close(conn);
		
		return totalContent;
	}

	public int deleteFaq(String faqCode) {
		Connection conn = getConnection();
		int result = new FAQDAO().deleteFaq(conn, faqCode);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	public FAQ selectFaq(String faqCode) {
		Connection conn = getConnection();
		FAQ f = new FAQDAO().selectFAQ(conn, faqCode);
		
		close(conn);
		
		return f;
	}

	public int updateFAQ(FAQ f) {
		Connection conn = getConnection();
		int result = new FAQDAO().updateFAQ(conn, f);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	public int insertFAQ(FAQ f) {
		Connection conn = getConnection();
		int result = new FAQDAO().insertFAQ(conn, f);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	
}
