package movie.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class MovieSeat extends MovieNow implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int seatCnt;
	private int remainSeats;
	
	public MovieSeat() {
		super();
	}
	public MovieSeat(int seatCnt, int remainSeats) {
		super();
		this.seatCnt = seatCnt;
		this.remainSeats = remainSeats;
	}
	
	
	public MovieSeat(String movieCode, String movieTitle, int runningTime, String director, String actor,
			String description, Date releaseDate, int ticketSales, String originalFileName, String renamedFileName,
			String movieVideo, String genres, String schedule, int showCode, int screenCode, String showTurn,
			String startTime, String endTime, Date showDate, int remainSeats, int seatCnt, int remainSeats2) {
		super(movieCode, movieTitle, runningTime, director, actor, description, releaseDate, ticketSales,
				originalFileName, renamedFileName, movieVideo, genres, schedule, showCode, screenCode, showTurn,
				startTime, endTime, showDate, remainSeats);
		this.seatCnt = seatCnt;
		remainSeats = remainSeats2;
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
		return "MovieSeat [seatCnt=" + seatCnt + ", remainSeats=" + remainSeats + "]";
	}
	
	
}
