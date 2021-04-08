package ticket.model.dao;

import static common.JDBCTemplate.*;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import movie.model.vo.Movie;
import movie.model.vo.MovieNow;
import movie.model.vo.MovieSeat;
import theater.model.vo.Seat;
import ticket.model.vo.Payment;
import ticket.model.vo.Ticketing;

public class TicketDAO {
	
	private Properties prop = new Properties();
	
	public TicketDAO() {
		
		String fileName = TicketDAO.class.getResource("/sql/ticket/ticket-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Movie> selectMovieNow(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMovieNow");
		
		List<Movie> list = new ArrayList<Movie>();
		
		try {
			pstmt = conn.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Movie m = new Movie();
				
				m.setMovieCode(rset.getString("movie_code"));
				m.setMovieTitle(rset.getString("movie_title"));
				m.setRunningTime(rset.getInt("runningtime"));
				m.setDirector(rset.getString("director"));
				m.setActor(rset.getString("actor"));
				m.setDescription(rset.getString("description"));
				m.setReleaseDate(rset.getDate("release_date"));
				m.setTicketSales(rset.getInt("ticket_sales"));
				m.setOriginalFileName(rset.getString("originalfilename"));
				m.setRenamedFileName(rset.getString("renamedfilename"));
				m.setMovieVideo(rset.getString("movie_video"));
				m.setGenres(rset.getString("genres"));
				m.setSchedule(rset.getString("schedule"));
				
				list.add(m);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public List<MovieNow> selectMovieNowDate(Connection conn, String movieCode) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMovieNowDate");
		
		List<MovieNow> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, movieCode);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				MovieNow m = new MovieNow();
				
//				m.setMovieCode(rset.getString("movie_code"));
//				m.setMovieTitle(rset.getString("movie_title"));
//				m.setRunningTime(rset.getInt("runningtime"));
//				m.setDirector(rset.getString("director"));
//				m.setActor(rset.getString("actor"));
//				m.setDescription(rset.getString("description"));
//				m.setReleaseDate(rset.getDate("release_date"));
//				m.setTicketSales(rset.getInt("ticket_sales"));
//				m.setOriginalFileName(rset.getString("originalfilename"));
//				m.setRenamedFileName(rset.getString("renamedfilename"));
//				m.setMovieVideo(rset.getString("movie_video"));
//				m.setGenres(rset.getString("genres"));
//				m.setSchedule(rset.getString("schedule"));
//				m.setShowCode(rset.getInt("show_code"));
//				m.setScreenCode(rset.getString("screen_code"));
//				m.setShowTurn(rset.getString("show_turn"));
//				m.setStartTime(rset.getString("start_time"));
//				m.setEndTime(rset.getString("end_time"));
				m.setShowDate(rset.getDate("show_date"));
				
				list.add(m);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public List<MovieNow> selectMovieNowTime(Connection conn, String movieCode, Date showDate) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMovieNowTime");
		
		List<MovieNow> list = new ArrayList<>();
		
//		System.out.println("showDate@ticketDAO="+showDate);
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, movieCode);
			pstmt.setDate(2, showDate);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				MovieNow m = new MovieNow();
				
				m.setMovieCode(rset.getString("movie_code"));
//				m.setMovieTitle(rset.getString("movie_title"));
//				m.setRunningTime(rset.getInt("runningtime"));
//				m.setDirector(rset.getString("director"));
//				m.setActor(rset.getString("actor"));
//				m.setDescription(rset.getString("description"));
//				m.setReleaseDate(rset.getDate("release_date"));
//				m.setTicketSales(rset.getInt("ticket_sales"));
//				m.setOriginalFileName(rset.getString("originalfilename"));
//				m.setRenamedFileName(rset.getString("renamedfilename"));
//				m.setMovieVideo(rset.getString("movie_video"));
//				m.setGenres(rset.getString("genres"));
//				m.setSchedule(rset.getString("schedule"));
				m.setShowCode(rset.getInt("show_code"));
				m.setScreenCode(rset.getInt("screen_code"));
				m.setShowTurn(rset.getString("show_turn"));
				m.setStartTime(rset.getString("start_time"));
				m.setEndTime(rset.getString("end_time"));
				m.setShowDate(rset.getDate("show_date"));
				
				list.add(m);
				
			}
			
//			System.out.println("list@ticketDAO="+list);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public MovieNow selectMovieNowByShowCode(Connection conn, int showCode) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMovieNowByShowCode");
		MovieNow m = new MovieNow();
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, showCode);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				m.setShowCode(rset.getInt("show_code"));
				m.setScreenCode(rset.getInt("screen_code"));
				m.setShowTurn(rset.getString("show_turn"));
				m.setStartTime(rset.getString("start_time"));
				m.setEndTime(rset.getString("end_time"));
				m.setShowDate(rset.getDate("show_date"));				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return m;
	}

	public List<Seat> selectSeatInfo(Connection conn, int screenCode, int showCode) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectSeatInfo");
		
		List<Seat> seatList = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, screenCode);
			pstmt.setInt(2, showCode);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Seat s = new Seat();
				
				s.setSeatNo(rset.getString("seat_no"));
				s.setScreenCode(rset.getInt("screen_code"));
				s.setReservationYN(rset.getString("reservation_yn"));
				s.setShowCode(rset.getInt("show_code"));
				
				seatList.add(s);
			}
			
