<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

	<section id=enroll-container>
		<h2>비밀번호 변경</h2>
		<form 
			name="updatePwdFrm" 
			action="<%=request.getContextPath()%>/member/updatePassword" 
			method="post"
			id = "updatePwdFrm" >
			<table>
				<tr>
					<th>현재 비밀번호</th>
					<td><input type="password" name="password" id="password_" required></td>
				</tr>
				<tr>
					<th>변경할 비밀번호</th>
					<td>
						<input type="password" name="newPassword" id="newPassword" required>
					</td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td>	
						<input type="password" id="passwordCheck" required><br>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<input type="submit" value="변경"/>
					</td>
				</tr>
			</table>
		</form>
	</section>
<script>
$("#updatePwdFrm").submit(function(){
	console.log("updatePassword.jsp")
	var $password = $(password_);
	var $newPassword = $(newPassword);
	var $passwordCheck = $(passwordCheck);


	if(/^.{4,}$/.test($password.val()) == false){
		alert("유효한 비밀번호를 입력하세요")
		$password.select();
		return false;
	}
	
	if(/^.{4,}$/.test($newPassword.val()) == false){
		alert("새로운 비밀번호가 유요하지 않습니다.")
		$newPassword.select();
		return false;
	}
	
	if($newPassword.val() != $passwordCheck.val()){
		alert("비밀번호를 다시 확인해 주세요.")
		$passwordCheck.select();
		return false;
	}
});
</script>	
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
