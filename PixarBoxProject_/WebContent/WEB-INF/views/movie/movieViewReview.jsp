<%@page import="movie.model.vo.LikeList"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="movie.model.vo.MovieWithCommentCnt"%>
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
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/movie_movieView.css" />

        <section id="allContent">
            <div id="menuLine">
                <ul id="movieMenu">
                    <li><a href="<%=request.getContextPath()%>/movie/boxOffice" id="m1">박스오피스</a></li>
                    <li><a href="<%=request.getContextPath() %>/movie/movieNow/movieNow.do" id="m2">현재상영작</a></li>
                    <li><a href="<%=request.getContextPath()%>/movie/scheduleMovie/scheduleMovie.do" id="m3">상영예정작</a></li>
                    <li><a href="<%=request.getContextPath()%>/movie/find/movieFinder.do" id="m4">영화찾기</a></li>
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
                <div class="title"><img src="<%=request.getContextPath()%>/images/전체연령.png" alt="전체연령" class="movieRating"><span><%=movie.getMovieTitle()%></span></div>
                <hr>
                <div id="ratingLine">
                    <span class="rating">예매율 </span><span class="ratingTicketing"><%=!rate.equals("0.0")?rate+"%":"-" %> &nbsp;</span><!-- <strong class="grade">1위&nbsp;&nbsp;&nbsp;</strong><span id="bar">|&nbsp;&nbsp;</span>  -->
                    <!-- <span class="rating">평점 &nbsp;</span><strong class="grade">7.9</strong><span class="star">&nbsp;&nbsp;★★별표자리</span> -->
                </div>
                <hr>
                <div class="text">
                </div>
                <table id="movieInformation">
                    <tr>
                        <td class="t_title">개봉일</td>
                        <td class="t_content"><%=movie.getReleaseDate()%></td>
                    </tr>
                    <tr>
                        <td class="t_title">기본정보</td>
                        <td class="t_content"><%=movie.getGenres()%>(<%=movie.getRunningTime()%>분)</td>
                    </tr>
                    <tr>
                        <td class="t_title">감독</td>
                        <td class="t_content"><%=movie.getDirector()%></td>
                    </tr>
                    <tr>
                        <td class="t_title">출연진</td>
                        <td class="t_content"><%=movie.getActor()%></td>
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
	            <a href="<%=request.getContextPath()%>/movie/movieView/movieDescription.do?movieCode=<%=movie.getMovieCode()%>&memberId=<%=memberLoggedIn!=null?memberLoggedIn.getMemberId():""%>">줄거리</a>
	            <!-- <a href="#none" id="a2" class="a" onclick="viewBox2()">스틸컷</a> -->
	            <a href="<%=request.getContextPath()%>/movie/movieView/movieTrailer.do?movieCode=<%=movie.getMovieCode()%>&memberId=<%=memberLoggedIn!=null?memberLoggedIn.getMemberId():""%>">트레일러</a>
	            <a href="<%=request.getContextPath()%>/movie/movieView/movieReview.do?movieCode=<%=movie.getMovieCode()%>&memberId=<%=memberLoggedIn!=null?memberLoggedIn.getMemberId():""%>" style="color: white;">영화리뷰</a>
	        </div>
	        <div id="box4" class="box">
	            <span class="view_title">영화리뷰</span>
	            <span class="cnt">
	            <%
	            	if(((MovieWithCommentCnt)movie).getCommentCnt()>0) { 
	            %>
	            	<%=((MovieWithCommentCnt)movie).getCommentCnt()%>건
	            <% 
	            	}
	            	else {
	            %>
	            	0건
	            <% 
	            	}
	            %>
	            </span>
	
	            <!-- 댓글 컨테이너 -->
	            <div class="comment-container">
	                <!-- 댓글작성 -->
	                <div class="comment-editor">
	                    <form action="<%=request.getContextPath() %>/movie/movieCommentInsert" method="post" name="MovieCommentFrm">
	                          <input type="hidden" name="movieRef" value="<%=movie.getMovieCode() %>">
	                          <input type="hidden" name="commentWriter" value="<%=memberLoggedIn!=null?memberLoggedIn.getMemberId():"" %>">
	                          <input type="hidden" name="commentLevel" value="1" />
	                          <input type="hidden" name="commentRef" value="0" />
	                          
	                          <% if(memberLoggedIn!=null) { %>
	                          <textarea name="commentContent" placeholder="리뷰를 작성해주세요." cols="110" rows="5"></textarea>	                          
	                          <% 
	                          	} 
	                          	else {%>
	                          <textarea name="commentContent" placeholder="로그인 후 이용하실 수 있습니다." cols="110" rows="5"></textarea>
	                          <% } %>
	                          <input type="submit" value="등록" id="btn-insert">
	                    </form>
	                </div> <!-- comment-editor end -->
	            </div> <!-- comment-container end -->
				
				<!-- 댓글목록테이블 -->
				<table id="tbl-comment">
				<%
					if(commentList!=null && !commentList.isEmpty()) {
						for(MovieComment mc : commentList) {
							//1. 댓글인 경우
							if(mc.getCommentLevel() == 1) {
								//System.out.println(mc.getCommentNo());
				%>
					<tr>
						<td>
							<sub class="comment-writer"><%=mc.getCommentWriter() %></sub>
							<sub class="comment-date"><%=mc.getCommentDate() %></sub>
							<br />
							<%=mc.getCommentContent() %>
						</td>
						<td>
							<button class="btn-reply" value="<%=mc.getCommentNo() %>">답글</button>
							<!-- 삭제버튼 글쓴이or관리자에게만 노출, 삭제후 삭제성공메세지 후 현재페이지로 돌아옴 -->
							<% if(memberLoggedIn!=null && ("admin".equals(memberLoggedIn.getMemberId()) || mc.getCommentWriter().equals(memberLoggedIn.getMemberId()))) { %>
								<button class="btn-delete" onclick="deleteComment(<%=mc.getCommentNo() %>)">삭제</button>
							<% } %>
						</td>
					</tr>
				
				<% 				
							}
							//2. 대댓글인 경우
							else {
				%>				
					<tr class="level2">
						<td>
							<sub class="comment-writer"><%=mc.getCommentWriter() %></sub>
							<sub class="comment-date"><%=mc.getCommentDate() %></sub>
							<br />
							<%=mc.getCommentContent() %>
						</td>
						<td>
							<% if(memberLoggedIn!=null && ("admin".equals(memberLoggedIn.getMemberId()) || mc.getCommentWriter().equals(memberLoggedIn.getMemberId()))) { %>
							<button class="btn-delete" onclick="deleteComment(<%=mc.getCommentNo() %>)">삭제</button>
							<% } %>
						</td>
					</tr>
							
				<% 
							}
						}
					}
				%>
				
				
				</table>
	        </div> <!-- box4 end -->
        </div> <!-- viewContent2 end -->
        
    </section>
    
        <script>
        $(()=> {
        	
        	$(".btn-reply").on("click",function(e) {
        		<% if(memberLoggedIn == null) { %>
        			loginAlert();
        		<%
        			}
        			//로그인 한 경우
        			else {
        		%>
        			console.log($(this));
        			console.log($(this).val());
        			var tr = $("<tr></tr>");
        			var html = '<td style = "display:none; text-align:left;" colspan=2>';
        			html += '<form action="<%=request.getContextPath() %>/movie/movieCommentInsert" method="POST">';
        			html += '<input type="hidden" name="movieRef" value="<%=movie.getMovieCode() %>" />';
        			html += '<input type="hidden" name="commentWriter" value="<%=memberLoggedIn.getMemberId() %>" />';
        			html += '<input type="hidden" name="commentLevel" value="2" />';
        			html += '<input type="hidden" name="commentRef" value="'+$(this).val()+'" />';
        			html += '<textarea name="commentContent" id="" cols="60" rows="1"></textarea>';
        			html += '<input type="submit" class="btn-insert2" value="등록" />';
        			html += '</form></td>';
        			tr.html(html);
        			
        			tr.insertAfter($(this).parent().parent())
        			  .children("td")
        			  .slideDown(800)
        			  .children("form")
        			  .submit(function(e) {
        				 var $textarea = $(this).children("textarea");
        				 
        				 if($textarea.val().trim().length == 0)
        					 e.preventDefault();
        			  });
        		<%	  
        			}
        		%>
        	});
        	
        	//textarea
        	$("[name=commentContent]").click(function() {
        		if(<%=memberLoggedIn == null %>) {
        			loginAlert();
        		}
        	});
        	
        	//submit: jquery방식 - preventDefault함수 사용
        	$("[name=MovieCommentFrm]").submit(function(e) { //e에 이벤트객체가 담김
        		if(<%=memberLoggedIn == null %>) {
        			loginAlert();
        			e.preventDefault(); //로그인하지 않았을 경우 제출되지 않음
        			return;
        		}
        		
        		var $content = $("[name=commentContent]");
        		if($content.val().trim().length == 0) {
        			e.preventDefault(); //글을 쓰지 않았을 경우 제출되지 않음
        			return;
        		}
        	});
        });
        
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
        
        function loginAlert() {
        	alert("로그인 후 이용하실 수 있습니다.");
        }
        function deleteComment(commentNo) {
        	if(!confirm("댓글을 삭제하시겠습니까??")) return;
        	location.href = "<%=request.getContextPath() %>/movie/movieCommentDelete?movieCode=<%=movie.getMovieCode() %>&commentNo="+commentNo;
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
