package ticket.model.vo;

import java.sql.Date;

public class Payment {
	private String paymentCode;
	private String memberId;
	private String paymentMethod;
	private Date paymentDate;
	private int paymentPrice;
	
	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Payment(String paymentCode, String memberId, String paymentMethod, Date paymentDate, int paymentPrice) {
		super();
		this.paymentCode = paymentCode;
		this.memberId = memberId;
		this.paymentMethod = paymentMethod;
		this.paymentDate = paymentDate;
		this.paymentPrice = paymentPrice;
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

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public int getPaymentPrice() {
		return paymentPrice;
	}

	public void setPaymentPrice(int paymentPrice) {
		this.paymentPrice = paymentPrice;
	}

	@Override
	public String toString() {
		return "Payment [paymentCode=" + paymentCode + ", memberId=" + memberId + ", paymentMethod=" + paymentMethod
				+ ", paymentDate=" + paymentDate + ", paymentPrice=" + paymentPrice + "]";
	}
	
	
	
	
	
}
