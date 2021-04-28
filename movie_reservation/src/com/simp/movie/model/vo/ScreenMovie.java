package com.simp.movie.model.vo;

public class ScreenMovie extends Movie {
	private String movieTitle;
	private String genre;
	private String mainPoster;
	private String poster;
	private String steelCut;
	private String trailer;
	private String Synopsis;
	private double purchaseRate;
	private String screeningRate;
	private String scrennStatus;
	
	public ScreenMovie(String title, String link, String image, String subtitle, String pubDate, String director,
			String actor, String userRating, String movieTitle, String genre, String mainPoster, String poster,
			String steelCut, String trailer, String synopsis, double purchaseRate, String screeningRate,
			String scrennStatus) {
		super(title, link, image, subtitle, pubDate, director, actor, userRating);
		this.movieTitle = movieTitle;
		this.genre = genre;
		this.mainPoster = mainPoster;
		this.poster = poster;
		this.steelCut = steelCut;
		this.trailer = trailer;
		this.Synopsis = synopsis;
		this.purchaseRate = purchaseRate;
		this.screeningRate = screeningRate;
		this.scrennStatus = scrennStatus;
	}
	
	public ScreenMovie() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ScreenMovie(String title, String link, String image, String subtitle, String pubDate, String director,
			String actor, String userRating) {
		super(title, link, image, subtitle, pubDate, director, actor, userRating);
		// TODO Auto-generated constructor stub
	}
	public ScreenMovie(String title, String image, String pubDate, String director, String actor) {
		super(title, image, pubDate, director, actor);
		// TODO Auto-generated constructor stub
	}
	
	public String getMovieTitle() {
		return movieTitle;
	}
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getMainPoster() {
		return mainPoster;
	}
	public void setMainPoster(String mainPoster) {
		this.mainPoster = mainPoster;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getSteelCut() {
		return steelCut;
	}
	public void setSteelCut(String steelCut) {
		this.steelCut = steelCut;
	}
	public String getTrailer() {
		return trailer;
	}
	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}
	public String getSynopsis() {
		return Synopsis;
	}
	public void setSynopsis(String synopsis) {
		Synopsis = synopsis;
	}
	public double getPurchaseRate() {
		return purchaseRate;
	}
	public void setPurchaseRate(double purchaseRate) {
		this.purchaseRate = purchaseRate;
	}
	public String getScreeningRate() {
		return screeningRate;
	}
	public void setScreeningRate(String screeningRate) {
		this.screeningRate = screeningRate;
	}
	public String getScrennStatus() {
		return scrennStatus;
	}
	public void setScrennStatus(String scrennStatus) {
		this.scrennStatus = scrennStatus;
	}
	
	@Override
	public String toString() {
		return "ScreenMovie [movieTitle=" + movieTitle + ", genre=" + genre + ", mainPoster=" + mainPoster + ", poster="
				+ poster + ", steelCut=" + steelCut + ", trailer=" + trailer + ", Synopsis=" + Synopsis
				+ ", purchaseRate=" + purchaseRate + ", screeningRate=" + screeningRate + ", scrennStatus="
				+ scrennStatus + "]";
	}
}
