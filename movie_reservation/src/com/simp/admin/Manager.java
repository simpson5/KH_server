package com.simp.admin;

public class Manager {
	private String managerId;
	private String password;
	private String managerGrade;
	public Manager() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Manager(String managerId, String password, String managerGrade) {
		super();
		this.managerId = managerId;
		this.password = password;
		this.managerGrade = managerGrade;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getManagerGrade() {
		return managerGrade;
	}
	public void setManagerGrade(String managerGrade) {
		this.managerGrade = managerGrade;
	}
	@Override
	public String toString() {
		return "Manager [managerId=" + managerId + ", password=" + password + ", managerGrade=" + managerGrade + "]";
	}
	
	
}
