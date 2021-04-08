<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!-- <link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous"> -->

<script src="<%=request.getContextPath() %>/js/jquery-3.4.1.js"></script>
<!-- <script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
	integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
	crossorigin="anonymous"></script> -->
	
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
	var $idValid = $("#idValid");
	if($idValid.val() == 2) {
		alert("아이디는 4글자 이상 가능합니다.");
		return false;
	}
	
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

$(function() {
	
	$("#memberId").on("keyup", function(e) {
		$("#checkId").remove();
		
		var memberId = $("#memberId").val().trim();
		//console.log(memberId);
		
		if(memberId.length == 0) return;
		
		$.ajax({
			url: "<%=request.getContextPath() %>/member/checkIdDuplicate",
			type: "post",
			data: {memberId: memberId},
			success: function(data) {
				//console.log(data[0].result);
				//console.log($("#idValid").val());
				
				let html = "";
				if(data[0].result==1) {
					html = "<span id='checkId' style='color: red'>이미 존재하는 아이디입니다.</span>";
					$("#idValid").attr("value", 0);
				}
				else if(data[0].result == 0 && memberId.length>=4) {
					html = "<span id='checkId'>사용가능한 아이디입니다.</span>";
					$("#idValid").attr("value", 1);
				}
				else if(data[0].result==0 && memberId.length<4) {
					html = "<span id='checkId' style='color: red'>아이디는 4자 이상 입력해주세요.</span>";
					$("#idValid").attr("value", 2);
				}
				
				//console.log($("#idValid").val());
				$("#checkIdDuplicate").html(html);	
			},
			error: function(x, s, e) {
				console.log("ajax실패!!", x, s, e);
			}
		});
	});
	//console.log($("#idValid").val());
});

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
	    <form action="memberEnrollEnd" name="memberEnrollForm" method="post"
            id="enrollForm" onsubmit="return enrollValidate();">
            <p id="p_border_left">기본정보</p>
            <input type="text" name="lastName" id="lastName" placeholder="성"
                required>&nbsp;&nbsp;&nbsp; <input type="text"
                name="firstName" id="firstName" placeholder="이름" required> <br>
            <br> <input type="text" name="memberId" id="memberId"
                placeholder="아이디" required>&nbsp;
            <div id="checkIdDuplicate"></div>
            <!-- <input type="button" value="중복검사" onclick="checkIdDuplicate();"> -->
            <input type="hidden" id="idValid" value="0" /> <br> <input
                type="password" name="password" id="password" placeholder="비밀번호"
                required> <br>
            <br> <input type="password" name="pwdChk" id="pwdChk"
                placeholder="비밀번호 확인" required> <br>
            <br> <input type="text" name="email" id="email"
                placeholder="이메일" required> @ <select name="emailAdd"
                id="emailAdd">
                <option value="hanmail.net">hanmail.net</option>
                <option value="daum.net">daum.net</option>
                <option value="naver.com">naver.com</option>
                <option value="gmail.com">gmail.com</option>
            </select> <br>
            <br> <input type="tel" name="phone1" id="phone1"
                placeholder="010" maxlength="3">&nbsp;- <input type="tel"
                name="phone2" id="phone2" placeholder="1234" maxlength="4">&nbsp;-
            <input type="tel" name="phone3" id="phone3" placeholder="5678"
                maxlength="4"> <br>
            <br>
            <p id="p_border_gender">
                성별 <input type="radio" name="gender" id="gender0" value="F" />여자 <input
                    type="radio" name="gender" id="gender1" value="M" />남자
            </p>
            <br />
            <br />
            <p id="p_border">연령</p>
            <input type="radio" name="age" id="age10" value="10" />10대&nbsp;
            <input type="radio" name="age" id="age20" value="20" />20대&nbsp;
            <input type="radio" name="age" id="age30" value="30" />30대&nbsp;
            <input type="radio" name="age" id="age40" value="40" />40대&nbsp;
            <input type="radio" name="age" id="age50" value="50" />50대&nbsp;
			<input type="radio" name="age" id="age60" value="60" />60대 이상 <br />
            <br />
            <br />
            <p id="p_border">관심장르</p>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" name="genre"
                    id=genre1 value="드라마"> <label class="form-check-label"
                    for="genre1">드라마</label> <input class="form-check-input"
                    type="checkbox" name="genre" id=genre2 value="판타지"> <label
                    class="form-check-label" for="genre2">판타지</label> <input
                    class="form-check-input" type="checkbox" name="genre" id=genre3
                    value="아동"> <label class="form-check-label" for="genre3">아동</label>
                <input class="form-check-input" type="checkbox" name="genre"
                    id=genre4 value="코미디"> <label class="form-check-label"
                    for="genre4">코미디</label> <br>
                    
                <br> <input class="form-check-input" type="checkbox"
                    name="genre" id=genre5 value="어드벤처"> <label
                    class="form-check-label" for="genre5">어드벤쳐</label> <input
                    class="form-check-input" type="checkbox" name="genre" id=genre6
                    value="가족"> <label class="form-check-label" for="genre6">가족</label>
                <input class="form-check-input" type="checkbox" name="genre"
                    id=genre7 value="SF"> <label class="form-check-label"
                    for="genre7">SF</label> <input class="form-check-input"
                    type="checkbox" name="genre" id=genre8 value="뮤지컬"> <label
                    class="form-check-label" for="genre8">뮤지컬</label> <br>
                <br> <input class="form-check-input" type="checkbox"
                    name="genre" id=genre9 value="애니메이션"> <label
                    class="form-check-label" for="genre9">애니메이션</label> <input
                    class="form-check-input" type="checkbox" name="genre" id=genre10
                    value="모험"> <label class="form-check-label" for="genre10">모험</label>
                <input class="form-check-input" type="checkbox" name="genre"
                    id=genre11 value="액션"> <label class="form-check-label"
                    for="genre11">액션</label>
            </div>
            <br />
            <br />
            <!-- 하은 22시 수정 style삭제 p태그 추가 오타,label추가 div삭제 148번줄부터 230줄까지 끝-->
	 <!--        <table>
	            <thead>
	                <tr>
	                    <td><input type="checkbox" name="genre" id="genre1" value="공포/호러"><label for=genre1>공포/호러</label></td>
	                    <td><input type="checkbox" name="genre" id="genre2" value="코미디"><label for="genre2">코미디</label></td>
	                    <td><input type="checkbox" name="genre" id="genre3" value="SF/판타지"><label for="genre3">SF/판타지</label></td>
	                    <td><input type="checkbox" name="genre" id="genre3" value="SF/판타지"><label for="genre3">SF/판타지</label></td>
	                </tr>
	                <tr>
	                    <td><input type="checkbox" name="genre" id="genre4" value="드라마"><label for="genre4">드라마</label></td>
	                    <td><input type="checkbox" name="genre" id="genre5" value="액션"><label for="genre5">액션</label></td>
	                    <td><input type="checkbox" name="genre" id="genre6" value="멜로"><label for="genre6">멜로</label></td>
	                    <td><input type="checkbox" name="genre" id="genre6" value="멜로"><label for="genre6">멜로</label></td>
	                </tr>
	                <tr>
	                    <td><input type="checkbox" name="genre" id="genre4" value="드라마"><label for="genre4">드라마</label></td>
	                    <td><input type="checkbox" name="genre" id="genre5" value="액션"><label for="genre5">액션</label></td>
	                    <td><input type="checkbox" name="genre" id="genre6" value="멜로"><label for="genre6">멜로</label></td>
	                </tr>
	            </thead>
	        </table> -->
	        <input type="submit" value="회원가입" />
	
	    </form>
	</div>
</section>	
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
