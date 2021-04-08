package theater.model.vo;

import java.io.Serializable;

public class Seat extends ScreenArea implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String seatNo;
	private String reservationYN;
	private String reservationYNTemp;
	private int showCode;
	
	public Seat() {
		super();
	}

	public Seat(String seatNo, String reservationYN, String reservationYNTemp, int showCode) {
		super();
		this.seatNo = seatNo;
		this.reservationYN = reservationYN;
		this.reservationYNTemp = reservationYNTemp;
		this.showCode = showCode;
	}

	public Seat(int screenCode, int seatCnt, int remainSeats, String seatNo, String reservationYN,
			String reservationYNTemp, int showCode) {
		super(screenCode, seatCnt, remainSeats);
		this.seatNo = seatNo;
		this.reservationYN = reservationYN;
		this.reservationYNTemp = reservationYNTemp;
		this.showCode = showCode;
	}

	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

	public String getReservationYN() {
		return reservationYN;
	}

	public void setReservationYN(String reservationYN) {
		this.reservationYN = reservationYN;
	}

	public String getReservationYNTemp() {
		return reservationYNTemp;
	}

	public void setReservationYNTemp(String reservationYNTemp) {
		this.reservationYNTemp = reservationYNTemp;
	}

	public int getShowCode() {
		return showCode;
	}

	public void setShowCode(int showCode) {
		this.showCode = showCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Seat [seatNo=" + seatNo + ", reservationYN=" + reservationYN + ", reservationYNTemp="
				+ reservationYNTemp + ", showCode=" + showCode + "]";
	}
	
	
}
