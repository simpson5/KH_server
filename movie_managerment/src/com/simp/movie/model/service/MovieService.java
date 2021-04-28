package com.simp.movie.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.simp.movie.model.dao.MovieDao;
import com.simp.movie.model.vo.Banner;
import com.simp.movie.model.vo.Movie;
import com.simp.movie.model.vo.ScreenInfo;
import com.simp.movie.model.vo.ScreenMovie;

public class MovieService {

	private MovieDao movieDao = new MovieDao();

	// 상영 영화 등록
	public int enrollMovie(Movie m) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = movieDao.enrollMovie(conn, m);
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

	// 상영 영화 추가 정보 등록
	public int enrollScreenMovie(ScreenMovie sm) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = movieDao.enrollScreenMovie(conn, sm);
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

	// 상영 등록
	public String enrollScreen(ScreenInfo si) {
		Connection conn = getConnection();
		String msg = "";
		try {
			msg = movieDao.enrollScreen(conn, si);
			commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return msg;
	}

	// 상영 영화 조회
	public List<ScreenMovie> selectMovie() {
		Connection conn = getConnection();
		List<ScreenMovie> list = movieDao.selectMovie(conn);
		close(conn);
		return list;
	}

	// 영화 중복 검사
	public int checkMovie(Movie m) {
		Connection conn = getConnection();
		int cm = movieDao.checkMovie(conn, m);
		close(conn);
		return cm;
	}

	public List<ScreenInfo> selectScreenInfo() {
		Connection conn = getConnection();
		List<ScreenInfo> list = movieDao.selectScreenInfo(conn);
		close(conn);
		return list;
	}

	public List<ScreenInfo> serachScreenInfo(String title) {
		Connection conn = getConnection();
		List<ScreenInfo> list = movieDao.serachScreenInfo(conn, title);
		close(conn);
		return list;
	}

	public ScreenMovie movieInfo(String movieTitle) {
		Connection conn = getConnection();
		ScreenMovie sm = movieDao.movieInfo(conn, movieTitle);
		// System.out.println("list@service = " + list);
		close(conn);
		return sm;
	}

	// 상영영화정보수정
	public int updateScreenMovie(ScreenMovie screenmovie) {
		Connection conn = getConnection();
		int result = 0;
		String msg = "";
		try {
			result = movieDao.updateScreenMovie(conn, screenmovie);
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

	// 상영관리상태수정
	public int ScreenMovieStatus(ScreenInfo si) {
		Connection conn = getConnection();
		int result = 0;
		String msg = "";
		try {
			result = movieDao.ScreenMovieStatus(conn, si);
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

	public List<Banner> bannerList() {
		Connection conn = getConnection();
		List<Banner> list = movieDao.bannerList(conn);
		close(conn);
		return list;
	}

	public int bannerUpdate(int no, String poster) {
		Connection conn = getConnection();
		int result = 0;
		String msg = "";
		try {
			result = movieDao.bannerUpdate(conn, no, poster);
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

}