//			System.out.println("seatList@ticketDAO="+seatList);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return seatList;
	}

	public List<MovieSeat> selectMovieInfo(Connection conn, int showCode) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMovieInfo");
		
		List<MovieSeat> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, showCode);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				MovieSeat m = new MovieSeat();
				
				m.setMovieTitle(rset.getString("movie_title"));
				m.setRenamedFileName(rset.getString("renamedfilename"));
				m.setShowCode(rset.getInt("show_code"));
				m.setShowTurn(rset.getString("show_turn"));
				m.setScreenCode(rset.getInt("screen_code"));
				m.setStartTime(rset.getString("start_time"));
				m.setEndTime(rset.getString("end_time"));
				m.setMovieCode(rset.getString("movie_code"));
				m.setShowDate(rset.getDate("show_date"));
				m.setSeatCnt(rset.getInt("seat_cnt"));
				m.setRemainSeats(rset.getInt("remain_seats"));
				
				list.add(m);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public String selectReservationYN(Connection conn, String string) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String reservationYN = null;
		
		String query = prop.getProperty("selectReservationYN");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, string);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				reservationYN = rset.getString("reservation_yn");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return reservationYN;
	}

	public int insertPayment(Connection conn, Payment p) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertPayment");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, p.getPaymentCode());
			pstmt.setString(2, p.getMemberId());
			pstmt.setString(3, p.getPaymentMethod());
			pstmt.setInt(4, p.getPaymentPrice());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int insertTicketing(Connection conn, Ticketing t) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertTicketing");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, t.getMovieTitle());
			pstmt.setString(2, t.getPaymentCode());
			pstmt.setString(3, t.getMemberId());
			pstmt.setString(4, t.getSeatNo());
			pstmt.setInt(5, t.getScreenCode());
			pstmt.setString(6, t.getStartTime());
			pstmt.setString(7, t.getEndTime());
			pstmt.setInt(8, t.getPaymentPrice());
			pstmt.setDate(9, t.getShowDate());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateReservationYN_Y(Connection conn, String seatNo, int screenCode, int showCode) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateReservationYN_Y");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, seatNo);
			pstmt.setInt(2, screenCode);
			pstmt.setInt(3, showCode);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	public int usePoint(Connection conn, String memberId, int point, String movieTitle, String paymentCode) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("usePoint");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setInt(2, point);
			pstmt.setString(3, movieTitle);
			pstmt.setString(4, paymentCode);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		
		return result;
	}

	public List<Ticketing> selectTicketingListBymemberId(Connection conn, String memberId) {
		List<Ticketing> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectTicketingListBymemberId");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Ticketing t = new Ticketing();
				
				t.setTickeNo(rset.getInt("ticket_no"));
				t.setMovieTitle(rset.getString("movie_title"));
				t.setPaymentCode(rset.getString("payment_code"));
				t.setMemberId(rset.getString("member_id"));
				t.setSeatNo(rset.getString("seat_no"));
				t.setScreenCode(rset.getInt("screen_code"));
				t.setStartTime(rset.getString("start_time"));
				t.setEndTime(rset.getString("end_time"));
				t.setTicketingDate(rset.getString("ticketing_date"));
				t.setCancelYN(rset.getString("cancel_yn"));
				t.setCancelDate(rset.getString("cancel_date"));
				t.setPaymentPrice(rset.getInt("payment_price"));
				t.setShowDate(rset.getDate("show_date"));
				
				list.add(t);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public List<Ticketing> selectCancelListBymemberId(Connection conn, String memberId) {
		List<Ticketing> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectCancelListBymemberId");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Ticketing t = new Ticketing();
				
				t.setTickeNo(rset.getInt("ticket_no"));
				t.setMovieTitle(rset.getString("movie_title"));
				t.setPaymentCode(rset.getString("payment_code"));
				t.setMemberId(rset.getString("member_id"));
				t.setSeatNo(rset.getString("seat_no"));
				t.setScreenCode(rset.getInt("screen_code"));
				t.setStartTime(rset.getString("start_time"));
				t.setEndTime(rset.getString("end_time"));
				t.setTicketingDate(rset.getString("ticketing_date"));
				t.setCancelYN(rset.getString("cancel_yn"));
				t.setCancelDate(rset.getString("cancel_date"));
				t.setPaymentPrice(rset.getInt("payment_price"));
				t.setShowDate(rset.getDate("show_date"));
				
				list.add(t);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int savePoint(Connection conn, String memberId, double savePoint, String movieTitle, String paymentCode) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("savePoint");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setDouble(2, savePoint);
			pstmt.setString(3, movieTitle);
			pstmt.setString(4, paymentCode);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		
		return result;
	}	
}
