<%@page import="com.simp.movie.model.vo.Movie"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Movie m = (Movie)request.getAttribute("movie");
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=2, initial-scale=1.0" />
    <title>Document</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/enroll_movie.css" />
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
        <div class="movie_info">
            <div class="movie">
                <table>
                	<tr>
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
				                <p>
				                  <span class="opening_date"><%= m.getPubDate() %></span>
				                </p>
				                <p>
				                  <span>감독 : <span></span class="director"><%= m.getDirector() %></span>
				                  <span>출연 : <span>
				                    </span class="actor"><%= m.getActor() %></span>
				                </p>
				        	</div>
                		</td>
                	</tr>
                </table>
            </div>
        </div>
        <div class="additional_infor">
         	<form action="<%= request.getContextPath() %>/movie/movieEnroll"
				  name="movieEnrollFrm"
				  method="POST"
				  enctype="multipart/form-data">
         		<table>
         			<tr>
         				<td><label for="main_poster">메인포스터 : </label></td>
         				<td><input type="file" name="main_poster"></td>
         			</tr>
         			<tr>
         				<td><label for="poster">포스터 : </label></td>
         				<td><input type="file" name="poster" id="" />	</td>
         			</tr>
         			<tr>
         				<td><label for="steel_cut">스틸컷 : </label></td>
         				<td><input multiple="multiple" type="file" name="steel_cut" id="" /></td>
         			</tr>
         			<tr>
         				<td><label for="trailer">트레일러 : </label></td>
         				<td><input type="text" name="trailer" id="" /></td>
         			</tr>
         			<tr>
         				<td><label for="synopsis">줄거리 : </label></td>
         				<td><textarea rows="5" cols="40" name="synopsis"></textarea></td>
         			</tr>
         			<tr>
         				<td><label for="screening_rate">상영등급 : </label></td>
         				<td>
         					<select id="theater" name="screening_rate">
	                   			<option value="A">전체</option>
	                   			<option value="B">12세</option>
	               			    <option value="C">15세</option>
	                			<option value="R">청소년 관람 불가</option>
               			 	</select>
         				</td>
         			</tr>
         			<tr>
         				<td>장르</td>
         				<td>
							<input type="checkbox" name="genre"  value="드라마"><label for="genre">드라마</label>
							<input type="checkbox" name="genre"  value="판타지"><label for="genre">판타지</label>
							<input type="checkbox" name="genre"  value="서부"><label for="genre">서부</label>
							<input type="checkbox" name="genre"  value="공포"><label for="genre">공포</label>
							<input type="checkbox" name="genre"  value="로맨스"><label for="genre">로맨스</label>
							<br />
							<input type="checkbox" name="genre"  value="스릴러"><label for="genre">스릴러</label>
							<input type="checkbox" name="genre"  value="컬트"><label for="genre">컬트</label>
							<input type="checkbox" name="genre"  value="다큐멘터리"><label for="genre">다큐멘터리</label>
							<input type="checkbox" name="genre"  value="코미디"><label for="genre">코미디</label>
							<input type="checkbox" name="genre"  value="가족"><label for="genre">가족</label>
							<br />
							<input type="checkbox" name="genre"  value="미스터리"><label for="genre">미스터리</label>
							<input type="checkbox" name="genre"  value="전쟁"><label for="genre">전쟁</label>
							<input type="checkbox" name="genre"  value="애니메이션"><label for="genre">애니메이션</label>
							<input type="checkbox" name="genre"  value="범죄"><label for="genre">범죄</label>
							<input type="checkbox" name="genre"  value="뮤지컬"><label for="genre">뮤지컬</label>
							<br>
							<input type="checkbox" name="genre"  value="SF"><label for="genre">SF</label>
							<input type="checkbox" name="genre"  value="액션"><label for="genre">액션</label>
						</td>
         			</tr>
         			<tr>
						<input type="hidden" name="title" value="<%= m.getTitle() %>"/>
         				<input type="hidden" name="img" value="<%= m.getImage() %>"/>
						<input type="hidden" name="opening_date" value="<%= m.getPubDate() %>"/>
						<input type="hidden" name="director" value="<%= m.getDirector() %>"/>
						<input type="hidden" name="actor" value="<%= m.getActor() %>"/>
         				<td colsapn=2><input type="submit" value="등록하기" /></td>
         			</tr>
         		</table>
         	</form>
        </div>
      </div>
    </div>
  </body>
  
<script>
$("[name=genre]").click(function(){
	var cnt = $("[name=genre]:checked").length;
			console.log(cnt);
	if(cnt > 3){
		if($(this).is(":checked")){
			console.log(cnt);
			alert("장르는 3개 까지 선택해주세요")
			return false;			
		}
	}
})
</script>
</html>