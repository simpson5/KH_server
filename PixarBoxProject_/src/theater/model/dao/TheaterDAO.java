package theater.model.dao;

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

import movie.model.vo.MovieNow;
import movie.model.vo.MovieSeat;
import ticket.model.dao.TicketDAO;

public class TheaterDAO {
	
	private Properties prop = new Properties();
	
	public TheaterDAO() {
		
		String fileName = TicketDAO.class.getResource("/sql/theater/theater-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<MovieSeat> selectShowTimeList(Connection conn, Date srchDate) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectShowTimeList");
		
		List<MovieSeat> list = new ArrayList<MovieSeat>();
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setDate(1, srchDate);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				MovieSeat m = new MovieSeat();
				
				m.setShowCode(rset.getInt("show_code"));
				m.setMovieCode(rset.getString("movie_code"));
				m.setScreenCode(rset.getInt("screen_code"));
				m.setShowTurn(rset.getString("show_turn"));
				m.setStartTime(rset.getString("start_time"));
				m.setEndTime(rset.getString("end_time"));
				m.setShowDate(rset.getDate("show_date"));
				m.setMovieTitle(rset.getString("movie_title"));
				m.setSeatCnt(rset.getInt("seat_cnt"));
				m.setRemainSeats(rset.getInt("remain_seats"));
				
				list.add(m);
			}
			
//			System.out.println("MovieList@TheaterDAO="+list);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

}
