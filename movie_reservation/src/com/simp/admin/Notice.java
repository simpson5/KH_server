package com.simp.admin;

public class Notice {
	private int noticeNo; // 공지사항 번호
	private String managerId; // 운영자 아이디
	private String noticeContent; // 공지내용
	private int noticeRank; // 공지순위
	private String noticeStatus; // 공지상태
	public Notice(int noticeNo, String managerId, String noticeContent, int noticeRank, String noticeStatus) {
		super();
		this.noticeNo = noticeNo;
		this.managerId = managerId;
		this.noticeContent = noticeContent;
		this.noticeRank = noticeRank;
		this.noticeStatus = noticeStatus;
	}
	public Notice() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	public int getNoticeRank() {
		return noticeRank;
	}
	public void setNoticeRank(int noticeRank) {
		this.noticeRank = noticeRank;
	}
	public String getNoticeStatus() {
		return noticeStatus;
	}
	public void setNoticeStatus(String noticeStatus) {
		this.noticeStatus = noticeStatus;
	}
	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", managerId=" + managerId + ", noticeContent=" + noticeContent
				+ ", noticeRank=" + noticeRank + ", noticeStatus=" + noticeStatus + "]";
	}
	
	
}
