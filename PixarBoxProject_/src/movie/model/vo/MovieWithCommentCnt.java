package movie.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class MovieWithCommentCnt extends Movie implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int commentCnt;

	public MovieWithCommentCnt() {
		super();
	}
	public MovieWithCommentCnt(int commentCnt) {
		super();
		this.commentCnt = commentCnt;
	}
	public MovieWithCommentCnt(String movieCode, String movieTitle, int runningTime, String director, String actor,
			String description, Date releaseDate, int ticketSales, String originalFileName, String renamedFileName,
			String movieVideo, String genres, String schedule, int commentCnt) {
		super(movieCode, movieTitle, runningTime, director, actor, description, releaseDate, ticketSales,
				originalFileName, renamedFileName, movieVideo, genres, schedule);
		this.commentCnt = commentCnt;
	}
	
	public int getCommentCnt() {
		return commentCnt;
	}

	public void setCommentCnt(int commentCnt) {
		this.commentCnt = commentCnt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "MovieCommentCnt [commentCnt=" + commentCnt + "]";
	}
	
	
	
	
}
