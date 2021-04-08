package movie.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class MovieNow extends MovieWithCommentCnt implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int showCode;
	private int screenCode;
	private String showTurn;
	private String startTime;
	private String endTime;
	private Date showDate;
	private int remainSeats;
	
	public MovieNow() {
		super();
	}

	public MovieNow(int showCode, int screenCode, String showTurn, String startTime, String endTime, Date showDate,
			int remainSeats) {
		super();
		this.showCode = showCode;
		this.screenCode = screenCode;
		this.showTurn = showTurn;
		this.startTime = startTime;
		this.endTime = endTime;
		this.showDate = showDate;
		this.remainSeats = remainSeats;
	}

	public MovieNow(String movieCode, String movieTitle, int runningTime, String director, String actor,
			String description, Date releaseDate, int ticketSales, String originalFileName, String renamedFileName,
			String movieVideo, String genres, String schedule, int showCode, int screenCode, String showTurn,
			String startTime, String endTime, Date showDate, int remainSeats) {
		super(movieCode, movieTitle, runningTime, director, actor, description, releaseDate, ticketSales,
				originalFileName, renamedFileName, movieVideo, genres, schedule, remainSeats);
		this.showCode = showCode;
		this.screenCode = screenCode;
		this.showTurn = showTurn;
		this.startTime = startTime;
		this.endTime = endTime;
		this.showDate = showDate;
		this.remainSeats = remainSeats;
	}

	public int getShowCode() {
		return showCode;
	}

	public void setShowCode(int showCode) {
		this.showCode = showCode;
	}

	public int getScreenCode() {
		return screenCode;
	}

	public void setScreenCode(int screenCode) {
		this.screenCode = screenCode;
	}

	public String getShowTurn() {
		return showTurn;
	}

	public void setShowTurn(String showTurn) {
		this.showTurn = showTurn;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Date getShowDate() {
		return showDate;
	}

	public void setShowDate(Date showDate) {
		this.showDate = showDate;
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
		return "MovieNow [showCode=" + showCode + ", screenCode=" + screenCode + ", showTurn=" + showTurn
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", showDate=" + showDate + ", remainSeats="
				+ remainSeats + "]";
	}
	
}
