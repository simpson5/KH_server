package movie.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import member.model.dao.MemberDAO;
import member.model.vo.Member;
import movie.model.vo.LikeList;
import movie.model.vo.Movie;
import movie.model.vo.MovieComment;
import movie.model.vo.MovieNow;
import movie.model.vo.MovieWithCommentCnt;


public class MovieDAO {
	
	private Properties prop = new Properties();
	
	public MovieDAO() {
		
		//실제 빌드패스에 접근해서 쿼리를 가져옴
		String fileName = MemberDAO.class.getResource("/sql/movie/movie-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public int selectMovieCount(Connection conn) {
	
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalContent = 0;
		String query = prop.getProperty("selectMovieCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next())
				totalContent = rset.getInt("cnt");
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContent;
	}
	
	public int selectMovieNowCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalContent = 0;
		String query = prop.getProperty("selectMovieNowCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			
			if(rset.next())
				totalContent = rset.getInt("cnt");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return totalContent;
	}

	public List<Movie> selectMovieMore(Connection conn, int cPage, int numPerPage) {
		
		
		List<Movie> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectMovieMore");
		
		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);
			
			//시작 rownum과 마지막 rownum 구하는 공식
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			
			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			while(rset.next()){
				Movie m = new Movie();
				
				m.setMovieCode(rset.getString("movie_code"));
				m.setMovieTitle(rset.getString("movie_title"));
				m.setRunningTime(rset.getInt("runningtime"));
				m.setDirector(rset.getString("director"));
				m.setActor(rset.getString("actor"));
				m.setDescription(rset.getString("description"));
				m.setReleaseDate(rset.getDate("release_date"));
				m.setTicketSales(rset.getInt("ticket_sales"));
//				m.setMovieImg(rset.getString("movie_img"));
				m.setOriginalFileName(rset.getString("originalFileName"));
				m.setRenamedFileName(rset.getString("renamedFileName"));
				m.setMovieVideo(rset.getString("movie_video"));
				m.setGenres(rset.getString("genres"));
				m.setSchedule(rset.getString("schedule"));
				
				list.add(m);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return list;
	}


	public Movie selectMovie(Connection conn, String movieCode) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		MovieWithCommentCnt movie = new MovieWithCommentCnt();
		String query = prop.getProperty("selectMovie");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, movieCode);
			pstmt.setString(2, movieCode);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				movie.setMovieCode(rset.getString("movie_code"));
				movie.setMovieTitle(rset.getString("movie_title"));
				movie.setRunningTime(rset.getInt("runningtime"));
				movie.setDirector(rset.getString("director"));
				movie.setActor(rset.getString("actor"));
				movie.setDescription(rset.getString("description"));
				movie.setReleaseDate(rset.getDate("release_date"));
				movie.setTicketSales(rset.getInt("ticket_sales"));
//				movie.setMovieImg(rset.getString("movie_img"));
				movie.setOriginalFileName(rset.getString("originalFileName"));
				movie.setRenamedFileName(rset.getString("renamedFileName"));
				movie.setMovieVideo(rset.getString("movie_video"));
				movie.setGenres(rset.getString("genres"));
				movie.setSchedule(rset.getString("schedule"));
				//댓글수
				movie.setCommentCnt(rset.getInt("comment_cnt"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return movie;
	}

	
public int insertMovieLike(Connection conn, LikeList li) {
		
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertMovieLike");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, li.getMemberId());
			pstmt.setString(2, li.getMovieCode());
			
			
			result = pstmt.executeUpdate();
			
			
//			System.out.println("insertMovieLike_Result@Moviedao="+result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
		
		
	}


	public List<LikeList> selectAllLikeList(Connection conn, String memberId) {
		List<LikeList> likeList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectAllLikeList");
		
		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, memberId);
			
			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			while(rset.next()){
				
				LikeList li = new LikeList();
				
				li.setLikeNo(rset.getInt("like_no"));
				li.setMemberId(rset.getString("memberid"));
				li.setMovieCode(rset.getString("movie_code"));
				
				likeList.add(li);
			}
//			System.out.println("movieDAO@likeList="+likeList);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return likeList;
	}


	public int selectLikeCnt(Connection conn, LikeList li) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int likeCnt = 0;
		String query = prop.getProperty("selectLikeCnt");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, li.getMemberId());
			pstmt.setString(2, li.getMovieCode());
			
