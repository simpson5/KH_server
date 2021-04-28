package com.simp.product.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.simp.product.model.exception.ProductException;
import com.simp.product.model.vo.PaymentHistory;
import com.simp.product.model.vo.Product;



public class ProductDao {
	
	private static Properties prop = new Properties();

	
	public ProductDao() {
		String fileName = this.getClass() // 클래스 객체
				.getResource("/sql/product/product-query.properties") // Url 객체
				.getPath(); // String 객체 : 절대경로
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Product> selectProduct(Connection conn) {
		List<Product> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("selectProduct");

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Product pd = new Product();
				pd.setProductNo(rset.getInt("product_no"));
				pd.setProductName(rset.getString("product_name"));
				pd.setPdCategory(rset.getString("pd_category"));
				pd.setPdImg(rset.getString("pd_img"));
				pd.setProductPrice(rset.getInt("product_price"));
				pd.setStock(rset.getInt("stock"));
				pd.setPdInfo(rset.getString("pd_info"));

				list.add(pd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int enrollProduct(Connection conn, Product pd) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("enrollProduct");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pd.getProductName());
			pstmt.setString(2, pd.getPdCategory());
			pstmt.setString(3, pd.getPdImg());
			pstmt.setInt(4, pd.getProductPrice());
			pstmt.setString(5, pd.getPdInfo());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	//DML : insert, update, delete && DQL : select
	public int updateProduct(Connection conn, Product pd) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateProduct");
		
		try {
			// 미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			// 쿼리문미완성을 완성 시켜줌
			pstmt.setInt(1, pd.getProductPrice());
			pstmt.setInt(2, pd.getStock());
			pstmt.setString(3, pd.getPdInfo());
			pstmt.setInt(4, pd.getProductNo());
			// 쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			// DML은 executeUpdate()
			result = pstmt.executeUpdate();
			System.out.println(result);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public static int deleteProduct(Connection conn, String delete_pd_name) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteProduct");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, delete_pd_name);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ProductException("상품 삭제 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public List<PaymentHistory> searchProduct(Connection conn, Map<String, String> param) {
		List<PaymentHistory> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("searchPagedPayment");
        query = setQuery(query, param.get("searchType"), param.get("searchKeyword"));
		System.out.println("query@dao = " + query);
		try {
			// 미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, param.get("start"));
			pstmt.setString(2, param.get("end"));
			// 쿼리문실행
			rset = pstmt.executeQuery();
			list = new ArrayList<>();
			while (rset.next()) {
				PaymentHistory pay = new PaymentHistory();
				pay.setPaymentNo(rset.getString("payment_no"));
				pay.setMemberId(rset.getString("member_id"));
				pay.setAmount(rset.getInt("amount"));
				pay.setPoint(rset.getInt("point"));
				pay.setPayDate(rset.getDate("pay_date"));
				pay.setPayContent(rset.getString("pay_Content"));
				
				list.add(pay);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	
	public int searchProductCount(Connection conn, Map<String, String> param) {
		int totalContents = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("searchPaymentCount");
		//select count(*) cnt from member M where #
		query = setQuery(query, param.get("searchType"), param.get("searchKeyword"));
		System.out.println("query@dao = " + query);
		try {
			// 미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			// 쿼리문실행
			rset = pstmt.executeQuery();
			if (rset.next()) {
				totalContents = rset.getInt("cnt");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContents;
	}

	public String setQuery(String query, String searchType, String searchKeyword) {
		switch(searchType) {
		case "memberId" 	: query = query.replace("#", " member_id like '%" + searchKeyword + "%'"); break;
		case "payDate" 	: query = query.replace("#", " pay_date like '%" + searchKeyword + "%'"); break;
		case "movieName" : query = query.replace("#", " pay_content like '%" + searchKeyword + "%'"); break;
		
		}
		return query;
	}

	public int totalPrice(Connection conn, String searchType, String searchKeyword) {
		int totalPrice = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("totalPrice");
		// select  sum(amount) from payment_history where # = ?
		query = setQuery(query, searchType, searchKeyword);
		System.out.println("query@dao = " + query);
		try {
			// 미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			// 쿼리문실행
			rset = pstmt.executeQuery();
			if (rset.next()) {
				totalPrice = rset.getInt("A");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalPrice;
	}
}
