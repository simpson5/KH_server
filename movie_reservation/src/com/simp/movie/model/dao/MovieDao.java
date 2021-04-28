package com.simp.movie.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.simp.movie.model.vo.Banner;
import com.simp.movie.model.vo.ScreenInfo;
import com.simp.movie.model.vo.ScreenMovie;
import com.simp.product.model.vo.Ticket;

public class MovieDao {

	private Properties prop = new Properties();

	public MovieDao() {
//      String drtry = JDBCTemplate.class.getResource("/").getPath();
//      System.out.println("drtry@dao = " + drtry);
//      String filename = JDBCTemplate.class.getResource("/sql/screen_movie-query.properties").getPath();
		String fileName = this.getClass() // 클래스 객체
				.getResource("/sql/movie/screen_movie-query.properties") // Url 객체
				.getPath(); // String 객체 : 절대경로
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<ScreenMovie> selectList(Connection conn) {
		List<ScreenMovie> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("selectList");
		// System.out.println("query@dao = " + query);
		try {
			// 미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			// 쿼리문실행
			rset = pstmt.executeQuery();

			while (rset.next()) {
				ScreenMovie screenmovie = new ScreenMovie();
				screenmovie.setMovieTitle(rset.getString("movie_title"));
				screenmovie.setMainPoster(rset.getString("main_poster"));
				screenmovie.setPoster(rset.getString("poster"));
				screenmovie.setSteelCut(rset.getString("steel_cut"));
				screenmovie.setTrailer(rset.getString("trailer"));
				screenmovie.setSynopsis(rset.getString("Synopsis"));
				screenmovie.setPurchaseRate(rset.getDouble("purchase_rate"));
				screenmovie.setScreeningRate(rset.getString("screening_rate"));
				screenmovie.setScrennStatus(rset.getString("screen_status"));

				list.add(screenmovie);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public ScreenMovie movieInfo(Connection conn, String movieTitle) {
		ScreenMovie screenmovie = new ScreenMovie();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("movieInfo");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + movieTitle + "%");
			rset = pstmt.executeQuery();

			if (rset.next()) {
				screenmovie.setMovieTitle(rset.getString("movie_title"));
				screenmovie.setMainPoster(rset.getString("main_poster"));
				screenmovie.setPoster(rset.getString("poster"));
				screenmovie.setSteelCut(rset.getString("steel_cut"));
				screenmovie.setTrailer(rset.getString("trailer"));
				screenmovie.setSynopsis(rset.getString("Synopsis"));
				screenmovie.setPurchaseRate(rset.getDouble("purchase_rate"));
				screenmovie.setGenre(rset.getString("genre"));
				screenmovie.setScreeningRate(rset.getString("screening_rate"));
				screenmovie.setScrennStatus(rset.getString("screen_status"));
				screenmovie.setDirector(rset.getString("movie_director"));
				screenmovie.setActor(rset.getString("movie_actor"));
				System.out.println(rset.getString("movie_director"));
				System.out.println(rset.getString("movie_actor"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return screenmovie;
	}

	public List<ScreenInfo> selectScreenInfo(Connection conn, String title, Timestamp sqlDate) {
		List<ScreenInfo> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("selectScreenInfo");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setTimestamp(2, sqlDate);
			pstmt.setTimestamp(3, sqlDate);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				ScreenInfo si = new ScreenInfo();
				si.setMovieTitle(rset.getString("movie_title"));
				si.setMainPoster(rset.getString("main_poster"));
				si.setScreenNo(rset.getString("screen_no"));
				si.setTheaterNo(rset.getString("theater_no"));
				si.setStartTime(rset.getTimestamp("start_time"));
				si.setEndTime(rset.getTimestamp("end_time"));

				list.add(si);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public ScreenInfo selectOneScreenInfo(Connection conn, String screen_no) {
		ScreenInfo si = new ScreenInfo();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("selectOneScreenInfo");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, screen_no);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				si.setMovieTitle(rset.getString("movie_title"));
				si.setMainPoster(rset.getString("main_poster"));
				si.setScreenNo(rset.getString("screen_no"));
				si.setTheaterNo(rset.getString("theater_no"));
				si.setStartTime(rset.getTimestamp("start_time"));
				si.setEndTime(rset.getTimestamp("end_time"));
				si.setScreen_price(rset.getInt("screen_price"));
				si.setTotalTicket(rset.getInt("total_ticket"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return si;
	}

	public List<Ticket> selectTicket(Connection conn, String screen_no) {
		List<Ticket> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("selectTicket");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, screen_no);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Ticket tk = new Ticket();
				tk.setTicketNo(rset.getString("ticket_no"));
				tk.setSetNo(rset.getString("seat_no"));
				tk.setTicketStatus(rset.getString("ticket_status"));

				list.add(tk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int soldTicket(Connection conn, String screen_no, List<String> seat_arr, String member_id, int amount,
			int point) {
		CallableStatement cstmt = null;
		int result = 0;
		String sql_seat = "";
		int sql_n = 0;
		for (String str : seat_arr) {
			sql_n++;
			sql_seat += "?";
			if (sql_n != seat_arr.size()) {
				sql_seat += ",";
			}
		}
		String sql = "{call proc_ticket_sold(var_arr(" + sql_seat + "), ?, var_arr(" + sql_seat + "), 'X', ?, ?, ?)}";
		try {
			// conn.createArrayOf("VARCHAR", seat_arr) 죽은기능인가보다

			List<String> tk_list = ticketNo(conn, screen_no, seat_arr.size());
			int idn = 1;
			cstmt = conn.prepareCall(sql);
			for (String tk : tk_list) {
				cstmt.setString(idn++, tk);
			}
			cstmt.setString(idn++, screen_no);
			for (String seat : seat_arr) {
				cstmt.setString(idn++, seat);
			}
			cstmt.setString(idn++, member_id);
			cstmt.setInt(idn++, amount);
			cstmt.setInt(idn++, point);
			result = cstmt.executeUpdate();
			System.out.println("dao@soldTicket : " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(cstmt);
		}
		return result;
	}

	private List<String> ticketNo(Connection conn, String screen_no, int cnt) {
		List<String> ticketNo = new ArrayList<String>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("ticketNo");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, screen_no);
			pstmt.setInt(2, cnt);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				ticketNo.add(rset.getString("ticket_no"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return ticketNo;
	}

	public boolean checkReservation(Connection conn, String memberId, String screen_no) {
		boolean check = true;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("checkReservation");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();

			// 현재 상영관의 상영이 처음인지 체크하는 변수
			while (rset.next()) {
				Timestamp startTime = rset.getTimestamp("start_time");
				Timestamp endTime = rset.getTimestamp("end_time");
				System.out.println(
						"dao@check : " + check + " || new_sn : " + screen_no + "|| old_sn" + rset.getInt("screen_no"));
				if (!screenTimeCheck(conn, screen_no, startTime, endTime)) {
					System.out.println("dao@check : " + false + " || new_sn : " + screen_no + "|| old_sn"
							+ rset.getInt("screen_no"));
					return false;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return check;
	}

	// 상영관 시간 겹침 확인
	private boolean screenTimeCheck(Connection conn, String screen_no, Timestamp startTime, Timestamp endTime) {
		boolean check = false;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("screenTimeCheck");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, screen_no);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				Timestamp startTime2 = rset.getTimestamp("start_time");
				Timestamp endTime2 = rset.getTimestamp("end_time");
				if (endTime2.getTime() < startTime.getTime() || endTime.getTime() < startTime2.getTime()) {
					check = true;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return check;
	}

	public int dipMoive(Connection conn, String memberId, String movieTitle) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("dipMoive");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, movieTitle);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int dipMoiveDelete(Connection conn, String memberId, String movieTitle) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("dipMoiveDelete");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, movieTitle);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public List<String> dipMovieList(Connection conn, String member_id) {
		List<String> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("dipMovieList");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member_id);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				list.add(rset.getString("movie_title"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int storePayment(Connection conn, String pd_name, String member_id, String cnt) {
		CallableStatement cstmt = null;
		int result = 0;
		String sql = "{call proc_product_sold(?, ?, ?, 1)}";
		try {
			cstmt = conn.prepareCall(sql);
			cstmt.setString(1, pd_name);
			cstmt.setString(2, member_id);
			cstmt.setInt(3, Integer.parseInt(cnt));
			result = cstmt.executeUpdate();
			System.out.println("dao@soldTicket : " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(cstmt);
		}
		return result;
	}

	public List<ScreenMovie> topList(Connection conn) {
		List<ScreenMovie> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("topList");
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				ScreenMovie sm = new ScreenMovie();
				sm.setMovieTitle(rset.getNString("movie_title"));
				sm.setMainPoster(rset.getNString("main_poster"));
				sm.setPurchaseRate(rset.getDouble("purchase_rate"));
				list.add(sm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int gender_rate(Connection conn, String movieTitle) {
		int rate = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("gender_rate");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, movieTitle);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				rate = rset.getInt("cnt");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return rate;
	}

	public int audience_cnt(Connection conn, String movieTitle) {
		int audience_cnt = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("audience_cnt");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, movieTitle);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				audience_cnt = rset.getInt("sum");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return audience_cnt;
	}

	public int refund(Connection conn, String pay_no) {
		CallableStatement cstmt = null;
		int result = 0;
		String sql = "{call proc_refund(?)}";
		try {
			cstmt = conn.prepareCall(sql);
			cstmt.setString(1, pay_no);
			result = cstmt.executeUpdate();
			System.out.println("dao@soldTicket : " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(cstmt);
		}
		return result;
	}

	public List<ScreenMovie> dipList(Connection conn, String memberId) {
		List<ScreenMovie> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("dipList");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				ScreenMovie sm = new ScreenMovie();
				sm.setMovieTitle(rset.getString("movie_title"));
				sm.setMainPoster(rset.getString("main_poster"));

				list.add(sm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	// 추천영화
	public List<ScreenMovie> recoList(Connection conn, String memberId, String genre) {
		// 결과
		List<ScreenMovie> list = new ArrayList<>();
		// 추천영화
		Map<ScreenMovie, Integer> recoList = new HashMap<ScreenMovie, Integer>();
		// 상위 영화
		List<String> topList = new ArrayList<>();
		// 찜한 영화
		List<String> dipList = new ArrayList<>();
		// 전체 영화
		List<ScreenMovie> allList = new ArrayList<>();
		// 관심 장르
		String[] genre_arr = genre.split(",");

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		// 상영 영화 가져오기
		String topList_query = prop.getProperty("topList");

		// 찜한 영화 가져오기
		String dipList_query = prop.getProperty("dipList");

		// 전체 영화 목롤
		String allList_query = "select * from screen_movie";
		try {
			pstmt = conn.prepareStatement(allList_query);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				ScreenMovie screenmovie = new ScreenMovie();
				screenmovie.setMovieTitle(rset.getString("movie_title"));
				screenmovie.setMainPoster(rset.getString("main_poster"));
				screenmovie.setPoster(rset.getString("poster"));
				screenmovie.setSteelCut(rset.getString("steel_cut"));
				screenmovie.setTrailer(rset.getString("trailer"));
				screenmovie.setSynopsis(rset.getString("Synopsis"));
				screenmovie.setPurchaseRate(rset.getDouble("purchase_rate"));
				screenmovie.setScreeningRate(rset.getString("screening_rate"));
				screenmovie.setScrennStatus(rset.getString("screen_status"));
				
				System.out.println(screenmovie.getMovieTitle());

				allList.add(screenmovie);
			}

			pstmt = conn.prepareStatement(topList_query);
			rset = pstmt.executeQuery();
			while (rset.next()) {

				topList.add(rset.getNString("movie_title"));
			}
			System.out.println("인기영화DAO" + topList);

			pstmt = conn.prepareStatement(dipList_query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				dipList.add(rset.getString("movie_title"));
			}
			System.out.println("찜한영화DAO" + dipList);
			
			//전체 영화에서 점수를 매긴다.
			for (ScreenMovie sm : allList) {
				int x = 0;
				if (topList.contains(sm.getMovieTitle())) {
					x = 5 - topList.indexOf(sm);
				}
				int y = 0;
				if (dipList.contains(sm.getMovieTitle())) {
					y = 5;
				}
				int z = 0;
				for (String my_genres : genre_arr) {
					if(sm.getGenre() != null) {
						String[] movie_genre_ar = sm.getGenre().split(",");
						for (String movie_genre : movie_genre_ar) {
							if (movie_genre.equals(my_genres)) {
								z++;
							}
						}						
					}
				}
				int point = x + y + z;
				System.out.println(sm.getMovieTitle() + ":" + point + "점" + x + y + z);
				recoList.put(sm, point);
			}
			// 점수고 높은 순으로 내림차순 정렬
			List<ScreenMovie> keySet = new ArrayList<>(recoList.keySet());

			//keySet.sort((o1, o2) -> map.get(o2) - map.get(o1)); ??? 어떻게 돌아가는 거지?
			//람다식으로 구현
			keySet.sort((o1, o2) -> recoList.get(o2) - recoList.get(o1));

			int cnt = 0;
			for (ScreenMovie key : keySet) {
				if (cnt < 5) {
					list.add(key);
					System.out.println("추천영화" + cnt + " : " + key.getMovieTitle() + " | " + recoList.get(key) + "점");
				}
				cnt++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
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

}