			rset = pstmt.executeQuery();
			
			if(rset.next())
				likeCnt = rset.getInt("cnt");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return likeCnt;
	}


	public int deleteMovieLike(Connection conn, LikeList li) {
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteMovieLike");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, li.getMemberId());
			pstmt.setString(2, li.getMovieCode());
			
			
			result = pstmt.executeUpdate();
			
			
//			System.out.println("deleteMovieLikeServlet@Moviedao="+result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}


	public List<Movie> selectMovieMoreByYear(Connection conn, int cPage, int numPerPage) {
		List<Movie> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectMovieMoreByYear");
		
		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);
			
			//시작 rownum과 마지막 rownum 구하는 공식
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			
			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			while(rset.next()){
				Movie m = new Movie();
				
				m.setMovieCode(rset.getString("movie_code"));
				m.setMovieTitle(rset.getString("movie_title"));
				m.setRunningTime(rset.getInt("runningtime"));
				m.setDirector(rset.getString("director"));
				m.setActor(rset.getString("actor"));
				m.setDescription(rset.getString("description"));
				m.setReleaseDate(rset.getDate("release_date"));
				m.setTicketSales(rset.getInt("ticket_sales"));
				m.setOriginalFileName(rset.getString("originalFileName"));
				m.setRenamedFileName(rset.getString("renamedFileName"));
				m.setMovieVideo(rset.getString("movie_video"));
				m.setGenres(rset.getString("genres"));
				m.setSchedule(rset.getString("schedule"));
				
				list.add(m);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return list;
	}


	public List<Movie> selectAllMovieLikeListBymemberId(Connection conn, int cPage, int numPerPage, String memberId) {
		List<Movie> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectAllMovieLikeListBymemberId");
		
		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);
			
			//시작 rownum과 마지막 rownum 구하는 공식

			pstmt.setString(1, memberId);
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			
			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			while(rset.next()){
				Movie m = new Movie();
				
				m.setMovieCode(rset.getString("movie_code"));
				m.setMovieTitle(rset.getString("movie_title"));
				m.setRunningTime(rset.getInt("runningtime"));
				m.setDirector(rset.getString("director"));
				m.setActor(rset.getString("actor"));
				m.setDescription(rset.getString("description"));
				m.setReleaseDate(rset.getDate("release_date"));
				m.setTicketSales(rset.getInt("ticket_sales"));
				m.setOriginalFileName(rset.getString("originalFileName"));
				m.setRenamedFileName(rset.getString("renamedFileName"));
				m.setMovieVideo(rset.getString("movie_video"));
				m.setGenres(rset.getString("genres"));
				m.setSchedule(rset.getString("schedule"));
				
				list.add(m);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return list;
	}


	public int selectMovieLikeCount(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int likeCnt = 0;
		String query = prop.getProperty("selectMovieLikeCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next())
				likeCnt = rset.getInt("cnt");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return likeCnt;
	}
	

	public List<MovieNow> selectMovieNow(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMovieNow");
		List<MovieNow> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				MovieNow m = new MovieNow();
				
				m.setMovieCode(rset.getString("movie_code"));
				m.setMovieTitle(rset.getString("movie_title"));
				m.setRunningTime(rset.getInt("runningtime"));
				m.setDirector(rset.getString("director"));
				m.setActor(rset.getString("actor"));
				m.setDescription(rset.getString("description"));
				m.setReleaseDate(rset.getDate("release_date"));
				m.setTicketSales(rset.getInt("ticket_sales"));
//				m.setMovieImg(rset.getString("movie_img"));
				m.setOriginalFileName(rset.getString("originalFileName"));
				m.setRenamedFileName(rset.getString("renamedFileName"));
				m.setMovieVideo(rset.getString("movie_video"));
				m.setGenres(rset.getString("genres"));
				m.setSchedule(rset.getString("schedule"));
//				m.setShowCode(rset.getInt("show_code"));
//				m.setScreenCode(rset.getInt("screen_code"));
//				m.setShowTurn(rset.getString("show_turn"));
//				m.setStartTime(rset.getString("start_time"));
//				m.setEndTime(rset.getString("end_time"));
//				m.setShowDate(rset.getDate("show_date"));
				
				list.add(m);
			}
			
//			System.out.println("list@MovieDAO="+list);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return list;
	}


	public List<Movie> selectMovieAll(Connection conn, String searchCategory, String searchKeyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMovieAll"); 
		List<Movie> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, "%"+searchKeyword+"%");
			pstmt.setString(2, "%"+searchKeyword+"%");
			pstmt.setString(3, "%"+searchKeyword+"%");
			
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
//				m.setMovieImg(rset.getString("movie_img"));
				m.setOriginalFileName(rset.getString("originalFileName"));
				m.setRenamedFileName(rset.getString("renamedFileName"));
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
		
//		System.out.println("movieList@movieDAO="+list);
		
		
		return list;
	}


	public List<Movie> selectMovieByTitle(Connection conn, String searchCategory, String searchKeyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMovieByTitle"); 
		List<Movie> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, "%"+searchKeyword+"%");
			
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
//				m.setMovieImg(rset.getString("movie_img"));
				m.setOriginalFileName(rset.getString("originalFileName"));
				m.setRenamedFileName(rset.getString("renamedFileName"));
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
		
