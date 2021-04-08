<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/mypageHeader.jsp" %>
<%	
	int totalPage = (int)request.getAttribute("totalPage");
	Member m = (Member)request.getAttribute("member");

%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mypage.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/movie_boxOffice.css" />
<script>
$(".mypoint p").html(<%=m.getPoint()%>);

var num = 1;

$(()=>{
	morePage(1);
	
	$("#btn-more").click(e=>{
		morePage($(e.target).val());
	});
	
	
	$("#likeList").css("color", "#503396").css("font-weight", "bold");
	
});

function delLikeFunc(mc){
	
	var result = confirm("찜 목록에서 삭제하시겠습니까?");
	
	if(result){
		
		location.href = "<%=request.getContextPath() %>/member/deleteMovieLike?movieCode="+mc+"&memberId=<%=memberLoggedIn.getMemberId()%>";	
	}
	else{
		return;
	}
}


function morePage(cPage){
	
		var param = {
				cPage: cPage,
				memberId:  '<%=memberLoggedIn.getMemberId()%>'
		}	
	
		$.ajax({
			url: "<%=request.getContextPath()%>/member/movieLikeMore",
			data: param,
			type: "POST",
			dataType: "json",
			success: data => {


				for(let i  in data){
					
					let html = "";
					let p = data[i];
					html += "<div class='moviePoster'>";
					html += "<div class='ranking_box'>"+num+"</div>";
					html += "<a href='"+"<%=request.getContextPath() %>/movie/movieView/movieDescription.do?movieCode="+p.movieCode+"&memberId=<%=memberLoggedIn!=null?memberLoggedIn.getMemberId():""%>'><img src='<%=request.getContextPath() %>/images/"+p.movieImg+"' class='poster'></a>";				
					html += "<div class='title'>"+p.movieTitle+"</div>";

					html += "<button id='delLike' onclick='delLikeFunc(\""+p.movieCode+"\")';>찜 취소</button>";
				
					$("#movieList").append(html);
					
			       num++;
			       
				}

			
			//더보기버튼 작업
			$("#btn-more").val(Number(cPage)+1)
						  .text("더보기("+cPage+"/<%=totalPage%>)");
						  
			//마지막페이지
			if(cPage == <%=totalPage%>)
				$("#btn-more").attr("disabled","disabled")
							  .css("cursor", "not-allowed");
			
		},
		error: (x,s,e) =>{
			console.log("ajax처리실패!", x, s, e);
		}
	});
}


</script>

<section id="content-boxOffice">

	<div id="movieList"></div>


	<div id="btn-more-container">
		<button id="btn-more" value="1">
			더보기(1/<%=totalPage%>)
		</button>
	</div>
	

</section>





<%@ include file="/WEB-INF/views/common/footer.jsp"%>