<%@page import="com.simp.movie.model.vo.Banner"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
List<Banner> list = (List<Banner>)request.getAttribute("list");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/movie_managerment.css">
</head>
<body>
<div class="total_content">
<h1>상영 영화 관리</h1>
<div class="main_container">
<table>
	<tr class="head">
		<td style="width:80px;">배너 번호</td>
		<td>배너 주소</td>
		<td>수정</td>
	</tr>
<% if(list != null) { %>
	<% for(Banner b : list) { %>		
	<tr>
		<form action="<%= request.getContextPath()%>/movie/bannerUpdate"
			  method="GET">
			<td>
				<input type="hidden" name="no" value="<%= b.getNo() %>" required/>
				<span><%= b.getNo() %></span>
			<td>	
				<input type="text" name="poster" value="<%= b.getPoster() %>"/>
			</td>
			<td>
				<input type="submit" value="수정하기" />
			</td>
		</form>
	</tr>
	<% } %>
<% } %>
</table>
<br />
</div>
</div>
</html>