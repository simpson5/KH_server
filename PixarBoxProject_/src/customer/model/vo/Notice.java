package customer.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Notice implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private String noticeOriginalFileName;
	private String noticeRenamedFileName;
	private Date noticeDate;
	
	public Notice() {
		super();
	}

	public Notice(int noticeNo, String noticeTitle, String noticeContent, String noticeOriginalFileName,
			String noticeRenamedFileName, Date noticeDate) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.noticeOriginalFileName = noticeOriginalFileName;
		this.noticeRenamedFileName = noticeRenamedFileName;
		this.noticeDate = noticeDate;
	}

	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public String getNoticeOriginalFileName() {
		return noticeOriginalFileName;
	}

	public void setNoticeOriginalFileName(String noticeOriginalFileName) {
		this.noticeOriginalFileName = noticeOriginalFileName;
	}

	public String getNoticeRenamedFileName() {
		return noticeRenamedFileName;
	}

	public void setNoticeRenamedFileName(String noticeRenamedFileName) {
		this.noticeRenamedFileName = noticeRenamedFileName;
	}

	public Date getNoticeDate() {
		return noticeDate;
	}

	public void setNoticeDate(Date noticeDate) {
		this.noticeDate = noticeDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", noticeTitle=" + noticeTitle + ", noticeContent=" + noticeContent
				+ ", noticeOriginalFileName=" + noticeOriginalFileName + ", noticeRenamedFileName="
				+ noticeRenamedFileName + ", noticeDate=" + noticeDate + "]";
	}
	
	
	
}
