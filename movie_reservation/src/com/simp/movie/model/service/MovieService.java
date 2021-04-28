package com.simp.movie.model.service;


import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;

import com.simp.movie.model.dao.MovieDao;
import com.simp.movie.model.vo.Banner;
import com.simp.movie.model.vo.ScreenInfo;
import com.simp.movie.model.vo.ScreenMovie;
import com.simp.product.model.vo.Ticket;

public class MovieService {
	
	private MovieDao movieDao = new MovieDao();

	public List<ScreenMovie> selectList() {
		Connection conn = getConnection();
		List<ScreenMovie> list = movieDao.selectList(conn);
		//System.out.println("list@service = " + list);
		close(conn);
		return list;
	}

	public ScreenMovie movieInfo(String movieTitle) {
		Connection conn = getConnection();
		ScreenMovie sm = movieDao.movieInfo(conn, movieTitle);
		//System.out.println("list@service = " + list);
		close(conn);
		return sm;
	}

	public List<ScreenInfo> selectScreenInfo(String title, Timestamp sqlDate) {
		Connection conn = getConnection();
		List<ScreenInfo> list = movieDao.selectScreenInfo(conn, title, sqlDate);
		//System.out.println("list@service = " + list);
		close(conn);
		return list;
	}

	public ScreenInfo selectOneScreenInfo(String screen_no) {
		Connection conn = getConnection();
		ScreenInfo si = movieDao.selectOneScreenInfo(conn, screen_no);
		close(conn);
		return si;
	}

	public List<Ticket> selectTicket(String screen_no) {
		Connection conn = getConnection();
		List<Ticket> list = movieDao.selectTicket(conn, screen_no);
		//System.out.println("list@service = " + list);
		close(conn);
		return list;
	}

	public int soldTicket(String screen_no, List<String> seat_arr, String member_id, int amount, int point) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = movieDao.soldTicket(conn, screen_no, seat_arr, member_id, amount, point);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {			
			close(conn);
		}
		return result;
	}

	public boolean checkReservation(String memberId, String screen_no) {
		Connection conn = getConnection();
		boolean check = movieDao.checkReservation(conn, memberId, screen_no);
		//System.out.println("list@service = " + list);
		close(conn);
		return check;
	}

	public int dipMoive(String memberId, String movieTitle) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = movieDao.dipMoive(conn, memberId, movieTitle);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {			
			close(conn);
		}
		return result;
	}

	public int dipMoiveDelete(String memberId, String movieTitle) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = movieDao.dipMoiveDelete(conn, memberId, movieTitle);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {			
			close(conn);
		}
		return result;
	}

	public List<String> dipMovieList(String member_id) {
		Connection conn = getConnection();
		List<String> list = movieDao.dipMovieList(conn, member_id);
		close(conn);
		return list;
	}

	public int storePayment(String member_id, String pd_name, String cnt) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = movieDao.storePayment(conn, pd_name, member_id, cnt);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {			
			close(conn);
		}
		return result;
	}
	
	public List<ScreenMovie> topList(){
		Connection conn = getConnection();
		List<ScreenMovie> list = movieDao.topList(conn);
		close(conn);
		return list;
	}

	public int gender_rate(String movieTitle) {
		Connection conn = getConnection();
		int rate = movieDao.gender_rate(conn, movieTitle);
		close(conn);
		return rate;
	}

	public int audience_cnt(String movieTitle) {
		Connection conn = getConnection();
		int audience_cnt = movieDao.audience_cnt(conn, movieTitle);
		close(conn);
		return audience_cnt;
	}

	public int refund(String pay_no) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = movieDao.refund(conn, pay_no);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {			
			close(conn);
		}
		return result;
	}

	
	public List<ScreenMovie> dipList(String memberId) {
		Connection conn = getConnection();
		List<ScreenMovie> list = movieDao.dipList(conn, memberId);
		close(conn);
		return list;	
	}
	
	public List<ScreenMovie> recoList(String memberId, String genre) {
		Connection conn = getConnection();
		List<ScreenMovie> list = movieDao.recoList(conn, memberId, genre);
		close(conn);
		return list;	
	}
	
	public List<Banner> bannerList() {
		Connection conn = getConnection();
		List<Banner> list = movieDao.bannerList(conn);
		close(conn);
		return list;
	}
}
