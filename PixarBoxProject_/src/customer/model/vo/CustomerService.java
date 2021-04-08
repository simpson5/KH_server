package customer.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class CustomerService implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int boardNo;
	private String memberId;
	private String boardTitle;
	private String boardType;
	private Date boardDate;
	private String boardContent;
	private String publicYN;
	private String originalFileName;
	private String renamedFileName;
	
	public CustomerService() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public CustomerService(int boardNo, String memberId, String boardTitle, String boardType, Date boardDate,
			String boardContent, String publicYN, String originalFileName, String renamedFileName) {
		super();
		this.boardNo = boardNo;
		this.memberId = memberId;
		this.boardTitle = boardTitle;
		this.boardType = boardType;
		this.boardDate = boardDate;
		this.boardContent = boardContent;
		this.publicYN = publicYN;
		this.originalFileName = originalFileName;
		this.renamedFileName = renamedFileName;
	}

	


	public int getBoardNo() {
		return boardNo;
	}



	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}



	public String getMemberId() {
		return memberId;
	}



	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}



	public String getBoardTitle() {
		return boardTitle;
	}



	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}



	public String getBoardType() {
		return boardType;
	}



	public void setBoardType(String boardType) {
		this.boardType = boardType;
	}



	public Date getBoardDate() {
		return boardDate;
	}



	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}



	public String getBoardContent() {
		return boardContent;
	}



	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}



	public String getpublicYN() {
		return publicYN;
	}



	public void setpublicYN(String publicYN) {
		this.publicYN = publicYN;
	}



	public String getOriginalFileName() {
		return originalFileName;
	}



	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}



	public String getRenamedFileName() {
		return renamedFileName;
	}



	public void setRenamedFileName(String renamedFileName) {
		this.renamedFileName = renamedFileName;
	}



	@Override
	public String toString() {
		return "CustomerService [boardNo=" + boardNo + ", memberId=" + memberId + ", boardTitle=" + boardTitle + ", boardType=" + boardType + ", boardDate="
				+ boardDate + ", boardContent="+ boardContent+ ",publicYN" + publicYN + ",originalFileName=" +originalFileName + ",renamedFileName=" + renamedFileName + " ]";
	}




	
	

}
