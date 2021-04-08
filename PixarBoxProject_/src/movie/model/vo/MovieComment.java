package movie.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class MovieComment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int commentNo;
	private int commentLevel;
	private String commentWriter;
	private String commentContent;
	private String movieRef;
	private int commentRef;
	private Date commentDate;
	
	public MovieComment() {
		super();
	}
	public MovieComment(int commentNo, int commentLevel, String commentWriter, String commentContent, String movieRef,
			int commentRef, Date commentDate) {
		super();
		this.commentNo = commentNo;
		this.commentLevel = commentLevel;
		this.commentWriter = commentWriter;
		this.commentContent = commentContent;
		this.movieRef = movieRef;
		this.commentRef = commentRef;
		this.commentDate = commentDate;
	}
	
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public int getCommentLevel() {
		return commentLevel;
	}
	public void setCommentLevel(int commentLevel) {
		this.commentLevel = commentLevel;
	}
	public String getCommentWriter() {
		return commentWriter;
	}
	public void setCommentWriter(String commentWriter) {
		this.commentWriter = commentWriter;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getMovieRef() {
		return movieRef;
	}
	public void setMovieRef(String movieRef) {
		this.movieRef = movieRef;
	}
	public int getCommentRef() {
		return commentRef;
	}
	public void setCommentRef(int commentRef) {
		this.commentRef = commentRef;
	}
	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "MovieComment [commentNo=" + commentNo + ", commentLevel=" + commentLevel + ", commentWriter="
				+ commentWriter + ", commentContent=" + commentContent + ", movieRef=" + movieRef + ", commentRef="
				+ commentRef + ", commentDate=" + commentDate + "]";
	}
	
	

}
