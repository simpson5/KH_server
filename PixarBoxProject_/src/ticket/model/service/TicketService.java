package ticket.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import movie.model.vo.Movie;
import movie.model.vo.MovieNow;
import movie.model.vo.MovieSeat;
import theater.model.vo.Seat;
import ticket.model.dao.TicketDAO;
import ticket.model.vo.Payment;
import ticket.model.vo.Ticketing;

public class TicketService {

	public List<Movie> selectMovieNow() {
		Connection conn = getConnection();
		List<Movie> list = new TicketDAO().selectMovieNow(conn);
		
		close(conn);
		
		return list;
	}

	public List<MovieNow> selectMovieNowDate(String movieCode) {
		Connection conn = getConnection();
		List<MovieNow> list = new TicketDAO().selectMovieNowDate(conn, movieCode);
		
		close(conn);
		
		return list;
	}

	public List<MovieNow> selectMovieNowTime(String movieCode, Date showDate) {
		Connection conn = getConnection();
		List<MovieNow> list = new TicketDAO().selectMovieNowTime(conn, movieCode, showDate);
		
		close(conn);
		
		return list;
	}

	public MovieNow selectMovieNowByShowCode(int showCode) {
		Connection conn = getConnection();
		MovieNow m = new TicketDAO().selectMovieNowByShowCode(conn, showCode);
		
		close(conn);
		
		return m;
	}

	public List<Seat> selectSeatInfo(int screenCode, int showCode) {
		Connection conn = getConnection();
		List<Seat> seatList = new TicketDAO().selectSeatInfo(conn, screenCode, showCode);
		
		close(conn);
		
		return seatList;
	}

	public List<MovieSeat> selectMovieInfo(int showCode) {
		Connection conn = getConnection();
		List<MovieSeat> list = new TicketDAO().selectMovieInfo(conn, showCode);
		
		close(conn);
		
		return list;
	}

	public String selectReservationYN(String string) {
		Connection conn = getConnection();
		String reservationYN = new TicketDAO().selectReservationYN(conn, string);
		close(conn);
		return reservationYN;
	}

	public int insertPayment(Payment p) {
		Connection conn = getConnection();
		int result = new TicketDAO().insertPayment(conn, p);
		close(conn);
		return result;
	}

	public int insertTicketing(Ticketing t) {
		Connection conn = getConnection();
		int result = new TicketDAO().insertTicketing(conn, t);
		close(conn);
		return result;
	}

	public int updateReservationYN_Y(String seatNo, int screenCode, int showCode) {
		Connection conn = getConnection();
		int result = new TicketDAO().updateReservationYN_Y(conn, seatNo, screenCode, showCode);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		
		return result;
	}
	
	public int usePoint(String memberId, int point, String movieTitle, String paymentCode) {
		Connection conn = getConnection();
		int result = new TicketDAO().usePoint(conn, memberId, point, movieTitle, paymentCode);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		
		return result;
	}
	
	public List<Ticketing> selectTicketingListBymemberId(String memberId) {
		Connection conn = getConnection();
		List<Ticketing> list = new TicketDAO().selectTicketingListBymemberId(conn, memberId);
		close(conn);
		return list;
	}

	public List<Ticketing> selectCancelListBymemberId(String memberId) {
		Connection conn = getConnection();
		List<Ticketing> list = new TicketDAO().selectCancelListBymemberId(conn, memberId);
		close(conn);
		return list;
	}

	public int savePoint(String memberId, double savePoint, String movieTitle, String paymentCode) {
		Connection conn = getConnection();
		int result = new TicketDAO().savePoint(conn, memberId, savePoint, movieTitle, paymentCode);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		
		return result;
	}
}
