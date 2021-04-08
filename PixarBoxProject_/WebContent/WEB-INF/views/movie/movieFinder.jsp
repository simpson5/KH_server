<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//int totalPage = (int)request.getAttribute("totalPage");
	//System.out.println(totalPage);
%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<script src="<%=request.getContextPath() %>/js/jquery-3.4.1.js"></script>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/movie_boxOffice.css" />

<section id="content-movieFinder">
	<div id="menuLine">
           <ul id="movieMenu">
              <li><a href="<%=request.getContextPath() %>/movie/boxOffice" id="m1">박스오피스</a></li>
              <li><a href="<%=request.getContextPath() %>/movie/movieNow/movieNow.do" id="m2">현재상영작</a></li>
              <li><a href="<%=request.getContextPath()%>/movie/scheduleMovie/scheduleMovie.do" id="m3">상영예정작</a></li>
              <li><a href="<%=request.getContextPath() %>/movie/find/movieFinder.do" id="m4">영화찾기</a></li>
          </ul>
      </div>

      <div id="finderContent">
          <div id="bar"></div><div id="bar_title">영화찾기</div>
          
          <div id="finderBox">
              <form action="" id="finder_form" onsubmit="return false">
                  <table>
                      <tr>
                          <th class="underline">영화검색</th>
                          <td class="underline">
                              <select name="searchCategory" id="search">
                                  <option value="searchAll">전체</option>
                                  <option value="searchTitle">영화제목</option>
                                  <option value="searchDirector">감독</option>
                                  <option value="searchActor">출연진</option>
                              </select>
                              <input type="text" id="search_text" name="searchKeyword" size="25" placeholder="검색할 키워드를 입력하세요.">
                          </td>
                      </tr>
                      <tr>
                          <th class="underline">장르</th>
                          <td class="underline">
                              <ul>
                                  <li>
                                      <input type="checkbox" name="genre" id="drama" value="드라마">
                                      <label for="drama">드라마</label>       
                                      <input type="checkbox" name="genre" id="fantasy" value="판타지">
                                      <label for="fantasy">판타지</label>       
                                      <input type="checkbox" name="genre" id="child" value="아동">
                                      <label for="child">아동</label>       
                                  </li>
                                  <li>
                                      <input type="checkbox" name="genre" id="comedy" value="코미디">
                                      <label for="comedy">코미디</label>       
                                      <input type="checkbox" name="genre" id="adventure" value="어드벤처">
                                      <label for="adventure">어드벤처</label>       
                                      <input type="checkbox" name="genre" id="family" value="가족">
                                      <label for="family">가족</label>       
                                  </li>
                                  <li>
                                      <input type="checkbox" name="genre" id="sf" value="sf">
                                      <label for="sf">SF</label>       
                                      <input type="checkbox" name="genre" id="musical" value="뮤지컬">
                                      <label for="musical">뮤지컬</label>       
                                      <input type="checkbox" name="genre" id="animation" value="애니메이션">
                                      <label for="animation">애니메이션</label>       
                                  </li>
                                  <li>
                                      <input type="checkbox" name="genre" id="adventure2" value="모험">
                                      <label for="adventure2">모험</label>       
                                      <input type="checkbox" name="genre" id="action" value="액션">
                                      <label for="action">액션</label>       
                                  </li>
                              </ul>
                          </td>
                     </tr>
                  </table>
                  <div class="submit">
                  	<input type="button" value="검색" id="btn-submit" />
                    <!-- <button type="submit" id="btn-submit">검색</button> -->
                    <button type="reset" id="btn-reset">초기화</button>
                  </div>
              </form>
		</div> <!--finderBox끝-->
        <hr id="hr">
        <div id="resultArea">
        	<br />
            <div id="resultDiv">
            </div>
            <div id="movieList">
        </div> <!-- resultArea -->
</section>

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

