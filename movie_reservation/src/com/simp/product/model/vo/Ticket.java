package com.simp.product.model.vo;

public class Ticket {
	private String memberId; //아이디
	private String setNo; //좌석번호
	private String screeningCnt; //상영번호
	private String ticketNo; // 티켓넘버
	private int ticketPrice; // 티켓가격
	private String ticketStatus; // 판매여부
	
	public Ticket() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ticket(String memberId, String setNo, String screeningCnt, String ticketNo, int ticketPrice,
			String ticketStatus) {
		super();
		this.memberId = memberId;
		this.setNo = setNo;
		this.screeningCnt = screeningCnt;
		this.ticketNo = ticketNo;
		this.ticketPrice = ticketPrice;
		this.ticketStatus = ticketStatus;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getSetNo() {
		return setNo;
	}

	public void setSetNo(String setNo) {
		this.setNo = setNo;
	}

	public String getScreeningCnt() {
		return screeningCnt;
	}

	public void setScreeningCnt(String screeningCnt) {
		this.screeningCnt = screeningCnt;
	}

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	public int getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(int ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public String getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	@Override
	public String toString() {
		return "Ticket [memberId=" + memberId + ", setNo=" + setNo + ", screeningCnt=" + screeningCnt + ", ticketNo="
				+ ticketNo + ", ticketPrice=" + ticketPrice + ", ticketStatus=" + ticketStatus + "]";
	}
	
	
}
