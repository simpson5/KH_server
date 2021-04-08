package movie.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Movie implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String movieCode;
	private String movieTitle;
	private int runningTime;
	private String director;
	private String actor;
	private String description;
	private Date releaseDate;
	private int ticketSales;
//	private String movieImg;
	private String originalFileName;
	private String renamedFileName;
	private String movieVideo;
	private String genres;
	private String schedule;
	
	public Movie() {
	
		super();
	}

	public Movie(String movieCode, String movieTitle, int runningTime, String director, String actor,
			String description, Date releaseDate, int ticketSales, String originalFileName, String renamedFileName,
			String movieVideo, String genres, String schedule) {
		super();
		this.movieCode = movieCode;
		this.movieTitle = movieTitle;
		this.runningTime = runningTime;
		this.director = director;
		this.actor = actor;
		this.description = description;
		this.releaseDate = releaseDate;
		this.ticketSales = ticketSales;
		this.originalFileName = originalFileName;
		this.renamedFileName = renamedFileName;
		this.movieVideo = movieVideo;
		this.genres = genres;
		this.schedule = schedule;
	}

	public String getMovieCode() {
		return movieCode;
	}

	public void setMovieCode(String movieCode) {
		this.movieCode = movieCode;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public int getRunningTime() {
		return runningTime;
	}

	public void setRunningTime(int runningTime) {
		this.runningTime = runningTime;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public int getTicketSales() {
		return ticketSales;
	}

	public void setTicketSales(int ticketSales) {
		this.ticketSales = ticketSales;
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

	public String getMovieVideo() {
		return movieVideo;
	}

	public void setMovieVideo(String movieVideo) {
		this.movieVideo = movieVideo;
	}

	public String getGenres() {
		return genres;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Movie [movieCode=" + movieCode + ", movieTitle=" + movieTitle + ", runningTime=" + runningTime
				+ ", director=" + director + ", actor=" + actor + ", description=" + description + ", releaseDate="
				+ releaseDate + ", ticketSales=" + ticketSales + ", originalFileName=" + originalFileName
				+ ", renamedFileName=" + renamedFileName + ", movieVideo=" + movieVideo + ", genres=" + genres
				+ ", schedule=" + schedule + "]";
	}

}
