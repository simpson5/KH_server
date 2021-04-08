package movie.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import movie.model.dao.MovieDAO;
import movie.model.vo.LikeList;
import movie.model.vo.Movie;
import movie.model.vo.MovieComment;
import movie.model.vo.MovieNow;


public class MovieService {



	public int selectMovieCount() {
		Connection conn = getConnection();
		int totalContent = new MovieDAO().selectMovieCount(conn);
		close(conn);
		return totalContent;
	}
	
	public int selectMovieNowCount() {
		Connection conn = getConnection();
		int totalContent = new MovieDAO().selectMovieNowCount(conn);
		
		close(conn);
		
		return totalContent;
	}

	public List<Movie> selectMovieMore(int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Movie> list = new MovieDAO().selectMovieMore(conn, cPage, numPerPage);
		close(conn);
		return list;
	}

	public Movie selectMovie(String movieCode) {
		Connection conn = getConnection();
		Movie movie = new MovieDAO().selectMovie(conn, movieCode);
		
		close(conn);
		
		return movie;
	}

	/*public List<Movie> selectMovieNow(int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Movie> list = new MovieDAO().selectMovieNowMore(conn, cPage, numPerPage);
		
		close(conn);
		
		return list;
	}*/

	public List<MovieNow> selectMovieNow() {
		Connection conn = getConnection();
		List<MovieNow> list = new MovieDAO().selectMovieNow(conn);
		
		close(conn);
		
		return list;
	}

	public List<Movie> selectMovieAll(String searchCategory, String searchKeyword) {
		Connection conn = getConnection();
		List<Movie> list = new MovieDAO().selectMovieAll(conn, searchCategory, searchKeyword);
		
		close(conn);
		
		
		return list;
	}

	public List<Movie> selectMovieByTitle(String searchCategory, String searchKeyword) {
		Connection conn = getConnection();
		List<Movie> list = new MovieDAO().selectMovieByTitle(conn, searchCategory, searchKeyword);
		
		close(conn);
		
		
		return list;
	}

	public List<Movie> selectMovieByDirector(String searchCategory, String searchKeyword) {
		Connection conn = getConnection();
		List<Movie> list = new MovieDAO().selectMovieByDirector(conn, searchCategory, searchKeyword);
		
		close(conn);
		
		
		return list;
	}

	public List<Movie> selectMovieByActor(String searchCategory, String searchKeyword) {
		Connection conn = getConnection();
		List<Movie> list = new MovieDAO().selectMovieByActor(conn, searchCategory, searchKeyword);
		
		close(conn);
		
		
		return list;
	}

	public int insertMovieComment(MovieComment mc) {
		Connection conn = getConnection();
		int result = new MovieDAO().insertMovieComment(conn, mc);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	public List<MovieComment> selectCommentList(String movieCode) {
		Connection conn = getConnection();
		List<MovieComment> commentList = new MovieDAO().selectCommentList(conn, movieCode);
		
		close(conn);
		
		return commentList;
	}

	public int deleteMovieComment(int commentNo) {
		Connection conn = getConnection();
		int result = new MovieDAO().deleteMovieComment(conn, commentNo);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	public int insertMovieLike(LikeList li) {
		Connection conn = getConnection();
		int result = new MovieDAO().insertMovieLike(conn, li);
		//트랜잭션 처리
		if(result>0) {
			commit(conn);
		}
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public List<LikeList> selectAllLikeList(String memberId) {
		Connection conn = getConnection();
		List<LikeList> likeList = new MovieDAO().selectAllLikeList(conn, memberId);
		close(conn);
		return likeList;
	}

	//moviecode의 라이크 중복되게 하는거 방지하기 위한 기능
	public int selectLikeCnt(LikeList li) {
		Connection conn = getConnection();
		int likeCnt = new MovieDAO().selectLikeCnt(conn,li);
		
		close(conn);
		
		return likeCnt;
	}

	public int deleteMovieLike(LikeList li) {
		Connection conn = getConnection();
		int result = new MovieDAO().deleteMovieLike(conn, li);
		//트랜잭션 처리
		if(result>0) {
			commit(conn);
		}
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public List<Movie> selectMovieMoreByYear(int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Movie> list = new MovieDAO().selectMovieMoreByYear(conn, cPage, numPerPage);
		close(conn);
		return list;
	}

	public List<Movie> selectAllMovieLikeListBymemberId(int cPage, int numPerPage, String memberId) {
		Connection conn = getConnection();
		List<Movie> list = new MovieDAO().selectAllMovieLikeListBymemberId(conn, cPage, numPerPage, memberId);
		close(conn);
		return list;
	}

	//내가 선택한 좋아요 총 수 구하는 함수
	public int selectMovieLikeCount(String memberId) {
		Connection conn = getConnection();
		int likeCnt = new MovieDAO().selectMovieLikeCount(conn,memberId);
		
		close(conn);
		
		return likeCnt;
	}

	public LikeList selectMovieLikeOne(String memberId, String movieCode) {
		Connection conn = getConnection();
		LikeList like = new MovieDAO().selectMovieLikeOne(conn, memberId, movieCode);
		close(conn);
		return like;
	}

	public List<Movie> selectAllMovieInfo() {
		Connection conn = getConnection();
		List<Movie> movieList = new MovieDAO().selectAllMovieInfo(conn);
		close(conn);
		return movieList;
	}

	public List<Movie> selectScheduleMovie() {
		Connection conn = getConnection();
		List<Movie> list = new MovieDAO().selectScheduleMovie(conn);
		
		close(conn);
		
		return list;
	}

	public List<MovieNow> selectMovieNowList(String movieCode) {
		Connection conn = getConnection();
		List<MovieNow> movieNowList = new MovieDAO().selectMovieNowList(conn, movieCode);
		
		return movieNowList;
	}

}