//		System.out.println("movieList@movieDAO="+list);
		
		return list;
	}


	public List<Movie> selectMovieByDirector(Connection conn, String searchCategory, String searchKeyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMovieByDirector"); 
		
		List<Movie> list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, "%"+searchKeyword+"%");
			
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
//				m.setMovieImg(rset.getString("movie_img"));
				m.setOriginalFileName(rset.getString("originalFileName"));
				m.setRenamedFileName(rset.getString("renamedFileName"));
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
		
//		System.out.println("movieList@movieDAO="+list);
		
		return list;
	}


	public List<Movie> selectMovieByActor(Connection conn, String searchCategory, String searchKeyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMovieByActor"); 
		
		List<Movie> list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, "%"+searchKeyword+"%");
			
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
//				m.setMovieImg(rset.getString("movie_img"));
				m.setOriginalFileName(rset.getString("originalFileName"));
				m.setRenamedFileName(rset.getString("renamedFileName"));
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
		
//		System.out.println("movieList@movieDAO="+list);
		
		return list;
	}


	public int insertMovieComment(Connection conn, MovieComment mc) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertMovieComment");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, mc.getCommentLevel());
			pstmt.setString(2, mc.getCommentWriter());
			pstmt.setString(3, mc.getCommentContent());
			pstmt.setString(4, mc.getMovieRef());
			pstmt.setString(5, mc.getCommentRef()==0?null:String.valueOf(mc.getCommentRef()));
			
			result = pstmt.executeUpdate();
			
//			System.out.println("result@dao="+result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}


	public List<MovieComment> selectCommentList(Connection conn, String movieCode) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<MovieComment> commentList = new ArrayList<>();
		String query = prop.getProperty("selectCommentList");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, movieCode);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				MovieComment mc = new MovieComment();
				
				mc.setCommentNo(rset.getInt("comment_no"));
				mc.setCommentLevel(rset.getInt("comment_level"));
				mc.setCommentWriter(rset.getString("comment_writer"));
				mc.setCommentContent(rset.getString("comment_content"));
				mc.setMovieRef(rset.getString("movie_ref"));
				mc.setCommentRef(rset.getInt("comment_ref"));
				mc.setCommentDate(rset.getDate("comment_date"));
				
				commentList.add(mc);
			}
			
//			System.out.println("commentList@dao="+commentList);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return commentList;
	}


	public int deleteMovieComment(Connection conn, int commentNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("deleteMovieComment");
		
//		System.out.println("commentNo@dao="+commentNo);
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, commentNo);
			
			result = pstmt.executeUpdate();
			
