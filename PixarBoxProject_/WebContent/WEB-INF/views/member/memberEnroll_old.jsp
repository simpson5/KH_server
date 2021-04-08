<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<script>
$(()=> {
	/* 비밀번호 일치여부 */
	$("#pwdChk").blur(function() {
		var $pwd1 = $("#password");
		var $pwd2 = $("#pwdChk");
		
		if($pwd1.val() != $pwd2.val()) {
			alert("패스워드가 일치하지 않습니다.");
			$pwd1.val('').focus();
			$pwd2.val('');
		}
	});
});

/* 회원가입 유효성 검사 */
function enrollValidate() {
	var $memberId = $("#memberId");
	if($memberId.val().trim().length < 4) {
		alert("아이디는 4글자 이상 가능합니다.");
		return;
	}
	
	var $idValid = $("#idValid");
	if($idValid.val() == 0) {
		alert("아이디 중복검사 해주세요.");
		return false;
	}
	
	return true;
}

/* 아이디 중복검사 함수: 팝업창 */
function checkIdDuplicate() {
	var $memberId = $("#memberId");
	//유효성검사
	if($memberId.val().trim().length < 4) {
		alert("아이디는 4글자 이상이어야 합니다.");
		return;
	}
	
	//폼을 팝업창에 제출
	//frm.target = 팝업창이름
	var url = "<%=request.getContextPath() %>/member/checkIdDuplicate";
	var title = "checkIdDuplicatePopup";
	
	var width = 400;
    var height = 200;
    var screenWidth = screen.availWidth;
    var screenHeight = screen.availHeight;
    
	var spec = "left="+(screenWidth-width)/2+", top="+(screenHeight-height)/2+", width="+width+", height="+height;
	
	open(url,title, spec);
	
	var popup = open("", title, spec);
	
	var frm = document.checkIdDuplicateFrm;
	frm.action = url;
	frm.target = title;
	frm.method = "POST";
	frm.memberId.value = $memberId.val().trim();
	frm.submit();
}

</script>
<!-- css -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/member.css" />	

<section id="content-member">	
	<!-- 아이디 중복검사 폼 -->
	<form name="checkIdDuplicateFrm">
		<input type="hidden" name="memberId" />
	</form>
	
	<h2>회원가입</h2>
	<div class="title">
	    <hr>
	</div>
	<div id="enrollFrm">
	    <br><br><br>
	    <form action="memberEnrollEnd" name="memberEnrollForm" method="post" id="enrollForm" onsubmit="return enrollValidate();" >
	        <p>기본정보</p>
	        <input type="text" name="lastName" id="lastName" placeholder="성" style="width: 50px;" required>&nbsp;&nbsp;&nbsp;
	        <input type="text" name="firstName" id="firstName" placeholder="이름" style="width: 150px;" required>
	        <br><br>
	        <input type="text" name="memberId" id="memberId" placeholder="아이디" style="width: 230px;" required>&nbsp;
	        <input type="button" value="중복검사" onclick="checkIdDuplicate();">
	        <input type="hidden" id="idValid" value="0" />
	        <br><br>
	        <input type="password" name="password" id="password" placeholder="비밀번호" style="width: 230px;" required>
	        <br><br>
	        <input type="password" name="pwdChk" id="pwdChk" placeholder="비밀번호 확인" style="width: 230px;" required>
	        <br><br>
	        <input type="text" name="email" id="email" placeholder="이메일" style="width: 100px;" required>
	        @
	        <select name="emailAdd" id="emailAdd" style="width: 105px;">
	            <option value="hanmail.net">hanmail.net</option>
	            <option value="daum.net">daum.net</option>
	            <option value="naver.com">naver.com</option>
	            <option value="gmail.com">gmail.com</option>
	        </select>
	        <br><br>
	        <input type="tel" name="phone1" id="phone1" placeholder="010" maxlength="3" style="width: 50px;">&nbsp;-
	        <input type="tel" name="phone2" id="phone2" placeholder="1234" maxlength="4" style="width: 70px;">&nbsp;-
	        <input type="tel" name="phone3" id="phone3" placeholder="5678" maxlength="4" style="width: 70px;">
	        <br><br>
	        <p>관심장르</p>
	        <table>
	            <thead>
	                <tr>
	                    <td><input type="checkbox" name="genre" id="genre1" value="공포/호러"><label for=genre1>공포/호러</label></td>
	                    <td><input type="checkbox" name="genre" id="genre2" value="코미디"><label for="genre2">코미디</label></td>
	                    <td><input type="checkbox" name="genre" id="genre3" value="SF/판타지"><label for="genre3">SF/판타지</label></td>
	                </tr>
	                <tr>
	                    <td><input type="checkbox" name="genre" id="genre4" value="드라마"><label for="genre4">드라마</label></td>
	                    <td><input type="checkbox" name="genre" id="genre5" value="액션"><label for="genre5">액션</label></td>
	                    <td><input type="checkbox" name="genre" id="genre6" value="멜로"><label for="genre6">멜로</label></td>
	                </tr>
	            </thead>
	        </table>
	        <input type="submit" value="회원가입" />
	
	    </form>
	</div>
</section>	
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
