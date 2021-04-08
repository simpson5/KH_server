<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/mypageHeader.jsp" %>
<%
	Member m = (Member)request.getAttribute("member");
	
	//이메일 셀렉티드처리
	String emailSelected[] = {"", "", "", ""};
	String email[] = null;
	
	if(m.getEmail() != null) {
		email = m.getEmail().split("@");
		
		if("hanmail.net".equals(email[1])) emailSelected[0] = "selected";
		else if("daum.net".equals(email[1])) emailSelected[1] = "selected";
		else if("naver.com".equals(email[1])) emailSelected[2] = "selected";
		else if("gmail.com".equals(email[1])) emailSelected[3] = "selected";
		//System.out.println(email[1]);
		/* for(String e : email) {
			switch(e) {
			case "hanmail.net": emailSelected[0] = "selected"; break;
			case "daum.net": emailSelected[1] = "selected"; break;
			case "naver.com": emailSelected[2] = "selected"; break;
			case "gmail.com": emailSelected[3] = "selected"; break;
			}
		} */
	}
	
	//관심장르 체크드처리
	String interestsChecked[] = {"", "", "", "", "", ""};
	
	if(m.getInterests() != null) {
		String interests[] = m.getInterests().split(",");
		
		for(String i : interests) {
			switch(i) {
			case "공포/호러": interestsChecked[0] = "checked"; break;
			case "코미디": interestsChecked[1] = "checked"; break;
			case "SF/판타지": interestsChecked[2] = "checked"; break;
			case "드라마": interestsChecked[3] = "checked"; break;
			case "액션": interestsChecked[4] = "checked"; break;
			case "멜로": interestsChecked[5] = "checked"; break;
			}
		}
	}
%>

<!-- css -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/member.css" />	
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mypage.css" />	

<section id="content-member">
	<h2>정보수정</h2>
	<div class="title">
	    <hr>
	</div>
	<div id="enrollFrm">
	    <br><br><br>
	    <form action="<%=request.getContextPath() %>/member/memberUpdate" name="memberUpdateForm" method="post" id="enrollForm" onsubmit="return updateValidate();" >
	        <p>기본정보</p>
	        <input type="text" name="lastName" id="lastName" placeholder="성" style="width: 50px;" value="<%=m.getLastName() %>" required>&nbsp;&nbsp;&nbsp;
	        <input type="text" name="firstName" id="firstName" placeholder="이름" style="width: 150px;" value="<%=m.getFirstName() %>" required>
	        <br><br>
	        <input type="text" name="memberId" id="memberId" style="width: 230px;" value="<%=m.getMemberId() %>" required readonly>&nbsp;
	        <input type="hidden" id="idValid" value="0" />
	        <br><br>
	        <input type="button" value="비밀번호 변경" onclick="updatePassword();" />
	        <br><br>
	        <input type="text" name="email" id="email" placeholder="이메일" style="width: 100px;" value="<%=email[0] %>" required>
	        @
	        <select name="emailAdd" id="emailAdd" style="width: 105px;">
	            <option value="hanmail.net" <%=emailSelected[0] %>>hanmail.net</option>
	            <option value="daum.net" <%=emailSelected[1] %>>daum.net</option>
	            <option value="naver.com" <%=emailSelected[2] %>>naver.com</option>
	            <option value="gmail.com" <%=emailSelected[3] %>>gmail.com</option>
	        </select>
	        <br><br>
	        <input type="tel" name="phone1" id="phone1" placeholder="010" maxlength="3" style="width: 50px;" value="010" required>&nbsp;-
	        <input type="tel" name="phone2" id="phone2" placeholder="1234" maxlength="4" style="width: 70px;" value="<%=m.getPhone().substring(3, 7) %>" required>&nbsp;-
	        <input type="tel" name="phone3" id="phone3" placeholder="5678" maxlength="4" style="width: 70px;" value="<%=m.getPhone().substring(7) %>" required>
	        <br><br>
	        <p>관심장르</p>
	        <table>
	            <thead>
	                <tr>
	                    <td><input type="checkbox" name="genre" id="genre1" value="공포/호러" <%=interestsChecked[0] %>><label for=genre1>공포/호러</label></td>
	                    <td><input type="checkbox" name="genre" id="genre2" value="코미디" <%=interestsChecked[1] %>><label for="genre2">코미디</label></td>
	                    <td><input type="checkbox" name="genre" id="genre3" value="SF/판타지" <%=interestsChecked[2] %>><label for="genre3">SF/판타지</label></td>
	                </tr>
	                <tr>
	                    <td><input type="checkbox" name="genre" id="genre4" value="드라마" <%=interestsChecked[3] %>><label for="genre4">드라마</label></td>
	                    <td><input type="checkbox" name="genre" id="genre5" value="액션" <%=interestsChecked[4] %>><label for="genre5">액션</label></td>
	                    <td><input type="checkbox" name="genre" id="genre6" value="멜로" <%=interestsChecked[5] %>><label for="genre6">멜로</label></td>
	                </tr>
	            </thead>
	        </table>
	        <input type="hidden" name="point" value="<%=memberLoggedIn.getPoint() %>" />
	        <input type="submit" value="정보수정" />
	        <input type="reset" value="초기화" />
	        <input type="button" value="탈퇴" onclick="confirmDelete();" />
	
	    </form>
	</div>
</section>
	<script>
	function updatePassword() {
		var url = "<%=request.getContextPath() %>/member/updatePassword?memberId=<%=m.getMemberId() %>";
		var title = "updatePasswordPopup";
		
		var width = 500;
        var height = 300;
        var screenWidth = screen.availWidth;
        var screenHeight = screen.availHeight;
        
		var spec = "left="+(screenWidth-width)/2+", top="+(screenHeight-height)/2+", width="+width+", height="+height;
		
		open(url,title, spec);
	}
	
	function confirmDelete() {
		if(confirm("정말로 탈퇴하시겠습니까?"))
			location.href = "<%=request.getContextPath() %>/member/memberDelete?memberId=<%=m.getMemberId() %>";
	}
	</script>
	
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
