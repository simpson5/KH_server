<%@page import="com.simp.board.model.vo.Review"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/common/floatingbar.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/index.css" />
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
</style>
<%
	Review review = (Review)request.getAttribute("review");
%>
    <!-- include libraries(jQuery, bootstrap) -->
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
    
    <!-- include summernote css/js-->
    <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
    <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>
    <title>리뷰 게시물</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/review_update.css">
<script>
$(document).ready(function() {
  $('#summernote').summernote({
	  placeholder : '내용을 입력해주세요',
	  tabsize : 2,
	  height : 300
  });
});

</script>
 <form action="<%=request.getContextPath()%>/review/update" method="post" name="reviewUpdate">
 <input type="hidden" value="<%= review.getReviewNo() %>" name="no"/>
 <input type="hidden" value="<%= review %>" name="review" />
    <article>
        <div id="container">
            <div class="review_title">
                <h4 class="title_line">			
                   <input type="text" name="title" 
                          id="review_title_update" value="<%= review.getTitle() %>" 
                          style="width: 500px;" placeholder="제목을 입력해주세요">
                </h4>
            </div>
            <div class="content">
                <p class="movie_title_tag"> 관련 영화 : <a href=""><%= review.getMovieTitle() %></a></p>
    
  			<textarea id="summernote" name="content">
  				<%= review.getContent() %>
  			</textarea>
				
            </div>

        </div>
        
	   
	    	<div class="review_write_footer">   
    			<a><input type="submit" value="작성완료"></a>  
        	
	    	</div>
      
</article>
  </form>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
<style>
	.footer{
		margin-top: 80px;
	}
</style>
</body>