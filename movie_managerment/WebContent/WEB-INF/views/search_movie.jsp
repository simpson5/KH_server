<%@page import="com.simp.movie.model.vo.Movie"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
List<Movie> list = (List<Movie>)request.getAttribute("list");
String msg = (String)session.getAttribute("msg");
//세션에서 msg를 지워버린다. 하지만 html msg는 남아있다.
if(msg != null) session.removeAttribute("msg");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>상영 영화 등록</title>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/search_movie.css" />
<link
  rel="stylesheet"
  href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
/>
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
  </head>
  <body>
    <div class="total_content">
      <h1>상영 영화 등록</h1>
      <div class="main_container">
        <div class="search_container">
          <form action="<%=request.getContextPath()%>/movie/search" method="GET">
			<input type="text" name="search_word" id="search_word" placeholder="영화검색"/>
            <input type="submit" class="movie_search_btn" value="검색" />
		  </form>
        </div>
        <div class="search-result">
          <table>
<% 
	if( list != null) {
		for(Movie m : list) {
%>
              <tr class="movie">
                <td>
                	<div class="poster">
                 	 <img
                   		src="<%= m.getImage() %>"
                    	alt=""/>
                	</div>
                </td>
                <td>
                	<div class="info">
                  		<p class="title"><a href="#"><%= m.getTitle() %></a></p>
                  		<p class="opening_date"><%= m.getPubDate() %></p>
                  		<p>
                    		<span>감독 : <span></span class="director"><%= m.getDirector() %></span>
                  		</p>
                  		<p>
                  			<span>출연 : <span>
                     		</span class="actor"><%= m.getActor() %></span>
                  		</p>
                  		<form action="<%= request.getContextPath() %>/movie/movieEnroll"
							  name="movieEnrollFrm"
							  method="GET">
								<input type="hidden" name="img" value="<%= m.getImage() %>"/>
								<input type="hidden" name="title" value="<%= m.getTitle() %>"/>
								<input type="hidden" name="opening_date" value="<%= m.getPubDate() %>"/>
								<input type="hidden" name="director" value="<%= m.getDirector() %>"/>
								<input type="hidden" name="actor" value="<%= m.getActor() %>"/>
                  		<p><input type="submit" value="등록하기" /></p>
						</form>
                	</div>
                </td>
            </tr>
	<%	} %>
<%	} %>
          </table>
        </div>
      </div>
      <div class="page_bar"><%= request.getAttribute("pageBar") != null ? request.getAttribute("pageBar") : "" %></div>
    </div>
  </body>
 <script>
<% if(msg != null) { %>
swal("영화 등록 에러","<%= msg %>","error");
<% } %>
</script>
</html>
