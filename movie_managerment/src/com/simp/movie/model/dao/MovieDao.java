package com.simp.movie.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.simp.movie.model.vo.Banner;
import com.simp.movie.model.vo.Movie;
import com.simp.movie.model.vo.ScreenInfo;
import com.simp.movie.model.vo.ScreenMovie;

public class MovieDao {

	private Properties prop = new Properties();

	public MovieDao() {
		// board-query.properties의 내용읽어와서 prop에 저장
		// resources/sql/board-query.properties가 아니라
		// WEB-INF/classes/sql/board-query.properties에 접근해야함.
//		String fileName = this.getClass() // 클래스 객체
//				.getResource("/sql/member/board-query.properties") // Url 객체
//				.getPath(); // String 객체 : 절대경로
		String fileName = MovieDao.class.getResource("/sql/movie/movie-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int enrollMovie(Connection conn, Movie m) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("enrollMovie");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getTitle());
			pstmt.setString(2, m.getImage());
			pstmt.setString(3, m.getPubDate());
			pstmt.setString(4, m.getDirector());
			pstmt.setString(5, m.getActor());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int enrollScreenMovie(Connection conn, ScreenMovie sm) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("enrollScreenMovie");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sm.getMovieTitle());
			pstmt.setString(2, sm.getGenre());
			pstmt.setString(3, sm.getMainPoster());
			pstmt.setString(4, sm.getPoster());
			pstmt.setString(5, sm.getSteelCut());
			pstmt.setString(6, sm.getTrailer());
			pstmt.setString(7, sm.getSynopsis());
			pstmt.setString(8, sm.getScreeningRate());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public String enrollScreen(Connection conn, ScreenInfo si) {
		PreparedStatement pstmt = null;
		int result = 0;
		String msg = "";
		String sql = prop.getProperty("enrollScreen");
		try {
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, si.getMovieTitle());
			pstmt.setString(2, si.getTheaterNo());
			pstmt.setTimestamp(3, si.getStartTime());
			pstmt.setTimestamp(4, si.getEndTime());
			pstmt.setInt(5, totalTicketCnt(conn, si.getTheaterNo()));
			pstmt.setInt(6, si.getScreenPrice());

			// 상영 시간 서순 체크
			if (si.getStartTime().getTime() > si.getEndTime().getTime()) {
				msg = "상영 시작시간이 종료시간 뒤에 있습니다.";
				System.out.println(msg);
				return msg;
			}

			// 상영 등록 시간 겹침 방지
			if (!screenTimeCheck(conn, si.getTheaterNo(), si.getStartTime(), si.getEndTime())) {
				msg = "상영 시갑이 겹칩니다.";
				System.out.println(msg);
				return msg;
			}

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return msg;
	}

	// 상영 영화 조회
	public List<ScreenMovie> selectMovie(Connection conn) {
		List<ScreenMovie> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("selectMovie");

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				ScreenMovie sc = new ScreenMovie();
				sc.setMovieTitle(rset.getString("movie_title"));
				sc.setMainPoster(rset.getString("main_poster"));
//				sc.setPurchaseRate(rset.getDouble("purchase_rate"));
//				sc.setScrennStatus(rset.getString("screnn_status"));

				list.add(sc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	// 영화 중복 검사
	public int checkMovie(Connection conn, Movie m) {
		int cm = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("checkMovie");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getTitle());
			rset = pstmt.executeQuery();
			if (rset.next()) {
				cm = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cm;
	}

	public List<ScreenInfo> selectScreenInfo(Connection conn) {
		List<ScreenInfo> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("selectScreenInfo");

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				ScreenInfo si = new ScreenInfo();
				si.setMovieTitle(rset.getString("movie_title"));
				si.setTheaterNo(rset.getString("theater_no"));
				si.setSoldTicket(rset.getInt("sold_ticket"));
				si.setTotalTicket(rset.getInt("total_ticket"));
				si.setScreenNo(rset.getString("screen_no"));
				si.setStatus(rset.getString("screen_stattus"));

				list.add(si);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public List<ScreenInfo> serachScreenInfo(Connection conn, String title) {
		List<ScreenInfo> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("serachScreenInfo");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + title + "%");
			rset = pstmt.executeQuery();

			while (rset.next()) {
				ScreenInfo si = new ScreenInfo();
				si.setMovieTitle(rset.getString("movie_title"));
				si.setTheaterNo(rset.getString("theater_no"));
				si.setSoldTicket(rset.getInt("sold_ticket"));
				si.setTotalTicket(rset.getInt("total_ticket"));

				list.add(si);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	// 내부에서 만 사용할 메서드 모음
	// 상영관 좌석수 조회
	private int totalTicketCnt(Connection conn, String theaterNo) {
		int totalTicket = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("totalTicketCnt");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, theaterNo.replace("상영관", "T"));
			rset = pstmt.executeQuery();

			if (rset.next()) {
				totalTicket = rset.getInt("cnt");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalTicket;
	}

	// 상영관 시간 겹침 확인
	private boolean screenTimeCheck(Connection conn, String theaterNo, Timestamp startTime, Timestamp endTime) {
		boolean check = false;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("screenTimeCheck");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, theaterNo);
			rset = pstmt.executeQuery();

			// 현재 상영관의 상영이 처음인지 체크하는 변수
			int n = 0;

			while (rset.next()) {
				check = false;
				n++;
				Timestamp startTime2 = rset.getTimestamp("start_time");
				Timestamp endTime2 = rset.getTimestamp("end_time");
				System.out.println(n + "번째");
				System.out.println("등록 시작"+startTime.getTime());
				System.out.println(startTime2.getTime());
				System.out.println("등록 끝"+endTime.getTime());
				System.out.println(endTime2.getTime());
				if (endTime2.getTime() < startTime.getTime() || endTime.getTime() < startTime2.getTime()) {
					check = true;
				}

				if (check == false) {
					return check;
				}
				System.out.println(check);
			}

			if (n == 0) {
				check = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return check;
	}

	public ScreenMovie movieInfo(Connection conn, String movieTitle) {
		ScreenMovie screenmovie = new ScreenMovie();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("movieInfo");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, movieTitle);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				screenmovie.setMovieTitle(rset.getString("movie_title"));
				screenmovie.setMainPoster(rset.getString("main_poster"));
				screenmovie.setPoster(rset.getString("poster"));
				screenmovie.setSteelCut(rset.getString("steel_cut"));
				screenmovie.setTrailer(rset.getString("trailer"));
				screenmovie.setSynopsis(rset.getString("Synopsis"));
				screenmovie.setPurchaseRate(rset.getInt("purchase_rate"));
				screenmovie.setGenre(rset.getString("genre"));
				screenmovie.setScreeningRate(rset.getString("screening_rate"));
				screenmovie.setScrennStatus(rset.getString("screen_status"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return screenmovie;
	}

	// 상영영화정보수정
	public int updateScreenMovie(Connection conn, ScreenMovie sm) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateScreenMovie");

		try {
			// 미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			// 쿼리문미완성을 완성 시켜줌
			pstmt.setString(1, sm.getTrailer());
			pstmt.setString(2, sm.getSynopsis());
			pstmt.setString(3, sm.getScrennStatus());
			pstmt.setString(4, sm.getTitle());

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

	public int ScreenMovieStatus(Connection conn, ScreenInfo si) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("screenMovieStatus");

		try {
			// 미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			// 쿼리문미완성을 완성 시켜줌
			pstmt.setString(1, si.getStatus());
			pstmt.setString(2, si.getScreenNo());

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

	public List<Banner> bannerList(Connection conn) {
		List<Banner> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from banner_poster";

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Banner b = new Banner();
				b.setNo(rset.getInt("no"));
				b.setPoster(rset.getString("poster_name"));

				list.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int bannerUpdate(Connection conn, int no, String poster) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "update banner_poster set poster_name = ? where no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, poster);
			pstmt.setInt(2, no);

			result = pstmt.executeUpdate();
			System.out.println(result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
}
