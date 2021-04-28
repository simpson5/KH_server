package com.simp.theater.model.vo;

public class Theater {
	private int theaterNo; // 상영관 호
	private int theaterSeat; // 좌석 수
	private String theaterStatus; // 개봉여부
	public Theater() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Theater(int theaterNo, int theaterSeat, String theaterStatus) {
		super();
		this.theaterNo = theaterNo;
		this.theaterSeat = theaterSeat;
		this.theaterStatus = theaterStatus;
	}
	public int getTheaterNo() {
		return theaterNo;
	}
	public void setTheaterNo(int theaterNo) {
		this.theaterNo = theaterNo;
	}
	public int getTheaterSeat() {
		return theaterSeat;
	}
	public void setTheaterSeat(int theaterSeat) {
		this.theaterSeat = theaterSeat;
	}
	public String getTheaterStatus() {
		return theaterStatus;
	}
	public void setTheaterStatus(String theaterStatus) {
		this.theaterStatus = theaterStatus;
	}
	@Override
	public String toString() {
		return "Theater [theaterNo=" + theaterNo + ", theaterSeat=" + theaterSeat + ", theaterStatus=" + theaterStatus
				+ "]";
	}

	
	
}
