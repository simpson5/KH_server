<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Date"%>
<%@page import="com.simp.member.model.vo.Member" %> 
<%@page import="com.simp.member.model.dao.MemberDao"%>
<%@page import="com.simp.member.model.service.MemberService" %> 
<%  List<Member> list = (List<Member>)request.getAttribute("list"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리</title>
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
  </head>
 <body>
 <style>
 body {
  background-color: gray;
}

 </style>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/calculate_board.css" />
<section id=member-container>
	<h1>회원 정보</h1>	 
		  <table id="tbl-member">
		
			<tr align = "center">
				<th>아이디</th>
				<th>이름</th>
				<th>주민번호</th>
				<th>이메일</th>
				<th>전화번호</th>
				<th>주소</th>
			    <th>회원등급</th>
				<th>회원상태</th>
				<th>회원상태변경</th>
			</tr>
			  <% for(Member m : list) { %>
			<tr>
		  	<form name="MemberStatusUpdateFrm" 
				  action="<%=request.getContextPath() %>/member/memberUpdate"
				  method="post"
				  id="MemberStatusUpdateFrm">
				<td><input type="text"  class="member" name="memberId"   id="memberId" size="10"    value="<%= m.getMember_id() %>" ></td>
		        <td><input type="text"  class="member" name="memberName" id="memberName" size="5" value="<%= m.getMember_name() %>" ></td>
		        <td><input type="text"  class="member" name="memberSsn" id="memberSsn"  size="5" value="<%= m.getMember_ssn() %>"></td>
			    <td><input type="text"  class="member" name="memberEmail" id="memberEmail" size="10"  value="<%= m.getEmail() %>"></td>
			    <td><input type="text"  class="member" name="memberPhone" id="memberPhone" size="9"  value="<%= m.getPhone() %>"></td>
			    <td><input type="text"  class="member" name="memberAddress" id="memberAddress" size="35"  value="<%= m.getAddress() %>"></td>
			    <td><p><%= m.getMember_grade() %></p></td> 
			    <td>		
	         		<select name="member_status" id="status">
						<option value="O">활성</option>
	    				<option value="B">차단</option>
					</select>	
				</td>
				<td> <button class="update_btn" value="상태변경" >회원상태변경</button></td>
			</form>
			</tr>
			<% } %>
		</table>
</section>
<script>

//회원상태 수정
/*$(".update_btn").click(function(){
	var updateFrm = $("#memberUpdateFrm");
	var mvStatus =$("#status").val();
	updateFrm.find("[name=status]").val(mvStatus);
	console.log(updateFrm.find("[name=status]").val()); 
	updateFrm.submit();
});*/





</script>

</body>
</html>