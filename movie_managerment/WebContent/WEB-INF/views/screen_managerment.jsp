<%@page import="com.simp.movie.model.vo.ScreenInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
List<ScreenInfo> list = (List<ScreenInfo>)request.getAttribute("list");
%> 
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/screen_managerment.css" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상영 관리</title>
 <script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
</head>
<style>
th, td {
  text-align: center;
}

</style>
<body>
<div class="total_content">
	<h1>상영 관리</h1>
	<form action="<%= request.getContextPath()%>/movie/screen_find"
		  method="GET"
		  class="search_frm">
		<label for="search_title">검색창</label>
		<input type="text" name="search_title" id="search_title" />
		<input type="submit" class="search_frm_btn" value="검색" />
	</form>	
	
	<table>
					 
		<tr>
			<th>상영번호</th>
			<th>제목</th>
			<th>상영관</th>
			<th>판매된 티켓</th>
			<th>총 티켓</th>
			<th>판매율</th>
			<th>상영상태</th>
			<th>수정하기</th>
		</tr>
<% if(list != null) { %>
	<% for(ScreenInfo si : list) { %>		
		<tr>
			<td><%= si.getScreenNo() %></td>
			<td><%= si.getMovieTitle() %></td>
			<td><%= si.getTheaterNo() %></td>
			<td><%= si.getSoldTicket() %></td>
			<td><%= si.getTotalTicket() %></td>
			<td><%= si.getSoldTicket() * 100.0 / si.getTotalTicket() + "%" %></td>
			<td>
			<form name="ScreenMovieUpdateFrm" 
					  action="<%=request.getContextPath() %>/movie/screenMovieStatus"
					  method="post"
					  id="ScreenMovieUpdateFrm">
					<input type="hidden" name="screen_no2" value= <%= si.getScreenNo() %> />
	         		<select name="screen_status" id="status">
						<option value="O" <%= si.getStatus().equals("O") ? "selected" : "" %>>상영</option>
		    			<option value="X" <%= si.getStatus().equals("X") ? "selected" : "" %>>상영중단</option>
					</select>	
         		<td><input type="submit" value="수정하기" class="update_btn"/></td>
         	<!-- <td><button class="update_btn" value="수정하기">수정하기</button></td> -->
         	</form>
	        </td>
		</tr>
		
	<% } %>
<% } %>

	</table>
</div>

<script>
//영화정보 수정
/* $(".update_btn").click(function(){
	var updateFrm = $("#ScreenMovieUpdateFrm");
	

	var mvStatus =$("#status").val();
   
	updateFrm.find("[name=screen_status]").val(mvStatus);
	
	
	console.log(updateFrm.find("[name=screen_status]").val()); 
	
	updateFrm.submit();
	
}); */
</script>
</body>
</html>