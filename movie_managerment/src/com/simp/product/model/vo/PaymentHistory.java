package com.simp.product.model.vo;

import java.sql.Date;

public class PaymentHistory {

	private String paymentNo;//결제번호
	private String memberId;//아이디
	private int amount;//결재금액
	private int point;//사용포인트
	private Date payDate;//결제시간
	private String payContent;//결제내용
	
	public PaymentHistory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PaymentHistory(String paymentNo, String memberId, int amount, int point, Date payDate, String payContent) {
		super();
		this.paymentNo = paymentNo;
		this.memberId = memberId;
		this.amount = amount;
		this.point = point;
		this.payDate = payDate;
		this.payContent = payContent;
	}

	/**
	 * @return the paymentNo
	 */
	public String getPaymentNo() {
		return paymentNo;
	}

	/**
	 * @param paymentNo the paymentNo to set
	 */
	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}

	/**
	 * @return the memberId
	 */
	public String getMemberId() {
		return memberId;
	}

	/**
	 * @param memberId the memberId to set
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * @return the point
	 */
	public int getPoint() {
		return point;
	}

	/**
	 * @param point the point to set
	 */
	public void setPoint(int point) {
		this.point = point;
	}

	/**
	 * @return the payDate
	 */
	public Date getPayDate() {
		return payDate;
	}

	/**
	 * @param payDate the payDate to set
	 */
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	/**
	 * @return the payContent
	 */
	public String getPayContent() {
		return payContent;
	}

	/**
	 * @param payContent the payContent to set
	 */
	public void setPayContent(String payContent) {
		this.payContent = payContent;
	}

	@Override
	public String toString() {
		return "PaymentHistory [paymentNo=" + paymentNo + ", memberId=" + memberId + ", amount=" + amount + ", point="
				+ point + ", payDate=" + payDate + ", payContent=" + payContent + "]";
	}
	
	
}
