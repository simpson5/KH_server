<%@page import="com.simp.board.model.vo.ReviewComment"%>
<%@page import="com.simp.movie.model.vo.ScreenMovie"%>
<%@page import="com.simp.board.model.vo.Review"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/common/floatingbar.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/review_board.css" />

<style>
   .header_common {
      color : black;
      position : static;
   }
   
   .header_common a {
      color : black;
   }
   
   .scroll_menu {
      margin-top : -95px;
   }
   
	.footer{
		margin-top: 80px;
		
	}

</style>
<%
	Review review = (Review)request.getAttribute("review");
	ScreenMovie sm = (ScreenMovie)request.getAttribute("screenMovie");
	List<ReviewComment> list = (List<ReviewComment>) request.getAttribute("list");
%>
<body>
    <article>
        <div id="container">
            <div class="review_title">
                <h4 class="title_line">			
                    <%= review.getTitle() %>        
                </h4>

                <ul class="statistic">
                    <li>조회 <span class="num_read"><%= review.getReadCnt() %></span></li>
                    <li>추천 <span class="num_recommend" id="goodcnt1"><%= review.getRecomCnt() %></span></li>        
                    <li><a href="" class="report">신고</a></li>
                </ul>
                <form id="reportForm" name="reportForm1"></form>  
            </div>
            <div class="content">
                <p class="movie_title_tag"> 관련 영화 : <a href=""><%= sm.getMovieTitle() %></a></p>
                <%= review.getContent() %>
            </div>
            <%
				if(loginMember != null){
			%>
            <div class="content_footer">
                <span class="recommend">
                      <input type="button" value="추천하기" id="recom_up">
                </span>
                <span class="reply_title">댓글</span>
                <div class="reply_box">
                    <span class="user_id" id="reply_user_id"><%= loginMember.getMember_id() %></span>
                    <!-- 댓글 content -->
                    <textarea name="reply_content" class="reply_area" id="" cols="30" rows="10" placeholder="주제와 무관한 댓글, 악플은 삭제될 수 있습니다."></textarea>
                    
                    <div class="reply_box_footer">
                        <input type="button" value="등록" class="reply_sumit">
                        <span class="reply_text_cnt">글자수/500</span>
                    </div>
                </div>
                <% for(ReviewComment r : list){ %>
                <div class="reply_history">
                    <span id="user_id"><%= r.getCommentWriter() %></span>
                    <% if(loginMember != null && (loginMember.getMember_id()).equals(r.getCommentWriter())){ %>
                   		<input type="button" value="삭제" class="reply_delete_btn">
                   		<input type="hidden" name="reply_no" value="<%= r.getCommentNo() %>"/>
                    <% } %>
                    <div class="reply_history_content_box">
                        <span class="reply_history_content"><%= r.getContent() %></span>
                        <p class="reply_history_content_time"><%= r.getRegDate() %></p>
                    </div>
                </div>
                <% } %>
            </div>
            <div class="reply_paging"><%= request.getAttribute("pageBar") %></div>   
        	<% } else{%>
	        	<div class="content_footer">
	                <span class="recommend">
	                      <input type="button" value="추천하기" id="recom_up">
	                </span>
	                <span class="reply_title">댓글</span>
	                <div class="reply_box">
	                    <span class="user_id" id="reply_user_id">비회원</span>
	                    <!-- 댓글 content -->
	                    <textarea name="reply_content" class="reply_area" id="" cols="30" rows="10" placeholder="로그인 후 이용 가능합니다"></textarea>
	                   
	                </div>
	                <% for(ReviewComment r : list){ %>
	                <div class="reply_history">
	                    <span id="user_id"><%= r.getCommentWriter() %></span>
	                    <div class="reply_history_content_box">
	                        <span class="reply_history_content"><%= r.getContent() %></span>
	                        <p class="reply_history_content_time"><%= r.getRegDate() %></p>
	                    </div>
	                </div>
	                <% } %>
	            </div>
	            <div class="reply_paging"><%= request.getAttribute("pageBar") %></div>   
        	<%} %>
        </div>
        
        <div class="review_write_footer">
        <form action="<%= request.getContextPath() %>/review" method="GET" id="reviewFrm">
			<input type="hidden" name="movie_title" value="<%= sm.getMovieTitle() %>"/>  
            <input type="submit" value="목록">
		</form>
         	<% if(loginMember != null && (loginMember.getMember_id()).equals(review.getWriter())) {%>
            <a href="<%= request.getContextPath() %>/review/update?no=<%= review.getReviewNo()%>">
            	<input type="button" value="수정" style="position:relative; top:-24px; left : 50px;">
            </a>
           <% } %>
        </div>
        
    </article>
    
<%@ include file="/WEB-INF/views/common/footer.jsp"%>

<script>
$("#recom_up").click(function(){
	   <% if(session.getAttribute("loginMember") != null) { %>
	   $.ajax({
	      url:"<%= request.getContextPath() %>/review/recomUp",
	      data: {
	         r_no : "<%= review.getReviewNo() %>",
	         member_id: "<%= loginMember.getMember_id() %>"
	      },
	      success: function(data){
	         swal(data)
	      },
	      error: function(xhr, status,err){
	         console.log(xhr, status, err);
	      }
	   })
	   <% } else {%>
	   swal("로그인후 이용 가능합니다.");
	<% } %>
	})

	$(".reply_sumit").click(function(){
	   <% if(session.getAttribute("loginMember") != null) { %>
	   var r_content = $("[name=reply_content]").val();
	   $.ajax({
	      url:"<%= request.getContextPath()%>/review/reply",
	      data : {
	         r_no : "<%= review.getReviewNo() %>",
	         member_id: "<%= loginMember.getMember_id() %>",
	         r_content : $("[name=reply_content]").val()
	      },
	      seccess : function(data){
	         console.log(1);
	         if(data > 0){
	            swal("작성 성공");
	         } else {
	            swal("작성 실패");
	         }
	         
	      },
	      error : function(xhr, status, err){
	         console.log(xhr,status, err);
	      }
	   })
	   <% } else {%>
	      swal("로그인후 이용 가능합니다.");
	   <% } %>
	});

$(".reply_delete_btn").click(function(){
	
	var reply_no = $(this).next().val();
	
	console.log(reply_no);
	
	var comment_no = $("[name=reply_no]").val();
	$.ajax({
		url: "<%= request.getContextPath()%>/review/reply/del",
		data : {
			no : reply_no
		},
		seccess : function(data){
			console.log(1);
			if(data > 0){
				swal("삭제 성공");
			} else {
				swal("삭제 실패");
			}
			
		},
		error : function(xhr, status, err){
			console.log(xhr,status, err);
		}
	})
	
})
</script>
</body>

