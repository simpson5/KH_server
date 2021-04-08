package theater.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import movie.model.vo.MovieNow;
import movie.model.vo.MovieSeat;
import theater.model.dao.TheaterDAO;

public class TheaterService {

	public List<MovieSeat> selectShowTimeList(Date srchDate) {
		Connection conn = getConnection();
		List<MovieSeat> list = new TheaterDAO().selectShowTimeList(conn, srchDate);
		
		close(conn);
		
		return list;
	}

}
