<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="member.model.vo.*" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	
	Member m = (Member)request.getAttribute("member");

	//필드가 not null이 아닌 경우(선택) null값 출력방지
	//String email = m.getEmail()!=null?m.getEmail():"";
	

	//관심장르 체크드처리
	String interestsChecked[] = {"", "", "", "", "", "", "", "", "", "", ""};
	
	if(m.getInterests() != null) {
		String interests[] = m.getInterests().split(",");
		
		for(String i : interests) {
			switch(i) {
			case "드라마": interestsChecked[0] = "checked"; break;
			case "판타지": interestsChecked[1] = "checked"; break;
			case "아동": interestsChecked[2] = "checked"; break;
			case "코미디": interestsChecked[3] = "checked"; break;
			case "어드벤처": interestsChecked[4] = "checked"; break;
			case "가족": interestsChecked[5] = "checked"; break;
			case "SF": interestsChecked[6] = "checked"; break;
			case "뮤지컬": interestsChecked[7] = "checked"; break;
			case "애니메이션": interestsChecked[8] = "checked"; break;
			case "모험": interestsChecked[9] = "checked"; break;
			case "액션": interestsChecked[10] = "checked"; break;
			}
		}
		
	}
	
	//이메일 셀렉티드처리
		String emailSelected[] = {"", "", "", ""};
		String email[] = null;
		
		if(m.getEmail() != null) {
			email = m.getEmail().split("@");
			
			if("hanmail.net".equals(email[1])) emailSelected[0] = "selected";
			else if("daum.net".equals(email[1])) emailSelected[1] = "selected";
			else if("naver.com".equals(email[1])) emailSelected[2] = "selected";
			else if("gmail.com".equals(email[1])) emailSelected[3] = "selected";
			
		}

		String genderChecked[] = {"", ""};
		
		if(m.getGender() != null) {
			if("F".equals(m.getGender())) genderChecked[0] = "checked";
			else if("M".equals(m.getGender())) genderChecked[1] = "checked";
		}
		
		String ageChecked[] = {"", "", "", "", "", ""};
		
		if(m.getAge()+"" != null) {
			if("10".equals(m.getAge()+"")) ageChecked[0] = "checked";
			else if("20".equals(m.getAge()+"")) ageChecked[1] = "checked";
			else if("30".equals(m.getAge()+"")) ageChecked[2] = "checked";
			else if("40".equals(m.getAge()+"")) ageChecked[3] = "checked";
			else if("50".equals(m.getAge()+"")) ageChecked[4] = "checked";
			else if("60".equals(m.getAge()+"")) ageChecked[5] = "checked";
		}
%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/admin.css" />

