package com.simp.board.model.vo;

import java.sql.Date;

public class ReviewComment {
	private int commentNo;//댓글번호
	private String commentWriter;//아이디
	private int reviewNo;//리뷰번호
	private int commentRef;//댓글 참조번호
	private String content;//댓글내용
	private Date regDate;//작성날짜
	private String reviewStatus;//댓글상태
	
	public ReviewComment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReviewComment(int commentNo, String commentWriter, int reviewNo, int commentRef, String content,
			Date regDate, String reviewStatus) {
		super();
		this.commentNo = commentNo;
		this.commentWriter = commentWriter;
		this.reviewNo = reviewNo;
		this.commentRef = commentRef;
		this.content = content;
		this.regDate = regDate;
		this.reviewStatus = reviewStatus;
	}

	/**
	 * @return the commentNo
	 */
	public int getCommentNo() {
		return commentNo;
	}

	/**
	 * @param commentNo the commentNo to set
	 */
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	/**
	 * @return the commentWriter
	 */
	public String getCommentWriter() {
		return commentWriter;
	}

	/**
	 * @param commentWriter the commentWriter to set
	 */
	public void setCommentWriter(String commentWriter) {
		this.commentWriter = commentWriter;
	}

	/**
	 * @return the reviewNo
	 */
	public int getReviewNo() {
		return reviewNo;
	}

	/**
	 * @param reviewNo the reviewNo to set
	 */
	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}

	/**
	 * @return the commentRef
	 */
	public int getCommentRef() {
		return commentRef;
	}

	/**
	 * @param commentRef the commentRef to set
	 */
	public void setCommentRef(int commentRef) {
		this.commentRef = commentRef;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the regDate
	 */
	public Date getRegDate() {
		return regDate;
	}

	/**
	 * @param regDate the regDate to set
	 */
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	/**
	 * @return the reviewStatus
	 */
	public String getReviewStatus() {
		return reviewStatus;
	}

	/**
	 * @param reviewStatus the reviewStatus to set
	 */
	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	@Override
	public String toString() {
		return "ReviewComment [commentNo=" + commentNo + ", commentWriter=" + commentWriter + ", reviewNo=" + reviewNo
				+ ", commentRef=" + commentRef + ", content=" + content + ", regDate=" + regDate + ", reviewStatus="
				+ reviewStatus + "]";
	}
	
	
	
	
}
