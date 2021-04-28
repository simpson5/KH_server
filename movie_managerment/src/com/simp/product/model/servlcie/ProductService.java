package com.simp.product.model.servlcie;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.simp.product.model.dao.ProductDao;
import com.simp.product.model.vo.PaymentHistory;
import com.simp.product.model.vo.Product;

public class ProductService {
	
	ProductDao productDao = new ProductDao();

	public List<Product> selectProduct() {
		Connection conn = getConnection();
		List<Product> list = productDao.selectProduct(conn);
		close(conn);
		return list;
	}

	public int enrollProduct(Product pd) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = productDao.enrollProduct(conn, pd);
			commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int updateProduct(Product product) {
		Connection conn = getConnection();
		int result = productDao.updateProduct(conn, product);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public int deleteProduct(String delete_pd_name) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = ProductDao.deleteProduct(conn, delete_pd_name);
			if(result == 0)
				throw new IllegalArgumentException("해당 상품이 존재하지 않습니다. : " + delete_pd_name );
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e; //controller가 예외처리를 결정할 수 있도록 넘김.
		} finally {
			close(conn);
		}
		return result;
	}

	public List<PaymentHistory> searchProduct(Map<String, String> param) {
		Connection conn = getConnection();
		List<PaymentHistory> list = null;
		try {
			//1.board update
			list = productDao.searchProduct(conn, param);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		}
		return list;
	}

	public int searchProductCount(Map<String, String> param) {
		Connection conn = getConnection();
		int totalContents = 0;
		try {
			totalContents = productDao.searchProductCount(conn, param);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		}
		return totalContents;
	}

	public int totalPrice(String searchType, String searchKeyword) {
		Connection conn = getConnection();
		int totalPrice = 0;
		try {
			totalPrice = productDao.totalPrice(conn, searchType,searchKeyword);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		}
		return totalPrice;
	}

	

}
