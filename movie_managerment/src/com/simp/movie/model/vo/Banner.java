package com.simp.movie.model.vo;

public class Banner {
	int no;
	String poster;
	public Banner(int no, String poster) {
		super();
		this.no = no;
		this.poster = poster;
	}
	public Banner() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	@Override
	public String toString() {
		return "Banner [no=" + no + ", poster=" + poster + "]";
	}
	
	
}
