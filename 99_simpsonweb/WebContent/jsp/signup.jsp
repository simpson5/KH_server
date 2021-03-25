<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.simpson.model.vo.Member" %>
<%
	Member member = (Member)request.getAttribute("member");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1><%= member.getName() %> 님의 회원가입이 성공하셨습니다.</h1>
	<%= member.toString() %>
</body>
</html>