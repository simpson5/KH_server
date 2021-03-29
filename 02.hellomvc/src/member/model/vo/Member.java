package member.model.vo;

import java.sql.Date;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * VO클래스
 * 
 * DB테이블의 한행의 정보를 가지고 있는 객체
 */

public class Member implements HttpSessionBindingListener{
	
	private String memberId;
	private String password;
	private String memberName;
	private String memberRole;
	private String gender;
	private Date birthday;
	private String email;
	private String phone;
	private String address;
	private String hobby;
	private Date enrolldate;
	
	public Member(String memberID, String password, String memberName, String memberRole, String gender, Date birthday,
			String email, String phone, String address, String hobby, Date enrolldate) {
		super();
		this.memberId = memberID;
		this.password = password;
		this.memberName = memberName;
		this.memberRole = memberRole;
		this.gender = gender;
		this.birthday = birthday;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.hobby = hobby;
		this.enrolldate = enrolldate;
	}
	
	//가입시간 없는 버젼
	public Member(String memberID, String password, String memberName, String memberRole, String gender, Date birthday,
			String email, String phone, String address, String hobby) {
		super();
		this.memberId = memberID;
		this.password = password;
		this.memberName = memberName;
		this.memberRole = memberRole;
		this.gender = gender;
		this.birthday = birthday;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.hobby = hobby;
	}
	
	public Member() {
		// TODO Auto-generated constructor stub
	}

	public String getMemberID() {
		return memberId;
	}
	public void setMemberID(String memberID) {
		this.memberId = memberID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberRole() {
		return memberRole;
	}
	public void setMemberRole(String memberRole) {
		this.memberRole = memberRole;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public Date getEnrolldate() {
		return enrolldate;
	}
	public void setEnrolldate(Date enrolldate) {
		this.enrolldate = enrolldate;
	}
	
	@Override
	public String toString() {
		return "Member [memberID=" + memberId + ", password=" + password + ", memberName=" + memberName
				+ ", memberRole=" + memberRole + ", gender=" + gender + ", birthday=" + birthday + ", email=" + email
				+ ", phone=" + phone + ", address=" + address + ", hobby=" + hobby + ", enrolldate=" + enrolldate + "]";
	}

	/**
	 * Session객체의 현재객체가 속성으로 등록될때 호출되는 event listener
	 */
	@Override
	public void valueBound(HttpSessionBindingEvent ev) {
		System.out.println(memberName + "[" + memberId + "]님이 로그인 했습니다.");
	}

	/**
	 * Session객체의 현재객체가 속성에서 해제될때 호출되는 event listener
	 * Session이 무효화될때도 호출
	 */
	@Override
	public void valueUnbound(HttpSessionBindingEvent ev) {
		System.out.println(memberName + "[" + memberId + "]님이 로그아웃 했습니다.");
	}
}
