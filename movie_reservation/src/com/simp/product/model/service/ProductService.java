package com.simp.product.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.simp.product.model.dao.ProductDao;
import com.simp.product.model.vo.PaymentHistory;
import com.simp.product.model.vo.Product;
import com.simp.product.model.vo.Shopping;



public class ProductService {
	public static final String PRODUCT_DRINK= "d";
	public static final String PRODUCT_SNACK = "s";
	public static final String PRODUCT_POINT = "p";
	
	private ProductDao productDao = new ProductDao();

	public List<Product> productList() {
		Connection conn = getConnection();
		List<Product> productList = productDao.productList(conn);
		close(conn);
		return productList;
		
	}
	
	public List<Shopping> shoppingList() {
		Connection conn = getConnection();
		List<Shopping> shoppingList = productDao.shoppingList(conn);
		close(conn);
		return shoppingList;
		
	}

	public Product selectProduct(String pd_title) {
		Connection conn = getConnection();
		Product pd = productDao.selectProduct(conn, pd_title);
		close(conn);
		return pd;
	}

	public List<PaymentHistory> payment(String memberId) {
		Connection conn = getConnection();
		List<PaymentHistory> list = productDao.payment(conn, memberId);
		close(conn);
		return list;
	}

	public int amount(String memberId) {
		Connection conn = getConnection();
		int amount = productDao.amount(conn, memberId);
		close(conn);
		return amount;
	}

}
