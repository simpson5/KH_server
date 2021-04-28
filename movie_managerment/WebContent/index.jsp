<%@page import="com.simp.movie.model.vo.Movie"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String msg = (String)session.getAttribute("msg");
//세션에서 msg를 지워버린다. 하지만 html msg는 남아있다.
if(msg != null) session.removeAttribute("msg");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/index_manager.css">
    <title>Simpson The Moive Manager Page</title>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
        <div id="btnall">
            <div class=btns>
                <span class="btn-group1">
                    <button class=btngroup1 onclick="location.href='<%= request.getContextPath()%>/movie/search';">영화등록</button>
                    <button class=btngroup1 onclick="location.href='<%= request.getContextPath()%>/movie/screen_managerment';">상영 관리</button>
                </span>
                <br>
                <br>
                <span class="btn-group2">
                    <button onclick="location.href='<%= request.getContextPath()%>/movie/managerment';">현재 상영작 조회</button>
                    <button onclick="location.href='<%= request.getContextPath()%>/product';">상품관리</button>
                </span>
                <br>
                <br>
                <span>
                	<button onclick="location.href='<%= request.getContextPath()%>/movie/banner';">배너관리</button>
                	<button onclick="location.href='<%= request.getContextPath()%>/member/memberlist';">회원관리</button>
                </span>
                <br>
                <br>
                <span clss="btn-group3">
                    <button onclick="location.href='<%= request.getContextPath()%>/calculate';">매출관리</button>
                    <button onclick="location.href='http://localhost:9090/movie_reservation/';">클라이언트페이지</button>
                </span>
            </div>
        </div>
</body>
<script>
<% if(msg != null) { %>
swal ( "Oops" ,  "<%= msg %>" ,  "error" )
<% } %>
</script>
</html>
