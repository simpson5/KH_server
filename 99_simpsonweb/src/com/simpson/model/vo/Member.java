package com.simpson.model.vo;

public class Member {
	private String memberId;
	private String password;
	private String name;
	private String ssn;
	private String phone;
	private String adress;
	private String email;
	
	public Member(String memberId, String password, String name, String ssn, String phone, String adress,
			String email) {
		super();
		this.memberId = memberId;
		this.password = password;
		this.name = name;
		this.ssn = ssn;
		this.phone = phone;
		this.adress = adress;
		this.email = email;
	}
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
