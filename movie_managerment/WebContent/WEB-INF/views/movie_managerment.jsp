<%@page import="com.simp.movie.model.vo.ScreenMovie"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
List<ScreenMovie> list = (List<ScreenMovie>)request.getAttribute("list");
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
		<td>영화제목</td>
		<td>상영정보</td>
		<td>등록</td>
		<td>수정</td>
	</tr>
<% if(list != null) { %>
	<% for(ScreenMovie m : list) { %>		
	<tr>
		<td><%= m.getMovieTitle() %></td>
		<td><button onclick="location.href='<%= request.getContextPath()%>/movie/screen_managerment';">상영정보</button></td>
		<td>
			<form action="<%= request.getContextPath()%>/movie/screenEnroll"
			      method="GET">
				<input type="hidden" name="movie_title" value="<%= m.getMovieTitle() %>"/>
				<input type="submit" value="상영등록" />
			</form>
		</td>
		<td>
			<form action="<%= request.getContextPath()%>/movie/movieUpdate"
			      method="GET">
				<input type="hidden" name="movie_title" value="<%= m.getMovieTitle() %>"/>
				<input type="submit" value="영화정보수정" />	
			</form>
		</td>
	</tr>
	<% } %>
<% } %>
</table>
<br />
</div>
</div>
</body>
</html>