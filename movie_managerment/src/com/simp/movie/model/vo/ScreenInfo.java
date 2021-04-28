package com.simp.movie.model.vo;

import java.sql.Timestamp;

public class ScreenInfo {
	private String screenNo;
	private String movieTitle;
	private String managerId;
	private String theaterNo;
	private Timestamp startTime;
	private Timestamp endTime;
	private int screenPrice;
	private int totalTicket;
	private int soldTicket;
	private String status;
	
	public ScreenInfo(String screenNo, String movieTitle, String managerId, String theaterNo, Timestamp startTime,
			Timestamp endTime, int screenPrice, int totalTicket, int soldTicket) {
		super();
		this.screenNo = screenNo;
		this.movieTitle = movieTitle;
		this.managerId = managerId;
		this.theaterNo = theaterNo;
		this.startTime = startTime;
		this.endTime = endTime;
		this.totalTicket = totalTicket;
		this.soldTicket = soldTicket;
		this.screenPrice = screenPrice;
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
	
	public int getScreenPrice() {
		return screenPrice;
	}
	public void setScreenPrice(int screenPrice) {
		this.screenPrice = screenPrice;
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
	
	@Override
	public String toString() {
		return "ScreenInfo [screenNo=" + screenNo + ", movieTitle=" + movieTitle + ", managerId=" + managerId
				+ ", theaterNo=" + theaterNo + ", startTime=" + startTime + ", endTime=" + endTime + ",screenPrice" + screenPrice + ",totalTicket="
				+ totalTicket + ", soldTicket=" + soldTicket + "]";
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
