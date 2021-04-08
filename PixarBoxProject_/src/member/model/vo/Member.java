package member.model.vo;

import java.io.Serializable;
import java.sql.Date;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class Member implements Serializable, HttpSessionBindingListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String memberId;
	private String password;
	private String lastName;
	private String firstName;
	private String email;
	private String phone;
	private String interests;
	private String gender;
	private int age;
	private int point;
	private Date enrollDate;

	public Member() {
		super();
	}
	public Member(String memberId, String password, String lastName, String firstName, String email, String phone,
			String interests, String gender, int age, int point, Date enrollDate) {
		super();
		this.memberId = memberId;
		this.password = password;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.phone = phone;
		this.interests = interests;
		this.gender = gender;
		this.age = age;
		this.point = point;
		this.enrollDate = enrollDate;
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
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

	public String getInterests() {
		return interests;
	}

	public void setInterests(String interests) {
		this.interests = interests;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", password=" + password + ", lastName=" + lastName + ", firstName="
				+ firstName + ", email=" + email + ", phone=" + phone + ", interests=" + interests + ", gender="
				+ gender + ", age=" + age + ", point=" + point + ", enrollDate=" + enrollDate + "]";
	}
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		
	}
	
}
