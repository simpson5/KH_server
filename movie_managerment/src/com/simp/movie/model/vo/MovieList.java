package com.simp.movie.model.vo;

import java.util.ArrayList;

public class MovieList {
	String lastBuildDate;
    String total;
    String start;
    String display;
    ArrayList<Movie> items = new ArrayList<Movie>();
	public String getLastBuildDate() {
		return lastBuildDate;
	}
	public void setLastBuildDate(String lastBuildDate) {
		this.lastBuildDate = lastBuildDate;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public ArrayList<Movie> getItems() {
		return items;
	}
	public void setItems(ArrayList<Movie> items) {
		this.items = items;
	}
}
