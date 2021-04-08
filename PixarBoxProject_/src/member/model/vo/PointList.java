package member.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class PointList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int pointCode;
	private String memberId;
	private int amount;
	private String status;
	private Date pointDate;
	private String movieTitle;
	private String paymentCode;
	
	public PointList() {
		super();
	}

	public PointList(int pointCode, String memberId, int amount, String status, Date pointDate, String movieTitle,
			String paymentCode) {
		super();
		this.pointCode = pointCode;
		this.memberId = memberId;
		this.amount = amount;
		this.status = status;
		this.pointDate = pointDate;
		this.movieTitle = movieTitle;
		this.paymentCode = paymentCode;
	}

	public int getPointCode() {
		return pointCode;
	}

	public void setPointCode(int pointCode) {
		this.pointCode = pointCode;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getPointDate() {
		return pointDate;
	}

	public void setPointDate(Date pointDate) {
		this.pointDate = pointDate;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "PointList [pointCode=" + pointCode + ", memberId=" + memberId + ", amount=" + amount + ", status="
				+ status + ", pointDate=" + pointDate + ", movieTitle=" + movieTitle + ", paymentCode=" + paymentCode
				+ "]";
	}

	
}