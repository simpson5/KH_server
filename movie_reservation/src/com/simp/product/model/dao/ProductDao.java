package com.simp.product.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.simp.member.model.vo.Member;
import com.simp.product.model.vo.PaymentHistory;
import com.simp.product.model.vo.Product;
import com.simp.product.model.vo.Shopping;




public class ProductDao {
	private Properties prop = new Properties();
	
	
	public ProductDao() {
		//board-query.properties의 내용읽어와서 prop에 저장
		//resources/sql/board-query.properties가 아니라
		//WEB-INF/classes/sql/board/board-query.properties에 접근해야함.
		String fileName = "/sql/product/product-query.properties";
		String absPath =  ProductDao.class.getResource(fileName).getPath();
		try {
			prop.load(new FileReader(absPath));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public List<Product> productList(Connection conn) {
		List<Product> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("productList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Product product = new Product();
				product.setProductNo(rset.getInt("product_no"));
				product.setPdCategory(rset.getString("pd_category"));
				product.setProductName(rset.getString("product_name"));
				product.setProductPrice(rset.getInt("product_price"));
				product.setStock(rset.getInt("stock"));
				product.setAccumulateSale(rset.getInt("accumulate_sale"));
				product.setPdStatus(rset.getString("pd_status"));
				product.setPdImg(rset.getString("pd_img"));
				product.setPdInfo(rset.getString("pd_info"));
				
				
				
				list.add(product);
				}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	
	}

	public List<Shopping> shoppingList(Connection conn) {
		List<Shopping> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("productList");
		//System.out.println("sql@dao = " + sql);
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Shopping shopping = new Shopping();
				shopping.setProductNo(rset.getInt("product_no"));
				shopping.setPdCategory(rset.getString("pd_category"));
				shopping.setProductName(rset.getString("product_name"));
				shopping.setProductPrice(rset.getInt("product_price"));
				shopping.setStock(rset.getInt("stock"));
				shopping.setAccumulateSale(rset.getInt("accumulate_sale"));
				shopping.setPdStatus(rset.getString("pd_status"));
				shopping.setPdImg(rset.getString("pd_img"));
				shopping.setPdInfo(rset.getString("pd_info"));
				
				
				
				list.add(shopping);
				}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public Product selectProduct(Connection conn, String pd_title) {
		Product pd = new Product();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectProduct");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setNString(1, pd_title);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				pd.setProductNo(rset.getInt("product_no"));
				pd.setPdCategory(rset.getString("pd_category"));
				pd.setProductName(rset.getString("product_name"));
				pd.setProductPrice(rset.getInt("product_price"));
				pd.setStock(rset.getInt("stock"));
				pd.setAccumulateSale(rset.getInt("accumulate_sale"));
				pd.setPdStatus(rset.getString("pd_status"));
				pd.setPdImg(rset.getString("pd_img"));
				pd.setPdInfo(rset.getString("pd_info"));
				}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return pd;
	}

	public List<PaymentHistory> payment(Connection conn, String memberId) {
		List<PaymentHistory> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("payment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();

			while(rset.next()) {
				PaymentHistory pay = new PaymentHistory();
				pay.setPaymentNo(rset.getString("payment_no"));
				pay.setMemberId(rset.getString("member_id"));
				pay.setAmount(rset.getInt("amount"));
				pay.setPoint(rset.getInt("point"));
				pay.setPayDate(rset.getDate("pay_date"));
				pay.setPayContent(rset.getString("pay_Content"));
				
				list.add(pay);
			}
			System.out.println("Dao@pay = " + list);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int amount(Connection conn, String memberId) {
		int amount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select sum(amount) am from payment_history where substr(payment_no,1,1) = 'M' and pay_date between trunc(add_months(sysdate,-2)+1) - to_char(sysdate,'DD') and trunc(last_day(sysdate)) +0.99999421 and member_id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				amount = rset.getInt("am");
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return amount;
	}
}
