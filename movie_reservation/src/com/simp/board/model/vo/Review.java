package com.simp.board.model.vo;

import java.sql.Date;

public class Review {
	
	private int reviewNo; // 리뷰 번호 (시퀀스)
	private String writer; // writer
	private String movieTitle; // 영화 제목 
	private String title; // 리뷰 제목
	private String content; // 리뷰 내용
	private int readCnt; // 조회수
	private int recomCnt;// 추천수
	private Date reviewDate; // 리뷰작성날짜
	private String reviewStatus; // 리뷰 상태

	
	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Review(int reviewNo, String writer, String movieTitle, String title, String content, int readCnt,
			int recomCnt, Date reviewDate, String reviewStatus) {
		super();
		this.reviewNo = reviewNo;
		this.writer = writer;
		this.movieTitle = movieTitle;
		this.title = title;
		this.content = content;
		this.readCnt = readCnt;
		this.recomCnt = recomCnt;
		this.reviewDate = reviewDate;
		this.reviewStatus = reviewStatus;
		
	}

	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getReadCnt() {
		return readCnt;
	}

	public void setReadCnt(int readCnt) {
		this.readCnt = readCnt;
	}

	public int getRecomCnt() {
		return recomCnt;
	}

	public void setRecomCnt(int recomCnt) {
		this.recomCnt = recomCnt;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public String getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus = reviewStatus;
	}


	@Override
	public String toString() {
		return "Review [reviewNo=" + reviewNo + ", writer=" + writer + ", movieTitle=" + movieTitle + ", title=" + title
				+ ", content=" + content + ", readCnt=" + readCnt + ", recomCnt=" + recomCnt + ", reviewDate="
				+ reviewDate + ", reviewStatus=" + reviewStatus +"]";
	}
	
	
	

}
