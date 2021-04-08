<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<script src="<%=request.getContextPath() %>/js/jquery-3.4.1.js"></script>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/movie_boxOffice.css" />

<script>
//DAO 상에서 이미 좋아요가 눌러진 영화인지 판별한 후에
//추가 해줄지 말지 여부 판단하기.
function likeFunc(m){
	
	
	//로그인 안했을 때 조건문 달아주기
	<%if(memberLoggedIn != null){
		if(!"admin".equals(memberLoggedIn.getMemberId())){%>
		var params = {
				movieCode: m,
				memberId:  '<%=memberLoggedIn.getMemberId()%>'
		}

		$.ajax({
			
			url: "<%=request.getContextPath()%>/movie/movieLike",
			data: params,
			type: "POST",
			dataType: "json",
			success: data => {
				
				//console.log(data);
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

$(()=> {
	
	<% if(memberLoggedIn != null){%>
		var param = {
				memberId:  '<%=memberLoggedIn.getMemberId()%>',
		}
	<% } else{ %>
		
		var param = {
				memberId:  "null",
		}	
	
	<% } %>
	
	$.ajax({
		url: "<%=request.getContextPath() %>/movie/movieNow/showMovieNow.do",
		data: param,
		type: "post",
		dataType: "json",
		success: data=> {
			//console.log(data);
			
			<%	int cnt = 0;%>
			
			for(let i in data) {
				<% cnt = 0;%>
				
				let html = "";
				let p = data[i];
				
				html += "<div class='moviePoster'>";
				html += "<a href='"+"<%=request.getContextPath() %>/movie/movieView/movieDescription.do?movieCode="+p.movieCode+"&memberId=<%=memberLoggedIn!=null?memberLoggedIn.getMemberId():""%>'><img src='<%=request.getContextPath() %>/images/"+p.movieImg+"' class='poster'></a>";
				
				var mc = p.movieCode;
				//console.log(p.like);
				
				if(p.like==1){
	
					html += "<img class='heart' id='"+mc+"' src='<%=request.getContextPath() %>/images/hearts.png'>"
				}else{
	
					html += "<img class='notHeart' id='"+mc+"' src='<%=request.getContextPath() %>/images/hearts.png'>"
				}
			
				html += "<div class='title'>"+p.movieTitle+"</div>"
				html += "<div class='date'>개봉일 <span>"+p.releaseDate+"</span></div>";
				
				<% if(memberLoggedIn!=null){
					if(!memberLoggedIn.getMemberId().equals("admin")){ %>
						
							html += "<button class='like' onclick='likeFunc(\""+p.movieCode+"\")';>찜하기</button>";
							html += "<button class='ticketing' onclick='ticketing();'>예매하기</button>"; 
							
							
					<% }   
				}else if(memberLoggedIn==null){%>
				
							html += "<button class='like' onclick='likeFunc(\""+p.movieCode+"\")';>찜하기</button>";
							html += "<button class='ticketing' onclick='ticketing();'>예매하기</button>"; 
							
				<%}%>
						
	
			
				$("#movieList").append(html);
				
			}
			
	       
		},

			<%-- for(let i in data) {
				let html = "";
				let m = data[i];
				html += "<div class='moviePoster'>";
				html += "<a href='<%=request.getContextPath() %>/movie/movieView/movieDescription.do?movieCode="+m.movieCode+"'><img src='<%=request.getContextPath() %>/images/"+m.movieImg+"' alt='"+m.movieTitle+"' class='poster'></a>";
				html += "<div class='title'><img src='<%=request.getContextPath() %>/images/전체연령.png' alt='"+m.movieTitle+"' class='movieRating'>"+m.movieTitle+"</div>";
				html += "<div class='ticketing_rate'>예매율 <span>57.0%</span></div>";
				html += "<div class='grade'>평점 <span>7.9 ★★★★☆</span></div>";
				html += "<div class='date'>개봉일 <span>"+m.releaseDate+"</span></div>";
				html += "<button class='like'>찜하기</button>";
				html += "<button class='ticketing'>예매하기</button>";
				html += "</div>";
				
				$("#movieList").append(html);
			} --%>
			
		error: (x, s, e)=> {
			console.log("ajax 처리 실패!!", x, s, e);
		}
	});
});

function addDeleteMovieNow(){
	location.href= "<%=request.getContextPath()%>/admin/addDeleteMovieNow";	
}
</script>

<section id="content-movieNow">
	<div id="menuLine">
		<ul id="movieMenu">
			<li><a href="<%=request.getContextPath() %>/movie/boxOffice/order1.do" id="orderTicketing">박스오피스</a></li>
			<li><a href="<%=request.getContextPath() %>/movie/movieNow/movieNow.do" id="m2">현재상영작</a></li>
			<li><a href="<%=request.getContextPath()%>/movie/scheduleMovie/scheduleMovie.do" id="m3">상영예정작</a></li>
			<li><a href="<%=request.getContextPath() %>/movie/find/movieFinder.do" id="m4">영화찾기</a></li>
		</ul>
	</div>
	
	<hr id="id" />
	<%
		if (memberLoggedIn != null) {
			if (memberLoggedIn.getMemberId().equals("admin")) {
	%>
	<div id="btn-area">
		<button id="btn-addDeleteMovieNow" onclick="addDeleteMovieNow()">현재상영작 추가/삭제</button>
	</div>
	<%
			}
		}
	%>
	<div id="resultArea">
		
		<div id="movieList">
		
		</div>
		
	</div>
	
</section>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>