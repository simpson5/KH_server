package customer.model.vo;

import java.io.Serializable;

public class CsComment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int cscommentNo;
	private int boardNo;
	private String cscomment;
	public CsComment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CsComment(int cscommentNo, int boardNo, String cscomment) {
		super();
		this.cscommentNo = cscommentNo;
		this.boardNo = boardNo;
		this.cscomment = cscomment;
	}
	public int getCscommentNo() {
		return cscommentNo;
	}
	public void setCscommentNo(int cscommentNo) {
		this.cscommentNo = cscommentNo;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getCscomment() {
		return cscomment;
	}
	public void setCscomment(String cscomment) {
		this.cscomment = cscomment;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "CsComment [cscommentNo=" + cscommentNo + ", boardNo=" + boardNo + ", cscomment=" + cscomment + " ]";
	}

}
