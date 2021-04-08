package customer.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import customer.model.dao.CustomerServiceDAO;
import customer.model.dao.FAQDAO;
import customer.model.vo.CustomerService;
import customer.model.vo.FAQ;

public class CustomerServiceService {

	
	public int insertCustomerService(CustomerService cs) {
		Connection conn = getConnection();
		int result = new CustomerServiceDAO().insertCustomerService(conn, cs);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	public List<CustomerService> selectAllCustomerService(int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<CustomerService> list = new CustomerServiceDAO().selectAllCustomerService(conn, cPage, numPerPage);
		
		close(conn);
		
		return list;
	}

	public int selectCustomerServiceCnt() {
		Connection conn = getConnection();
		int totalContent = new CustomerServiceDAO().selectCustomerServiceCnt(conn);
		
		close(conn);
		
		return totalContent;
	}


	public CustomerService selectCustomerServiceByBoardNo(int boardNo) {

		Connection conn = getConnection();
		CustomerService cs = new CustomerServiceDAO().selectCustomerServiceByBoardNo(conn, boardNo);
		
		close(conn);
		
		return cs;
	}

	public int updateCustomerService(CustomerService c) {
		Connection conn = getConnection();
		int result = new CustomerServiceDAO().updateCustomerService(conn, c);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	public int deleteCustomerService(int boardNo) {
		Connection conn = getConnection();
		int result = new CustomerServiceDAO().deleteCustomerService(conn, boardNo);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

}
