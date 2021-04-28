package com.simp.member.model.vo;

import java.sql.Date;

public class Member {
	
	private String member_id;
	private String password;
	private String member_name;
	private String member_ssn;
	private String email;
	private String phone;
	private String address;
	private String preference_genre;
	private String member_status;
	private String member_grade;
	private Date enroll_date;
	private int point;
	
	public Member(String member_id, String password, String member_name, String member_ssn, String email, String phone,
			String address, String preference_genre, String member_status, String member_grade, Date enroll_date,
			int point) {
		super();
		this.member_id = member_id;
		this.password = password;
		this.member_name = member_name;
		this.member_ssn = member_ssn;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.preference_genre = preference_genre;
		this.member_status = member_status;
		this.member_grade = member_grade;
		this.enroll_date = enroll_date;
		this.point = point;
	}
	
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_ssn() {
		return member_ssn;
	}
	public void setMember_ssn(String member_ssn) {
		this.member_ssn = member_ssn;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPreference_genre() {
		return preference_genre;
	}
	public void setPreference_genre(String preference_genre) {
		this.preference_genre = preference_genre;
	}
	public String getMember_status() {
		return member_status;
	}
	public void setMember_status(String member_status) {
		this.member_status = member_status;
	}
	public String getMember_grade() {
		return member_grade;
	}
	public void setMember_grade(String member_grade) {
		this.member_grade = member_grade;
	}
	public Date getEnroll_date() {
		return enroll_date;
	}
	public void setEnroll_date(Date enroll_date) {
		this.enroll_date = enroll_date;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	
	@Override
	public String toString() {
		return "Member [member_id=" + member_id + ", password=" + password + ", member_name=" + member_name
				+ ", member_ssn=" + member_ssn + ", email=" + email + ", phone=" + phone + ", address=" + address
				+ ", preference_genre=" + preference_genre + ", member_status=" + member_status + ", member_grade="
				+ member_grade + ", enroll_date=" + enroll_date + ", point=" + point + "]";
	}
}