//			System.out.println("result@dao="+result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}


	public LikeList selectMovieLikeOne(Connection conn, String memberId, String movieCode) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		LikeList like = new LikeList();
		String query = prop.getProperty("selectMovieLikeOne");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, memberId);
			pstmt.setString(2, movieCode);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				like.setLikeNo(rset.getInt("like_no"));
				like.setMemberId(rset.getString("memberid"));
				like.setMovieCode(rset.getString("movie_code"));
			
			}
			
//			System.out.println("commentList@dao="+commentList);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return like;
	}


	public List<Movie> selectAllMovieInfo(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Movie> movieList = new ArrayList<>();
		String query = prop.getProperty("selectAllMovieInfo");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				Movie m = new Movie();
				
				m.setMovieCode(rset.getString("movie_code"));
				m.setMovieTitle(rset.getString("movie_title"));
				m.setRunningTime(rset.getInt("runningtime"));
				m.setDirector(rset.getString("director"));
				m.setActor(rset.getString("actor"));
				m.setDescription(rset.getString("description"));
				m.setReleaseDate(rset.getDate("release_date"));
				m.setTicketSales(rset.getInt("ticket_sales"));
//				m.setMovieImg(rset.getString("movie_img"));
				m.setOriginalFileName(rset.getString("originalFileName"));
				m.setRenamedFileName(rset.getString("renamedFileName"));
				m.setMovieVideo(rset.getString("movie_video"));
				m.setGenres(rset.getString("genres"));
				m.setSchedule(rset.getString("schedule"));
				
				movieList.add(m);
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return movieList;
	}


	public List<Movie> selectScheduleMovie(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectScheduleMovie");
		List<Movie> list = new ArrayList<>();
		
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
//				m.setMovieImg(rset.getString("movie_img"));
				m.setOriginalFileName(rset.getString("originalFileName"));
				m.setRenamedFileName(rset.getString("renamedFileName"));
				m.setMovieVideo(rset.getString("movie_video"));
				m.setGenres(rset.getString("genres"));
				m.setSchedule(rset.getString("schedule"));
//				m.setShowCode(rset.getInt("show_code"));
//				m.setScreenCode(rset.getInt("screen_code"));
//				m.setShowTurn(rset.getString("show_turn"));
//				m.setStartTime(rset.getString("start_time"));
//				m.setEndTime(rset.getString("end_time"));
//				m.setShowDate(rset.getDate("show_date"));
				
				list.add(m);
			}
			
//			System.out.println("list@MovieDAO="+list);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return list;
	}


	public List<MovieNow> selectMovieNowList(Connection conn, String movieCode) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMovieNowList");
		
		List<MovieNow> movieNowList = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				MovieNow m = new MovieNow();
				
				m.setMovieCode(rset.getString("movie_code"));
				m.setMovieTitle(rset.getString("movie_title"));
				m.setRunningTime(rset.getInt("runningtime"));
				m.setDirector(rset.getString("director"));
				m.setActor(rset.getString("actor"));
				m.setDescription(rset.getString("description"));
				m.setReleaseDate(rset.getDate("release_date"));
				m.setTicketSales(rset.getInt("ticket_sales"));
				m.setOriginalFileName(rset.getString("originalFileName"));
				m.setRenamedFileName(rset.getString("renamedFileName"));
				m.setMovieVideo(rset.getString("movie_video"));
				m.setGenres(rset.getString("genres"));
				m.setSchedule(rset.getString("schedule"));
//				m.setShowCode(rset.getInt("show_code"));
//				m.setScreenCode(rset.getInt("screen_code"));
//				m.setShowTurn(rset.getString("show_turn"));
//				m.setStartTime(rset.getString("start_time"));
//				m.setEndTime(rset.getString("end_time"));
//				m.setShowDate(rset.getDate("show_date"));
				//댓글수
				m.setCommentCnt(rset.getInt("comment_cnt"));
				
				movieNowList.add(m);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return movieNowList;
	}


}
