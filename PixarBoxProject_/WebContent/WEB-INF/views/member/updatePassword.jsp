<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String memberId = request.getParameter("memberId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<%=request.getContextPath() %>/js/jquery-3.4.1.js"></script>
<style>
	form [type=password]{
	width: 150px;
	height: 20px;
	}
	
	div#updatePassword-container{
		border: 1px solid #35256b;
		height: 455px;
	}
	
	div#updatePassword-container table {
		margin:0 auto; 
		padding-top:120px;
		border-spacing: 20px;
		font-family: 'Nanum Gothic Coding', monospace;
	}
	div#updatePassword-container table tr:last-of-type td {
			text-align:center;
		}
		
	form [type=submit]{
	    border: 1px solid #35256b; 
	    background-color: #261049; 
	    color: white; 
	    margin-left:30px;
	    margin-top:20px;
	    width:100px;
	    height:35px;
	    font-size:12px;
		
	}
	form [type=button]{
	    border: 1px solid #35256b; 
	    background-color: #261049; 
	    color: white; 
	    margin-left:-1px;
	    margin-top:-20px;
	    width:100px;
	    height:35px;
	    font-size:12px;	
	}
	
	form [type=submit]:hover{
	color:#white; 
	background-color: black;
	}
	
	form [type=button]:hover{ 
	color:#white; 
	background-color: black; 
	}
</style>
<script>
	function password_validate() {
		var $pwd_new = $("#password_new").val().trim();
		var $pwd_chk = $("#password_chk").val().trim();
		
		if($pwd_new != $pwd_chk) {
			alert("패스워드가 일치하지 않습니다.");
			$("#password_new").select();
			return false;
		}
		
		return true;
	}
</script>
</head>
<body>
    <div id="updatePassword-container">
		<form name="updatePwdFrm" action="<%=request.getContextPath()%>/member/updatePasswordEnd" method="post" >
			<table>
				<tr>
					<th>현재 비밀번호</th>
					<td><input type="password" name="password" id="password" required></td>
				</tr>
				<tr>
					<th>변경할 비밀번호</th>
					<td>
						<input type="password" name="password_new" id="password_new" required>
					</td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td>	
						<input type="password" id="password_chk" required><br>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" onclick="return password_validate();" value="변경" />&nbsp;
						<input type="button" onclick="self.close();" value="닫기" />						
					</td>
				</tr>
			</table>
			<input type="hidden" name="memberId" value="<%=memberId %>" />
		</form>
	</div>
</body>
</html>