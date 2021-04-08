package theater.model.vo;

import java.io.Serializable;

public class ScreenArea implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int screenCode;
	private int seatCnt;
	private int remainSeats;
	
	public ScreenArea() {
		super();
	}

	public ScreenArea(int screenCode, int seatCnt, int remainSeats) {
		super();
		this.screenCode = screenCode;
		this.seatCnt = seatCnt;
		this.remainSeats = remainSeats;
	}

	public int getScreenCode() {
		return screenCode;
	}

	public void setScreenCode(int screenCode) {
		this.screenCode = screenCode;
	}

	public int getSeatCnt() {
		return seatCnt;
	}

	public void setSeatCnt(int seatCnt) {
		this.seatCnt = seatCnt;
	}

	public int getRemainSeats() {
		return remainSeats;
	}

	public void setRemainSeats(int remainSeats) {
		this.remainSeats = remainSeats;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ScreenArea [screenCode=" + screenCode + ", seatCnt=" + seatCnt + ", remainSeats=" + remainSeats + "]";
	}
	
	
}