<section id="content-member">	
	
	<h2>회원정보 보기 및 수정</h2>
	<div class="title">
	    <hr>
	</div>
	<div id="enrollFrm">
	    <br><br><br>
	    <form action="<%=request.getContextPath() %>/admin/memberUpdate"
	     name="memberEnrollForm" 
	     method="post" 
	     id="enrollForm" 
	     onsubmit="return enrollValidate();" >

	        <p id="p_border_left">기본정보</p>
	        <span>이름</span>&nbsp;&nbsp;
	        <input type="text" name="lastName" 
	        id="lastName" placeholder="성" 
	        value="<%=m.getLastName() %>"
	        style="width: 90px;" required>&nbsp;&nbsp;&nbsp;
	        
	        <input type="text" name="firstName" 
	        id="firstName" placeholder="이름" 
	        value="<%=m.getFirstName() %>"
	        style="width: 150px;" required>
	        <br><br>
	        
	        <span>ID</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	        <input type="text" name="memberId" 
	        id="memberId" placeholder="아이디" 
	        value="<%=m.getMemberId() %>" 
	         required readonly style="width: 270px; background-color: #b8afd6;" >&nbsp;
	         <br><br>
	        <!-- <input type="button" value="중복검사" onclick="checkIdDuplicate();">
	        <input type="hidden" id="idValid" value="0" />
	        <br><br>
	        <input type="password" name="password" id="password" placeholder="비밀번호" style="width: 230px;" required>
	        <br><br>
	        <input type="password" name="pwdChk" id="pwdChk" placeholder="비밀번호 확인" style="width: 230px;" required>
	        <br><br> -->
	        
	        <span>E-mail</span>
	        <input type="text" name="email" id="email" placeholder="이메일" style="width: 110px;" value="<%=email[0] %>" required>
	        @
	        <select name="emailAdd" id="emailAdd" style="width: 132px;">
	            <option value="hanmail.net" <%=emailSelected[0] %>>hanmail.net</option>
	            <option value="daum.net" <%=emailSelected[1] %>>daum.net</option>
	            <option value="naver.com" <%=emailSelected[2] %>>naver.com</option>
	            <option value="gmail.com" <%=emailSelected[3] %>>gmail.com</option>
	        </select>
	        <br /><br />
	        <span>Phone</span>
	        <input type="tel" name="phone1" id="phone1" placeholder="010" maxlength="3" style="width: 56px;" value="<%=m.getPhone().substring(0, 3) %>">&nbsp;-
	        <input type="tel" name="phone2" id="phone2" placeholder="1234" maxlength="4" style="width: 78px;" value="<%=m.getPhone().substring(3, 7) %>">&nbsp;-
	        <input type="tel" name="phone3" id="phone3" placeholder="5678" maxlength="4" style="width: 78px;" value="<%=m.getPhone().substring(7) %>">
	        <br><br>
	        
	          <p id="p_border_gender">
	        성별  <input type="radio" name="gender" id="gender0" value="F" <%=genderChecked[0] %> />여자
		    <input type="radio" name="gender" id="gender1" value="M" <%=genderChecked[1] %> />남자
		   </p>
		    <br /><br />
		    <p id="p_border">연령</p>
		    <input type="radio" name="age" id="age10" value="10" <%=ageChecked[0] %> />10대
		    <input type="radio" name="age" id="age20" value="20" <%=ageChecked[1] %> />20대
		    <input type="radio" name="age" id="age30" value="30" <%=ageChecked[2] %> />30대
		    <input type="radio" name="age" id="age40" value="40" <%=ageChecked[3] %> />40대
		    <input type="radio" name="age" id="age50" value="50" <%=ageChecked[4] %> />50대
		    <input type="radio" name="age" id="age60" value="60" <%=ageChecked[5] %> />60대 이상
			<br /><br />
			  <br />
	        <p id="p_border">관심장르</p>
	          <br />
	        <div class="form-check form-check-inline">
				<input class="form-check-input" type="checkbox" name="genre"
					id="genre1" value="드라마" <%=interestsChecked[0] %>> <label class="form-check-label"
					for="genre1"  >드라마</label> 
				<input class="form-check-input"
					type="checkbox" name="genre" id="genre2" value="판타지" <%=interestsChecked[1] %>> <label
					class="form-check-label" for="genre2"  >판타지</label>
			 	<input
					class="form-check-input" type="checkbox" name="genre" id=genre3
					value="아동"  <%=interestsChecked[2] %>> <label class="form-check-label" for="genre3">아동</label>
				<input class="form-check-input" type="checkbox" name="genre"
					id="genre4" value="코미디" <%=interestsChecked[3] %>> <label class="form-check-label"
					for="genre4"  >코미디</label> 
								<br />  <br />
				<input class="form-check-input"
					type="checkbox" name="genre" id="genre5" value="어드벤처" <%=interestsChecked[4] %>> <label
					class="form-check-label" for="genre5"  >어드벤처</label> 
				<input
					class="form-check-input" type="checkbox" name="genre" id="genre6"
					value="가족"  <%=interestsChecked[5] %>> <label class="form-check-label" for="genre6" >가족</label>

				<input class="form-check-input" type="checkbox" name="genre"
					id="genre7" value="SF"  <%=interestsChecked[6] %>> <label class="form-check-label"
					for="genre7">SF</label> 
					
				
				<input class="form-check-input"
					type="checkbox" name="genre" id="genre8" value="뮤지컬" <%=interestsChecked[7] %>> <label
					class="form-check-label" for="genre8"  >뮤지컬</label> 
								<br />  <br />
				<input
					class="form-check-input" type="checkbox" name="genre" id="genre9"
					value="애니메이션"  <%=interestsChecked[8] %>> <label class="form-check-label" for="genre9">애니메이션</label>
				
				<input class="form-check-input" type="checkbox" name="genre"
					id="genre10" value="모험"  <%=interestsChecked[9] %>> <label class="form-check-label"
					for="genre10">모험</label> 
				<input class="form-check-input"
					type="checkbox" name="genre" id="genre11" value="액션"  <%=interestsChecked[10] %>> <label
					class="form-check-label" for="genre11">액션</label>
			</div>
			<br /><br />
	        <%-- <table>
	            <thead>
	                <tr>
	                    <td><input type="checkbox" name="genre" id="genre0" value="공포/호러" <%=genre[0] %>><label for=genre1>공포/호러</label></td>
	                    <td><input type="checkbox" name="genre" id="genre1" value="코미디" <%=genre[1] %>><label for="genre2">코미디</label></td>
	                    <td><input type="checkbox" name="genre" id="genre2" value="SF/판타지" <%=genre[2] %>><label for="genre3">SF/판타지</label></td>
	                </tr>
	                <tr>
	                    <td><input type="checkbox" name="genre" id="genre3" value="드라마" <%=genre[3] %>><label for="genre4">드라마</label></td>
	                    <td><input type="checkbox" name="genre" id="genre4" value="액션" <%=genre[4] %>><label for="genre5">액션</label></td>
	                    <td><input type="checkbox" name="genre" id="genre5" value="멜로" <%=genre[5] %>><label for="genre6">멜로</label></td>
	                </tr>
	            </thead>
	        </table>
	        <br><br> --%>
	        <span>Point</span>
	        <input type="number" name="point" id="point" value="<%=m.getPoint() %>" min="0" step="10" />
	        <br /><br />
	        
	  		<input type="submit" value="정보수정">
			<input type="reset" value="초기화">
			<input type="button" value="회원삭제" onclick="confirmDelete();">
	
	    </form>
	</div>


<script>
<%-- function confirmModify() {
	 var bool = confirm("수정하시겠습니까?");
	 if(bool){
		 location.href = "<%=request.getContextPath() %>/member/memberModify";
	 } 

	 
}
 --%>

function confirmDelete(){
    var bool = confirm("정말로 삭제하시겠습니까?");
    if(!bool) return;
    
    location.href = "<%=request.getContextPath() %>/admin/memberDelete?memberId=<%=m.getMemberId()%>";    	
    
    <%-- location.href = "<%=request.getContextPath() %>/admin/memberList"; --%>

}
</script>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>