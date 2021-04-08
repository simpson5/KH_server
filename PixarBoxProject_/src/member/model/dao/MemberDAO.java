package member.model.dao;

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

import member.model.vo.Member;
import member.model.vo.PointList;

public class MemberDAO {
	
	private Properties prop = new Properties();
	
	public MemberDAO() {
		//실제 빌드패스에 접근해서 쿼리를 가져옴
		String fileName = MemberDAO.class.getResource("/sql/member/member-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int insertMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertMember");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getPassword());
			pstmt.setString(3, m.getLastName());
			pstmt.setString(4, m.getFirstName());
			pstmt.setString(5, m.getEmail());
			pstmt.setString(6, m.getPhone());
			pstmt.setString(7, m.getInterests());
			pstmt.setString(8, m.getGender());
			pstmt.setInt(9, m.getAge());
			
			//쿼리문실행: 완성된 쿼리를 가지고있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate();
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Member selectOne(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member m = null;
		String query = prop.getProperty("selectOne");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				m = new Member();
				
				m.setMemberId(rset.getString("memberid"));
				m.setPassword(rset.getString("password"));
				m.setLastName(rset.getString("lastname"));
				m.setFirstName(rset.getString("firstname"));
				m.setEmail(rset.getString("email"));
				m.setPhone(rset.getString("phone"));
				m.setInterests(rset.getString("interests"));
				m.setGender(rset.getString("gender"));
				m.setAge(rset.getInt("age"));
				m.setPoint(rset.getInt("point"));
				m.setEnrollDate(rset.getDate("enrolldate"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return m;
	}

	public int updateMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updateMember");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, m.getLastName());
			pstmt.setString(2, m.getFirstName());
			pstmt.setString(3, m.getEmail());
			pstmt.setString(4, m.getPhone());
			pstmt.setString(5, m.getInterests());
			pstmt.setString(6, m.getGender());
			pstmt.setInt(7, m.getAge());
			pstmt.setString(8, m.getMemberId());
			
			result = pstmt.executeUpdate();
			
//			System.out.println("result@memberdao="+result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public int deleteMember(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("deleteMember");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, memberId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public int updatePassword(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updatePassword");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, m.getPassword());
			pstmt.setString(2, m.getMemberId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<PointList> selectAddPointBymemberId(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<PointList> list = new ArrayList<>();
		
		String query = prop.getProperty("selectAddPointBymemberId");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, "적립");
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				PointList p = new PointList();
				p.setPointCode(rset.getInt("pointcode"));
				p.setMemberId(rset.getString("memberId"));
				p.setAmount(rset.getInt("amount"));
				p.setStatus(rset.getString("status"));
				p.setPointDate(rset.getDate("pointdate"));
				p.setMovieTitle(rset.getString("movie_title"));
				list.add(p);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public List<PointList> selectUsePointBymemberId(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<PointList> list = new ArrayList<>();
		String query = prop.getProperty("selectUsePointBymemberId");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, "사용");
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				PointList p = new PointList();
				p.setPointCode(rset.getInt("pointcode"));
				p.setMemberId(rset.getString("memberId"));
				p.setAmount(rset.getInt("amount"));
				p.setStatus(rset.getString("status"));
				p.setPointDate(rset.getDate("pointdate"));
				p.setMovieTitle(rset.getString("movie_title"));
				list.add(p);
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int updateCancelYN_Y(Connection conn, String ticketNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updateCancelYN_Y");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, ticketNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateReservationYN_N(Connection conn, int screenCode, String seatNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updateReservationYN_N");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, screenCode);
			pstmt.setString(2, seatNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deletePayment(Connection conn, String ticketNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("deletePayment");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, ticketNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public PointList selectPointByPaymentCode(Connection conn, String ticketNo, String status) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PointList pl = null;
		String query = prop.getProperty("selectPointByPaymentCode");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, ticketNo);
			pstmt.setString(2, status);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				pl = new PointList();
				pl.setPointCode(rset.getInt("pointcode"));
				pl.setMemberId(rset.getString("memberId"));
				pl.setAmount(rset.getInt("amount"));
				pl.setStatus(rset.getString("status"));
				pl.setPointDate(rset.getDate("pointdate"));
				pl.setMovieTitle(rset.getString("movie_title"));
				pl.setPaymentCode(rset.getString("payment_code"));
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return pl;
	}

}
