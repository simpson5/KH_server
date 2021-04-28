<%@page import="com.simp.movie.model.service.MovieService"%>
<%@page import="java.util.List"%>
<%@page import="com.simp.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
Member loginMember = (Member)session.getAttribute("loginMember");
List<String> dipList = null;

if(loginMember != null){
	dipList = new MovieService().dipMovieList(loginMember.getMember_id());
} else {
	dipList = null;
}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>The Simpson Movie</title>
<link rel="stylesheet"
	href="<%= request.getContextPath() %>/css/header.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>

<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"> </script>
<!-- 경고메세지부분 -->
<script>
<%
String msg_cart = (String)session.getAttribute("msg_cart");
//세션에서 msg를 지워버린다. 하지만 html msg는 남아있다.
if(msg_cart != null) session.removeAttribute("msg_cart");
if(msg_cart != null) {
%>
window.onload = function(){
	swal("<%= msg_cart %>","<%= msg_cart %>","error");	
}
<% } %>
<%
String msg = (String)session.getAttribute("msg");
//세션에서 msg를 지워버린다. 하지만 html msg는 남아있다.
if(msg != null) session.removeAttribute("msg");
if(msg != null) {
%>
window.onload = function(){
	swal("<%= msg %>","<%= msg %>","error");	
}
<% } %>
</script>
</html>
</script>
</head>
<body>
	<!-- header 부분 -->
	<div class="header_common">
		<div class="title">
			<h1>
				<img src="<%= request.getContextPath() %>/img/logo.png" alt="로고"
					onclick="location.href='<%= request.getContextPath()%>';" />
			</h1>
			<div class="title-menu">
				<span class="title-menu-list" style="color:black">고객센터</span>
				<% if(loginMember == null) { %>
				<span class="title-menu-list"><a
					href="<%= request.getContextPath()%>/member/login">로그인</a></span>
				<% } else { %>
				<span class="title-menu-list"><a href="#" onclick="mypage();">마이페이지</a></span>
				<span class="title-menu-list"><a
					href="<%= request.getContextPath()%>/member/logout">로그아웃</a></span>
				<% } %>
			</div>
		</div>
		<hr>
		<div class="main-menu">
			<div style="display: inline-block;">
				<ul style="padding: 0;">
					<li><a href="<%= request.getContextPath()%>/movie/screenmovie">영화</a></li>
					<li><a href="<%= request.getContextPath()%>/theater/dolby">영화관</a></li>
					<li><a href="<%= request.getContextPath()%>/product/productList">스토어</a></li>
					<li><a href="<%= request.getContextPath() %>/review">리뷰게시판</a></li>
				</ul>
			</div>
		</div>
	</div>
	<!-- scroll_menu -->
	<div class="scroll_menu">
		<div class="scroll_box">
			<ul>
				<li><a href="<%= request.getContextPath()%>/movie/screenmovie">영화</a></li>
				<li><a href="<%= request.getContextPath()%>/theater/dolby">영화관</a></li>
				<li><a href="<%= request.getContextPath()%>/product/productList">스토어</a></li>
				<li><a href="<%= request.getContextPath() %>/review">리뷰게시판</a></li>
			</ul>
		</div>
	</div>
	<style>
.title-menu a:link {
	text-decoration: none;
}

.title-menu a:visited {
	text-decoration: none;
	color:black;
}

. title-menua:hover {
	text-decoration: none;
}
</style>
	<script>
  
  function mypage(){
	 <% if(loginMember == null) { %>
	  swal("로그인 후 사용하십시오","로그인 후 사용가능한 서비스 입니다.","warning");
	 <% } else { %>
	  location.href = "<%= request.getContextPath()%>/member/mypage";
	 <% } %>
  }
  
  </script>