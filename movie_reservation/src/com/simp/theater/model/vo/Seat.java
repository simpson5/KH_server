package com.simp.theater.model.vo;

public class Seat {
	private String seatNo; // 좌석번호
	private int theaterNo; // 상영관 호
	private String seatStatus; // 좌석상태
	public Seat() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Seat(String seatNo, int theaterNo, String seatStatus) {
		super();
		this.seatNo = seatNo;
		this.theaterNo = theaterNo;
		this.seatStatus = seatStatus;
	}
	public String getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}
	public int getTheaterNo() {
		return theaterNo;
	}
	public void setTheaterNo(int theaterNo) {
		this.theaterNo = theaterNo;
	}
	public String getSeatStatus() {
		return seatStatus;
	}
	public void setSeatStatus(String seatStatus) {
		this.seatStatus = seatStatus;
	}
	@Override
	public String toString() {
		return "Seat [seatNo=" + seatNo + ", theaterNo=" + theaterNo + ", seatStatus=" + seatStatus + "]";
	}
	
	
	
	
	
}
