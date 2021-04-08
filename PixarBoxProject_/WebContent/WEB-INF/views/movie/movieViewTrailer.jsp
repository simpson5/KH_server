<%@page import="movie.model.vo.LikeList"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="movie.model.vo.MovieComment"%>
<%@page import="java.util.List"%>
<%@page import="movie.model.vo.Movie"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Movie movie = (Movie)request.getAttribute("movie");
	//System.out.println(movie);
	//System.out.println(movie.getMovieCode());
	LikeList like = (LikeList)request.getAttribute("like");
	List<MovieComment> commentList = (List<MovieComment>)request.getAttribute("commentList");
	String rate = (String)request.getAttribute("rate");
	DecimalFormat df = new DecimalFormat("###,###");
%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/movie_movieView.css" />

        <section id="allContent">
            <div id="menuLine">
                <ul id="movieMenu">
                    <li><a href="<%=request.getContextPath() %>/movie/boxOffice" id="m1">박스오피스</a></li>
                    <li><a href="<%=request.getContextPath() %>/movie/movieNow/movieNow.do" id="m2">현재상영작</a></li>
                    <li><a href="<%=request.getContextPath()%>/movie/scheduleMovie/scheduleMovie.do" id="m3">상영예정작</a></li>
                    <li><a href="<%=request.getContextPath() %>/movie/find/movieFinder.do" id="m4">영화찾기</a></li>
                </ul>
            </div>
        
        <div id="movieView">
            
			<img src="<%=request.getContextPath() %>/images/<%=movie.getRenamedFileName() %>" alt="<%=movie.getMovieTitle() %>" class="poster">
            <%if(like==null || like.getLikeNo()==0){ %>
				<img class='notHeart' id='<%=movie.getMovieCode() %>' src='<%=request.getContextPath() %>/images/hearts.png'>
			<% } else{%>
				<img class='heart' id='<%=movie.getMovieCode() %>' src='<%=request.getContextPath() %>/images/hearts.png'>
			<% } %>
			
            <div id="viewContent">
                <div class="title"><img src="<%=request.getContextPath() %>/images/전체연령.png" alt="전체연령" class="movieRating"><span><%=movie.getMovieTitle() %></span></div>
                <hr>
                <div id="ratingLine">
                    <span class="rating">예매율 </span><span class="ratingTicketing"><%=!rate.equals("0.0")?rate+"%":"-" %> &nbsp;<!-- </span><strong class="grade">1위&nbsp;&nbsp;&nbsp;</strong><span id="bar">|&nbsp;&nbsp;</span> --> 
                    <!-- <span class="rating">평점 &nbsp;</span><strong class="grade">7.9</strong><span class="star">&nbsp;&nbsp;★★별표자리</span> -->
                </div>
                <hr>
                <div class="text">
                </div>
                <table id="movieInformation">
                    <tr>
                        <td class="t_title">개봉일</td>
                        <td class="t_content"><%=movie.getReleaseDate() %></td>
                    </tr>
                    <tr>
                        <td class="t_title">기본정보</td>
                        <td class="t_content"><%=movie.getGenres() %>(<%=movie.getRunningTime() %>분)</td>
                    </tr>
                    <tr>
                        <td class="t_title">감독</td>
                        <td class="t_content"><%=movie.getDirector() %></td>
                    </tr>
                    <tr>
                        <td class="t_title">출연진</td>
                        <td class="t_content"><%=movie.getActor() %></td>
                    </tr>
                    <tr>
                        <td class="t_title">누적관객수</td>
                        <td class="t_content"><%=df.format(movie.getTicketSales()) %> <span>명</span></td>
                    </tr>
                </table>
				
				<div id="buttonArea">
				<% if(memberLoggedIn!=null && (memberLoggedIn.getMemberId().equals("admin"))){ %>
						<button id="updateMovie-btn" type="button" onclick="upateMovie();">수정하기</button>
						<button id="deleteMovie-btn" type="button" onclick="deleteMovie();">삭제하기</button>
						
						
						 	<form name="movieDelFrm" action="<%=request.getContextPath()%>/admin/movieDelete" method="post">
							    <input type="hidden" name="movieCode" value="<%=movie.getMovieCode() %>" />
							    <input type="hidden" name="renamedFileName" value="<%=movie.getRenamedFileName()!=null?movie.getRenamedFileName():"" %>" />
					   		</form>
						
				<% } 
				else{ %>
						
						   
			             <button id="ticketing" onclick="ticketing();">예매하기</button>
			             <button id="like" onclick="likeFun('<%=movie.getMovieCode()%>');">찜하기</button>
			              
				<% } %>
              	</div>
				
                <!-- <div id="buttonArea">
                    <button id="ticketing" onclick="ticketing();">예매하기</button>
                    <button id="like">찜하기</button>
                </div> -->
            </div>  <!-- 영화 정보 끝 -->
        </div> <!-- 이미지, 영화정보 라인 -->
        <div id="viewContent2">
	        <div id="viewLine">
	            <a href="<%=request.getContextPath() %>/movie/movieView/movieDescription.do?movieCode=<%=movie.getMovieCode() %>&memberId=<%=memberLoggedIn!=null?memberLoggedIn.getMemberId():""%>">줄거리</a>
	            <!-- <a href="#none" id="a2" class="a" onclick="viewBox2()">스틸컷</a> -->
	            <a href="<%=request.getContextPath() %>/movie/movieView/movieTrailer.do?movieCode=<%=movie.getMovieCode() %>&memberId=<%=memberLoggedIn!=null?memberLoggedIn.getMemberId():""%>" style="color: white;">트레일러</a>
	            <a href="<%=request.getContextPath() %>/movie/movieView/movieReview.do?movieCode=<%=movie.getMovieCode() %>&memberId=<%=memberLoggedIn!=null?memberLoggedIn.getMemberId():""%>">영화리뷰</a>
	        </div>
	        <div id="box3" class="box">
	            <span class="view_title">트레일러</span><span class="cnt"></span>
	            <p><%=movie.getMovieVideo() %></p>
	        </div>
		</div>

	<script>
		function likeFun(m){
			console.log(m);
			//로그인 안했을 때 조건문 달아주기
			<%if(memberLoggedIn != null){
				if(!"admin".equals(memberLoggedIn.getMemberId())){%>
				var params = {
						movieCode: '<%=movie.getMovieCode()%>',
						memberId:  '<%=memberLoggedIn.getMemberId()%>'
				}
	
				$.ajax({
					
					url: "<%=request.getContextPath()%>/movie/movieLike",
					data: params,
					type: "POST",
					dataType: "json",
					success: data => {
						
						console.log(data);
						//0or1로 데이터가 넘어온다.
						//1일때는 insert가 되었을때
			
				 		for(let i  in data){
							
							let p = data[i];
							if(p.clickResult==1){
								//console.log("1");
								
							
								$("#"+m).removeClass("notHeart");
								$("#"+m).addClass("heart");
								
	
								alert("해당영화가 찜 목록에 추가되었습니다.");
	
							}
							else if(p.clickResult==0){
								
	
								$("#"+m).removeClass("heart");
								$("#"+m).addClass("notHeart");
								
								alert("해당영화가 찜 목록에 삭제되었습니다.");
								
							}
							
				 		}
			
						
					},
					error: (x,s,e) =>{
						console.log("ajax처리실패!", x, s, e);
					}
				});	
				
				
				
				
			<%      } 
				}
			  else{ %>
			
				alert("로그인후 이용하세요.");
				return;
			<% } %>
			
	
			
		}
        function ticketing(){
        	location.href = "<%=request.getContextPath() %>/ticket/ticketingStep1/step1.do";	
        }
    	
        function upateMovie(){

        	location.href = "<%=request.getContextPath() %>/admin/updateMovie?movieCode=<%=movie.getMovieCode()%>";
        }
    	function deleteMovie(){
            if(!confirm('영화을 정말 삭제하시겠습니까?')) return;
            $("[name=movieDelFrm]").submit();
    		
    	}

    </script>


<%@ include file="/WEB-INF/views/common/footer.jsp" %>
