<%@page import="java.sql.Date"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%
	String memberId =loginMember.getMemberID();
	String memberName = loginMember.getMemberName();
	Date birthday = loginMember.getBirthday();
	String email = loginMember.getEmail() != null ? loginMember.getEmail() : "";
	String phone = loginMember.getPhone();
	String address = loginMember.getAddress() != null ? loginMember.getAddress() : "";
	String gender = loginMember.getGender() != null ? loginMember.getGender() : "";
	String hobby = loginMember.getHobby();

	List<String> hobbyList = null;
	if(hobby != null){
		String[] arr = hobby.split(",");
		hobbyList = Arrays.asList(arr); // String[] -> List<String>
	}
	

%>

<form name ="memberDeleteFrm">
	<input type="hidden" name="memberId" />
</form>

<section id="enroll-container">
	<h2>회원 정보</h2>
	<form id="memberUpdateFrm" action="<%= request.getContextPath() %>/member/memberUpdate" method="post">
		<table>
			<tr>
				<th>아이디</th>
				<td><input type="text" name="memberId" id="memberId_"
					value="<%= memberId %>" readonly /></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" name="memberName" id="memberName"
					value="<%= memberName %>" required /><br /></td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td><input type="date" name="birthday" id="birthday"
					value="<%= birthday %>" /><br /></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="email" placeholder="abc@xyz.com" name="email"
					id="email" value="<%= email %>" /><br /></td>
			</tr>
			<tr>
				<th>휴대폰</th>
				<td><input type="tel" placeholder="(-없이)01012345678"
					name="phone" id="phone" maxlength="11" value="<%= phone %>"
					required /><br /></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><input type="text" placeholder="" name="address"
					id="address" value="<%= address %>" /><br /></td>
			</tr>
			<tr>
				<th>성별</th>
				<td><input type="radio" name="gender" id="gender0" value="M"
					<%=
          "M".equals(gender) ? "checked" : "" %>> <label
					for="gender0">남</label> <input type="radio" name="gender"
					id="gender1" value="F"
					<%=
          "F".equals(gender) ? "checked" : "" %>> <label
					for="gender1">여</label></td>
			</tr>
			<tr>
				<th>취미</th>
				<td><input type="checkbox" name="hobby" id="hobby0" value="운동"
					<%= hobbyChecked(hobbyList, "운동") %> /><label
					for="hobby0">운동</label>
					<input type="checkbox" name="hobby" id="hobby1" value="등산"
					<%= hobbyChecked(hobbyList, "등산") %> /><label
					for="hobby1">등산</label>
					<input type="checkbox" name="hobby" id="hobby2" value="독서"
					<%= hobbyChecked(hobbyList, "독서")%> /><label
					for="hobby2">독서</label><br />
					<input type="checkbox" name="hobby" id="hobby3" value="게임"
					<%= hobbyChecked(hobbyList, "게임") %> /><label
					for="hobby3">게임</label>
					<input type="checkbox" name="hobby" id="hobby4" value="여행"
					<%= hobbyChecked(hobbyList, "여행") %> /><label
					for="hobby4">여행</label><br /></td>
			</tr>
		</table>
		<input type="submit" onclick="updateMember();" value="정보수정" />
		<input type="button" onclick="updatePassword();" value="비밀번호 변경" />
		<input type="button" onclick="deleteMember();" value="탈퇴"/>
	</form>
<script>
//내 방식
<%-- $(function () {
    var hobby = "<%=loginMember.getHobby()%>";
    var hobbyArr = hobby.split(",");
    for (var i in hobbyArr) {
      $.each($("[name=hobby]"), function (index, chk) {
        if (chk.value == hobbyArr[i]) {
          $(chk).prop("checked", true);
        }
      });
    }
  }); --%>
 
function updatePassword() {
	location.href = "<%= request.getContextPath()%>/member/updatePassword"
}
  
function deleteMember(){
	//폼제출
	var $memberId = $("#memberId_");
	$frm = $(document.memberDeleteFrm);
	$frm.find("[name=memberId]").val($memberId.val()); // 사용자 입력 id세팅
	
	$frm.attr("action", "<%= request.getContextPath() %>/member/memberDelete")
		.attr("method", "POST")
		.submit();
}

function updateMember(){
	//유효성 검사
	var phone = $("#phone").val();
	if(/^[0-9]{0,11}$/.test(phone) == false) {
		alert("전화번호는 숫자로만 적어주세요");
		return false;
	}
}
</script>
</section>
<%!
	//메소드 선언문
	public String hobbyChecked(List<String> hobbyList, String hobby){
		return hobbyList != null && hobbyList.contains(hobby) ? "checked" : "";
	}
%>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