$("#btn-submit").click(()=> {
	$(".resultSpan").remove();
	$(".moviePoster").remove();
	
	var cnt=0;
	var genreArr=new Array();
	
	$("input[name=genre]").each(function() {
		if(this.checked) {
			genreArr[cnt]=this.value;//배열로 저장
            cnt++;
		}
	});
	
	var genre = genreArr.join(",");
	
	//console.log(genreArr);
	//console.log(genre);
	
	//console.log($("#search_text").val());
	
	
	//console.log(searchInfo);
	
	<% if(memberLoggedIn != null){%>
		var searchInfo = {
			searchCategory: $("#search").val(),
			searchKeyword: $("#search_text").val(),
			genreArr: genreArr,
			yearStart: $("#yearStart").val(),
			yearEnd: $("#yearEnd").val(),
			memberId:  '<%=memberLoggedIn.getMemberId()%>'
		}
	<% } else{ %>
		
		var searchInfo = {
			searchCategory: $("#search").val(),
			searchKeyword: $("#search_text").val(),
			genreArr: genreArr,
			yearStart: $("#yearStart").val(),
			yearEnd: $("#yearEnd").val(),
			memberId:  null
		}
		/* var param = {
				jsonSearchInfo: JSON.stringify(searchInfo),
				memberId:  "null"
		} */
	
	<% } %>
		//var jsonSearchInfo = JSON.stringify(searchInfo);
	//console.log(jsonOrder);
	
	$.ajax({
		url: "<%=request.getContextPath() %>/movie/find/movieFinderView.do",
		data: searchInfo,
		type: "get",
		dataType: "json",
		success: function(data) {
			//console.log(data);
			//console.log(data.length);
			
			let cnt = "<span class='resultSpan'>선택조건에 해당하는 영화가</span>";
			cnt += "<span class='resultSpan' id='resultSpan'> 총"+data.length+"건 </span>";
			cnt += "<span class='resultSpan'>검색되었습니다.</span>";
			
			$("#resultDiv").append(cnt);
	            
			for(let i in data) {
				let html = "";
				let m = data[i];
				html += "<div class='moviePoster'>";
				html += "<a href='<%=request.getContextPath() %>/movie/movieView/movieDescription.do?movieCode="+m.movieCode+"&memberId=<%=memberLoggedIn!=null?memberLoggedIn.getMemberId():""%>'><img src='<%=request.getContextPath() %>/images/"+m.movieImg+"' alt='"+m.movieTitle+"' class='poster'></a>";
				
				var mc = m.movieCode;
				//console.log(p.like);
				
				if(m.like==1){
	
					html += "<img class='heart' id='"+mc+"' src='<%=request.getContextPath() %>/images/hearts.png'>"
				}else{
	
					html += "<img class='notHeart' id='"+mc+"' src='<%=request.getContextPath() %>/images/hearts.png'>"
				}
			
				html += "<div class='title'>"+m.movieTitle+"</div>"
				html += "<div class='date'>개봉일 <span>"+m.releaseDate+"</span></div>";
				
				<% if(memberLoggedIn!=null){
					if(!memberLoggedIn.getMemberId().equals("admin")){ %>
						
							html += "<button class='like' onclick='likeFunc(\""+m.movieCode+"\")';>찜하기</button>";
							html += "<button class='ticketing' onclick='ticketing();'>예매하기</button>"; 
							
							
					<% }   
				}else if(memberLoggedIn==null){%>
				
							html += "<button class='like' onclick='likeFunc(\""+m.movieCode+"\")';>찜하기</button>";
							html += "<button class='ticketing' onclick='ticketing();'>예매하기</button>"; 
							
				<%}%>
				
                $("#movieList").append(html);
			}
			
		},
		error: function(xhr, stt, err) {
			console.log("ajax 처리 실패!!", xhr, stt, err);
		}
	});
	
});
</script>















<%@ include file="/WEB-INF/views/common/footer.jsp" %>