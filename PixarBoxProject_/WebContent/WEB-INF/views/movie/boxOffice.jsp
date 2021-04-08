<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int totalPage = (int)request.getAttribute("totalPage");
	//System.out.println(totalPage);
	
	int order = (int)request.getAttribute("order");
	//System.out.println("order="+order);
%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<script src="<%=request.getContextPath() %>/js/jquery-3.4.1.js"></script>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/movie_boxOffice.css" />

<!-- 해야 할 것 
- 박스오피스, 예매순 글자 보라색
-->



<section id="content-boxOffice">
	<div id="menuLine">
           <ul id="movieMenu">
              <li><a href="<%=request.getContextPath() %>/movie/boxOffice/order1.do" id="orderTicketing">박스오피스</a></li>
              <li><a href="<%=request.getContextPath() %>/movie/movieNow/movieNow.do" id="m2">현재상영작</a></li>
              <li><a href="<%=request.getContextPath()%>/movie/scheduleMovie/scheduleMovie.do" id="m3">상영예정작</a></li>
              <li><a href="<%=request.getContextPath() %>/movie/find/movieFinder.do" id="m4">영화찾기</a></li>
          </ul>
      </div>
      <div id="orderMenu-wrap">
          <ul id="orderMenu">
              <li><a href="<%=request.getContextPath()%>/movie/boxOffice/order1.do" id="orderByTicketing">예매순</a></li>
              <span style="color:gray;">&nbsp;|&nbsp;</span>
              <li id="order_2"><a href="<%=request.getContextPath()%>/movie/boxOffice/order2.do" id="orderByYear">개봉일순</a></li>
          </ul>
      </div>
      
      <%
		if (memberLoggedIn != null) {
			if (memberLoggedIn.getMemberId().equals("admin")) {
	%>

	<div id="add">
		<button id="add-movie" onclick="addMovieFunc();">영화 추가</button>
	</div>
	

	<%
			}
		}
	%>
      
      <!-- ul 반복문  // mardin전체 div로 주기-->
      <div id="movieList">
     
      </div>
      
      <div id="btn-more-container">
	  	<button id="btn-more" value="1">더보기(1/<%=totalPage %>)</button>
	  </div>
     
</section>



<!-- 이전에 사용한 CSS를 브라우저가 캐쉬에 보관해놓고 사용하기에
링크된 CSS 변화점을 기억하지 못하는 것이라고 함 아무의미 없는 ?alter로 써줌-->

<!-- 해야 할 것 
- 박스오피스, 예매순 글자 보라색
-->

<script>

var num = 1;

function ticketing(){
	location.href = "<%=request.getContextPath() %>/ticket/ticketingStep1/step1.do";	
}

$(()=>{
	morePage(1);
	
	$("#btn-more").click(e=>{
		morePage($(e.target).val());
	});
	
	
});

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
	
		 		for(let i in data){
					
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


	//order가 1이면 ticket순 2면 year순
	var orderVal = <%=order%>;

		function morePage(cPage){
			
			<% if(memberLoggedIn != null){%>
				var param = {
						cPage: cPage,		
						memberId:  '<%=memberLoggedIn.getMemberId()%>',
						orderVal: orderVal
				}
			<% } else{ %>
				
				var param = {
						cPage: cPage,
						memberId:  "null",
						orderVal: orderVal
				}	
			
			<% } %>

			
				
				$.ajax({
					url: "<%=request.getContextPath()%>/movie/movieMore",
					data: param,
					type: "POST",
					dataType: "json",
					success: data => {
						//console.log(data);

						<%	int cnt = 0;%>
						
						for(let i  in data) {
							<% cnt = 0;%>
							
							let html = "";
							let p = data[i];
							html += "<div class='moviePoster'>";
							html += "<div class='ranking_box'>"+num+"</div>";
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
		



function addMovieFunc(){
	
	alert("영화를 추가해 봅시다.");

	location.href = "<%=request.getContextPath()%>/admin/addMovie";	
}

$(()=> {
	
});



$(()=> {
	if(<%=order%>==1) {
		$("#orderByTicketing").css("color", "#503396");
		//console.log(<%=order%>);
		//console.log($("#orderTicketing"));
	}
	else if(<%=order%>==2)
		$("#orderByYear").css("color", "#503396");
});
</script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>