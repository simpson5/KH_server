package com.simp.movie.model.vo;

import java.sql.Timestamp;

public class ScreenInfo extends ScreenMovie {
	private String screenNo;
	private String movieTitle;
	private String managerId;
	private String theaterNo;
	private Timestamp startTime;
	private Timestamp endTime;
	private int totalTicket;
	private int soldTicket;
	private int screen_price;
	public ScreenInfo(String title, String link, String image, String subtitle, String pubDate, String director,
			String actor, String userRating, String movieTitle, String genre, String mainPoster, String poster,
			String steelCut, String trailer, String synopsis, double purchaseRate, String screeningRate,
			String scrennStatus, String screenNo, String movieTitle2, String managerId, String theaterNo,
			Timestamp startTime, Timestamp endTime, int totalTicket, int soldTicket, int screen_price) {
		super(title, link, image, subtitle, pubDate, director, actor, userRating, movieTitle, genre, mainPoster, poster,
				steelCut, trailer, synopsis, purchaseRate, screeningRate, scrennStatus);
		this.screenNo = screenNo;
		movieTitle = movieTitle2;
		this.managerId = managerId;
		this.theaterNo = theaterNo;
		this.startTime = startTime;
		this.endTime = endTime;
		this.totalTicket = totalTicket;
		this.soldTicket = soldTicket;
		this.screen_price = screen_price;
	}
	public ScreenInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getScreenNo() {
		return screenNo;
	}
	public void setScreenNo(String screenNo) {
		this.screenNo = screenNo;
	}
	public String getMovieTitle() {
		return movieTitle;
	}
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getTheaterNo() {
		return theaterNo;
	}
	public void setTheaterNo(String theaterNo) {
		this.theaterNo = theaterNo;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public int getTotalTicket() {
		return totalTicket;
	}
	public void setTotalTicket(int totalTicket) {
		this.totalTicket = totalTicket;
	}
	public int getSoldTicket() {
		return soldTicket;
	}
	public void setSoldTicket(int soldTicket) {
		this.soldTicket = soldTicket;
	}
	public int getScreen_price() {
		return screen_price;
	}
	public void setScreen_price(int screen_price) {
		this.screen_price = screen_price;
	}
	@Override
	public String toString() {
		return "ScreenInfo [screenNo=" + screenNo + ", movieTitle=" + movieTitle + ", managerId=" + managerId
				+ ", theaterNo=" + theaterNo + ", startTime=" + startTime + ", endTime=" + endTime + ", totalTicket="
				+ totalTicket + ", soldTicket=" + soldTicket + ", screen_price=" + screen_price + "]";
	}
	
	
}
