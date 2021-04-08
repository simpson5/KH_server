package ticket.model.vo;

import java.sql.Date;

public class Ticketing {
	private int tickeNo;
	private String movieTitle;
	private String paymentCode;
	private String memberId;
	private String seatNo;
	private int screenCode;
	private String startTime;
	private String endTime;
	private String ticketingDate;
	private String cancelYN;
	private String cancelDate;
	private int paymentPrice;
	private Date showDate;
	
	public Ticketing() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ticketing(int tickeNo, String movieTitle, String paymentCode, String memberId, String seatNo, int screenCode,
			String startTime, String endTime, String ticketingDate, String cancelYN, String cancelDate,
			int paymentPrice, Date showDate) {
		super();
		this.tickeNo = tickeNo;
		this.movieTitle = movieTitle;
		this.paymentCode = paymentCode;
		this.memberId = memberId;
		this.seatNo = seatNo;
		this.screenCode = screenCode;
		this.startTime = startTime;
		this.endTime = endTime;
		this.ticketingDate = ticketingDate;
		this.cancelYN = cancelYN;
		this.cancelDate = cancelDate;
		this.paymentPrice = paymentPrice;
		this.showDate = showDate;
	}

	public int getTickeNo() {
		return tickeNo;
	}

	public void setTickeNo(int tickeNo) {
		this.tickeNo = tickeNo;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public String getPaymentCode() {
		return paymentCode;
	}

	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

	public int getScreenCode() {
		return screenCode;
	}

	public void setScreenCode(int screenCode) {
		this.screenCode = screenCode;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getTicketingDate() {
		return ticketingDate;
	}

	public void setTicketingDate(String ticketingDate) {
		this.ticketingDate = ticketingDate;
	}

	public String getCancelYN() {
		return cancelYN;
	}

	public void setCancelYN(String cancelYN) {
		this.cancelYN = cancelYN;
	}

	public String getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(String cancelDate) {
		this.cancelDate = cancelDate;
	}

	public int getPaymentPrice() {
		return paymentPrice;
	}

	public void setPaymentPrice(int paymentPrice) {
		this.paymentPrice = paymentPrice;
	}

	public Date getShowDate() {
		return showDate;
	}

	public void setShowDate(Date showDate) {
		this.showDate = showDate;
	}

	@Override
	public String toString() {
		return "Ticketing [tickeNo=" + tickeNo + ", movieTitle=" + movieTitle + ", paymentCode=" + paymentCode
				+ ", memberId=" + memberId + ", seatNo=" + seatNo + ", screenCode=" + screenCode + ", startTime="
				+ startTime + ", endTime=" + endTime + ", ticketingDate=" + ticketingDate + ", cancelYN=" + cancelYN
				+ ", cancelDate=" + cancelDate + ", paymentPrice=" + paymentPrice + ", showDate=" + showDate + "]";
	}

	
}

