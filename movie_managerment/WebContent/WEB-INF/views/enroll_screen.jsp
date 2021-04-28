
<%@page import="com.simp.movie.model.vo.ScreenMovie"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ScreenMovie sm = (ScreenMovie)request.getAttribute("screenMovie");
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=2, initial-scale=1.0" />
    <title>Document</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/screen_movie.css" />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
    />
    <script src="js/jquery-3.6.0.js"></script>
  </head>
  <body>
    <div class="total_content">
      <h1>상영 영화 등록</h1>
      <div class="main_container">
        <div class="movie_info">
            <div class="movie">
                <div class="poster">
                  <img
                    src="<%= request.getContextPath() %>/upload/movie/<%= sm.getMainPoster() %>"
                    alt="<%= sm.getMainPoster() %>"
                  />
                </div>
                <div class="info">
                  <p class="title"><%= sm.getMovieTitle() %></p>
                </div>
            </div>
        </div>
        <div class="check_list">
         <div class="selector_box">
           <form action="<%= request.getContextPath() %>/movie/screenEnroll"
				  name="screenEnrollFrm"
				  method="POST">
           	 <table>
           	 	<tr>
           	 		<td>
           	 			<input type="hidden" name="movie_title" value="<%= sm.getMovieTitle() %>" />
           	 			<label for="theater">상영관선택</label>
           	 			<select id="theater" name="theater">
                   			<option value="상영관1">상영관1</option>
                   			<option value="상영관2">상영관2</option>
               			    <option value="상영관3">상영관3</option>
                			<option value="상영관4">상영관4</option>
                   			<option value="상영관5">상영관5</option>
               			 </select>
           	 		</td>
           	 		<td>
           	 			<label for="screen_date">상영일</label>
           	 			<input type="date" name="screen_date" value="xxx" min="yyy">
           	 		</td>
           	 	</tr>
           	 	<tr>
           	 		<td>
           	 			<label for="start_time">시작시간</label>
           	 			<input type="time" name="start_time" value="xxx" min="yyy" max="zzz">
           	 		</td>
           	 		<td>
           	 			<label for="end_time">종료시간</label>
           	 			<input type="time" name="end_time" value="xxx" min="yyy" max="zzz">
           	 		</td>
           	 	</tr>
           	 	<p class="screen_price">
	                              가격 : 
	                  <input
	                      type="number"
	                      name="screen_price"
	                      id="screen_price"
	                      min="10000"
	                      value="10000"
	                      step="1000">
	              </p>
           	 	<tr>
           	 		<td>
           	 			<input type="submit" value="상영 등록" />
           	 		</td>
           	 	</tr>
           	 </table>
           </form>
         </div>
        </div>
      </div>
    </div>
  </body>
</html>
