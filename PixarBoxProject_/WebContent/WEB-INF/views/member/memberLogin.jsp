<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/member.css" />
	<script>
	function loginValidate() {
		var $loginId = $("#loginId");
		var $loginPwd = $("#loginPwd");
		
		if($loginId.val().trim().length == 0) {
			alert("아이디를 입력하세요.");
			$loginId.focus();
			return false;
		}
		if($loginPwd.val().trim().length == 0) {
			alert("비밀번호를 입력하세요.");
			$loginPwd.focus();
			return false;
		}
		
		return true;
	}
	
	function goMemberEnroll() {
		location.href = "<%=request.getContextPath() %>/member/memberEnroll";
	}
	
	</script>
<section id="content-member">
	<h2>로그인</h2>
	<div class="title">
		<hr>
	</div>
	<div id="loginFrm">
	    <form action="<%=request.getContextPath() %>/member/memberLoginCheck" id="loginForm" method="post" onsubmit="return loginValidate();">
	        <table>
	            <tr>
	                <td>
	                    <input type="text" name="loginId" id="loginId" placeholder="아이디를 입력하세요." value="<%=saveId?loginId:"" %>">
	                </td>
	                <td rowspan="2">
	                    <input type="submit" class="button login" value="로그인" tabindex="3" />
	                    <input type="button" class="button enroll" value="회원가입" onclick="goMemberEnroll();" />
	                </td>
	            </tr>
	            <tr>
	                <td>
	                    <input type="password" name="loginPwd" id="loginPwd" placeholder="비밀번호를 입력하세요.">
	                </td>
	            </tr>
	            <tr>
	            	<td colspan="2">
	            		<input type="checkbox" name="saveId" id="saveId" <%=saveId?"checked":"" %> />
	            		<label for="saveId">아이디 저장</label>
	            	</td>
	            </tr>
	        </table>
	    </form>
	</div>
</section>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>