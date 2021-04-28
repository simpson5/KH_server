<%@page import="com.simp.movie.model.vo.ScreenMovie"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/common/floatingbar.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/review_write.css" />
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
	ScreenMovie sm = (ScreenMovie)request.getAttribute("screenMovie");
%>
<!-- include libraries(jQuery, bootstrap) -->
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
    
    <!-- include summernote css/js-->
    <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
    <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>
<script>
$(document).ready(function() {
  $('#summernote').summernote({
	  placeholder : '내용을 입력해주세요',
	  tabsize : 2,
	  height : 300
  });
});

</script>
<body>
    <article>
     <form action="<%=request.getContextPath()%>/review/write" method="post" name="reviewWrite">
     	<input type="hidden" name="movie_title" value="<%= sm.getMovieTitle() %>" />
        <div id="container">
            <div class="review_title">
                <h4 class="title_line">			
                   <input type="text" name="review_title_update" 
                          id="review_title_update" value=""
                          style="width: 500px;" placeholder="제목을 입력해주세요">
                </h4>
			
                <form id="reportForm" name="reportForm1"></form>  
            </div>
            <div class="content">
                <p class="movie_title_tag"> 관련 영화 : <a href=""><%= sm.getMovieTitle() %></a></p>
               <textarea id="summernote" name="content">
  					
  				</textarea>
            </div>

        </div>
       
        <p class="review_write_footer">   
            <input type="submit" value="작성완료" class="write_btn">
        </p>
     </form>
    </article>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
<style>
	.footer{
		margin-top: 80px;
	}
</style>
</body>
<script>
$(".write_btn").click(function(){
	console.log(1);
	$("[name=reviewWrite]").submit();
});
</script>