package com.simp.member.model.vo;

import java.sql.Date;

public class Message {
	private int messageNo;
	private String memberId;
	private int reviewNo;
	private String managerId;
	private String messageCategory;
	private String messageContent;
	private Date regDate;
	private String readCheck;
	private String messageStatus;
	
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Message(int messageNo, String memberId, int reviewNo, String managerId, String messageCategory,
			String messageContent, Date regDate, String readCheck, String messageStatus) {
		super();
		this.messageNo = messageNo;
		this.memberId = memberId;
		this.reviewNo = reviewNo;
		this.managerId = managerId;
		this.messageCategory = messageCategory;
		this.messageContent = messageContent;
		this.regDate = regDate;
		this.readCheck = readCheck;
		this.messageStatus = messageStatus;
	}
	public int getMessageNo() {
		return messageNo;
	}
	public void setMessageNo(int messageNo) {
		this.messageNo = messageNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public int getReviewNo() {
		return reviewNo;
	}
	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getMessageCategory() {
		return messageCategory;
	}
	public void setMessageCategory(String messageCategory) {
		this.messageCategory = messageCategory;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getReadCheck() {
		return readCheck;
	}
	public void setReadCheck(String readCheck) {
		this.readCheck = readCheck;
	}
	public String getMessageStatus() {
		return messageStatus;
	}
	public void setMessageStatus(String messageStatus) {
		this.messageStatus = messageStatus;
	}
	@Override
	public String toString() {
		return "Message [messageNo=" + messageNo + ", memberId=" + memberId + ", reviewNo=" + reviewNo + ", managerId="
				+ managerId + ", messageCategory=" + messageCategory + ", messageContent=" + messageContent
				+ ", regDate=" + regDate + ", readCheck=" + readCheck + ", messageStatus=" + messageStatus + "]";
	}
	
	
}
