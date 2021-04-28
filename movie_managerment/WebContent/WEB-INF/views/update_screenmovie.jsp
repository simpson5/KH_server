<%@page import="com.simp.movie.model.vo.ScreenMovie"%>
<%@page import="com.simp.movie.model.vo.Movie"%>
<% %>
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
    <title>상영 영화 정보 수정</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/enroll_movie.css" />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
    />
    <script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
  </head>
  <body>
    <div class="total_content">
      <h1>상영 영화 정보 수정</h1>
      <div class="main_container">
        <div class="movie_info">
            <div class="movie">
                <table>
                	<tr>
                		<td>
                			<div class="poster">
                				<img src="<%=request.getContextPath() %>/upload/movie/<%= sm.getMainPoster() %>" alt=""/>
              				</div>
                		</td>
                		<td>
                			<div class="info">
				                <p class="title"><a href="#"><%= sm.getMovieTitle() %></a></p>
				                
				                <p>
				                  <span>감독 : </span><%= sm.getDirector() %><span class="director"></span>
				                  <span>출연 : </span><%= sm.getActor() %><span class="actor"></span>
				                </p>
				        	</div>
                		</td>
                	</tr>
                </table>
            </div>
        </div>
        <div class="additional_infor">
	         		<table>
		         	<form name="ScreenMovieUpdateFrm" 
					  action="<%=request.getContextPath() %>/movie/movieUpdate"
					  method="post"
					  id="ScreenMovieUpdateFrm">
						<input type="hidden" name="title" value="<%= sm.getMovieTitle() %>" />
	         			<tr>
	         				<td><label for="trailer">트레일러 : </label></td>
	         				<td><input type="text" name="trailer" id="" value="<%=sm.getTrailer() %>"/></td>
	         			</tr>
	         			<tr>
	         				<td><label for="synopsis">줄거리 : </label></td>
	         				<td><textarea rows="5" cols="40" name="synopsis"><%=sm.getSynopsis() %></textarea></td>
	         			<tr>
	         				<td><label for="status">상영상태 : </label></td>
	         				<td>
	         				
	         				<select name="screen_status" id="status">
								<option value="O">상영</option>
	    						<option value="X">상영중단</option>
							</select>	
	         			  	</td>
	         			</tr>
					</form>
	         			<tr>
	         				<td colsapn=2></td>
	         				<td>
		         				<button class="update_btn" value="수정하기">수정하기</button>
		         				<button class="reset_btn" value="초기화">초기화하기</button>
	         				</td>
	         			</tr>
					</table>
			</div>
		</div>
	</div>    
</body>
<script>
//영화정보 수정
$(".update_btn").click(function(){
	var updateFrm = $("#ScreenMovieUpdateFrm");
	

    var mvTrail= updateFrm.next().find("[name=trailer]").val();
	var mvSynop = updateFrm.next().find("[name=synopsis]").val();
	var mvStatus =$("#status").val();
   
	
	updateFrm.find("[name=trailer]").val(mvTrail);
	updateFrm.find("[name=synopsis]").val(mvSynop);
	updateFrm.find("[name=status]").val(mvStatus);
	
	console.log(mvTrail);
	console.log(mvSynop);
	console.log(updateFrm.find("[name=status]").val()); 
	
	updateFrm.submit();
	
});


// 영화정보 초기화
$(".reset_btn").click(function(){
	var updateFrm = $(this).closest(".additional_infor").prev();
	
	var $trailer = updateFrm.next().find("[name=trailer]");
	var $synopsis = updateFrm.next().find("[name=synopsis]");
	var $status = updateFrm.next().find("[name=status]");
	
	$trailer.val("");
	$synopsis.val("");
	$('input[name="status"]').removeAttr('checked');
	//체크되어있는 항목 모두 해제
	$('input[name="status"]')[0].checked = true;
	//name grade의 0번째만 체크하기	
});

//영화 삭제하기
/*$(".del_btn").click(function(){
	if(confirm("해당 영화를 삭제하시겠습니까?")){
		var deleteFrm = $("#deleteFrm");
		
		var pd_name = $(this).parent().prev().find(".name").text();
		
		 console.log(pd_name); 
		
		deleteFrm.children("[name=delete_pd_name]").val(pd_name);
		
		deleteFrm.submit();
	}
});*/












</script>
</html>