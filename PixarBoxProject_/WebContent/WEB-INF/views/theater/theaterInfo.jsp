<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
    
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/theater.css" />
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>

<!-- ! 할 일  ! -->
<!--
[jsp] 
- 요일 표시?
- 심야 표시
- 8일/11일 시간표 없음 표시
-  ★이미지 클릭 잘 안 먹음

[css]
- 호버 시 색상 영역
- css => 가운데로


 -->



<script>

var date;



$(()=>{
	/* showList_9(); */
	date = 10;
	showList_9(date);
	
	$("#btn-right").click(function(){
		console.log("date="+date);
		if(date < 12){
			showList_9(++date);			
		}
	});
	$("#btn-left").click(function(){
		console.log("date="+date);
		if(date > 10){
			showList_9(--date);
		}
	});
	
	
});
 


var srch_date;
var cnt = 1;

//9일
function showList_9(d){

		if(d == 10){
			srch_date = '2020-01-10';	
		}
		else if(d == 11){
			srch_date = '2020-01-11';
		}
		else if (d == 12){
			srch_date = '2020-01-12';
		}
		
			
	$.ajax({
		
		url: "<%=request.getContextPath()%>/theater/srchTheaterInfo",
		/* data: {srchDate: '2020-01-09'}, */
		data: {srchDate: srch_date},		
		type: "get",
		dataType: "json",
		success: data => {
			console.log(data);
			
			let $tr;
			let $date = $("#date");	
			let $day = $("#day");
			
			for(let i in data){
				let s = data[i];
				console.log(s);
				
				if(i == 1) {
					
 					var dateArr = (s.showDate).split('-');
 					console.log(dateArr[1]);
					
					$date.html(dateArr[1]+"."+dateArr[2]);
					/* 초기화해줘야 함!!! */
					$("#schedule").html("");
					/* $day.append(dateArr[3]); */

				}
				
 				//결과1: 영화명마다
 				if(s.showTurn == 1){
					$tr = $("<tr></tr>");
					let html = "<td rowspan='1' class='movie-title'>"+s.movieTitle+"</td>";
					html += "<td rowspan='1' class='movie-theater'>"+s.screenCode+"관<br>디지털(자막)</td>";
		            html += "<td><div class='reservation'>";
		            html += "<p>"+s.startTime+" ~ "+s.endTime+"</p><br>";
	  		        html += "<a href='javascript:void(0);' onclick='ticketingGo("+s.showCode+","+s.showTurn+");'>빠른예매</a></div>";
		            html += "<p class='jojo' style='font-weight: bold; color: #35256b; font-size: 17px;'>조조</p><br>"; 
		            html += "<p class='time'>"+s.startTime+"</p><br>"; 
		            html += "<p class='seat'>"+s.remainSeats+"/"+s.seatCnt+"</p></td>";		            
					
		            $tr.append(html);
				}
				
				if(s.showTurn != 1) {
		            let html = "<td><div class='reservation'>";
		            html += "<p>"+s.startTime+" ~ "+s.endTime+"</p><br>";
 	  		        html += "<a href='javascript:void(0);' onclick='ticketingGo("+s.showCode+","+s.showTurn+");'>빠른예매</a></div>";
		            html += "<p class='jojo'></p><br>"; 
		            html += "<p class='time'>"+s.startTime+"</p><br>"; 
		            html += "<p class='seat'>"+s.remainSeats+"/"+s.seatCnt+"</p></td>";	
		               
					$tr.append(html);
				}  
				
				$("#schedule").append($tr);
				
				
			}

			
			
		},
		error : (jqxhr, textStatus, errorThrown)=>{
			console.log(jqxhr, textStatus, errorThrown);
		}
	});
	
	
}

function ticketingGo(showCode, showTurn){
	
	
 	if(<%=memberLoggedIn==null%>){		
 		alert("로그인 후 이용하실 수 있습니다.");
		location.href="<%=request.getContextPath()%>/member/memberLogin";
		
	}
	else{
		<%-- location.href="<%=request.getContextPath()%>/theater/ticketingGo?showCode="+showCode; --%>
		location.href="<%=request.getContextPath()%>/ticket/ticketingStep2/step2.do?showCode="+showCode;
	}
	
}


</script>
<%-- <!-- 상영리스트 끝 -->

		<img src="<%=request.getContextPath()%>/images/theaterImage.png" alt=""/>
	<div id="menu">
        <a href="#1">상영시간표</a>
        <a href="#2">위치/교통</a>
        <a href="#3">관람료</a>
    </div>
    <div id="1"></div>
    <br /><br><br>
    <div id="bar"></div>
    <h2 id="title">상영시간표</h2>
    
    <div>
    	<img src="<%=request.getContextPath()%>/images/left.png" id="btn-left" style="cursor: pointer;"/>
    	<button id="btn-left"><img src="<%=request.getContextPath()%>/images/left.png" style="width: 70px; height: 70px;"></button>
        <p id="date"></p>
        <p id="day"></p>
    	<button id="btn-right"><img src="<%=request.getContextPath()%>/images/right.png" style="width: 70px; height: 70px;"></button>
        <img src="<%=request.getContextPath()%>/images/right.png" alt="" id="btn-right" style="cursor: pointer;"/>
        <img src="<%=request.getContextPath()%>/images/ratingInfo.png" alt="" id="img-ratingInfo"/>
        <input type="button" value="관람등급 안내" onclick="ratings();" id="btn-ratings" style="cursor: pointer;"/>
    </div>

	<!-- 상영리스트 -->
	<table id=schedule></table>
   
    <div id="2"></div>
   	<br /><br><br>
    <div id="bar"></div>
    <h2 id="title">위치/교통</h2>
    <div id="location">
	    <div id="map">
	    	<!-- * 카카오맵 - 지도퍼가기 -->
			<!-- 1. 지도 노드 -->
			<div id="daumRoughmapContainer1576223876110" class="root_daum_roughmap root_daum_roughmap_landing"></div>
	
			<!--2. 설치 스크립트
			* 지도 퍼가기 서비스를 2개 이상 넣을 경우, 설치 스크립트는 하나만 삽입합니다.-->
			<script charset="UTF-8" class="daum_roughmap_loader_script" src="https://ssl.daumcdn.net/dmaps/map_js_init/roughmapLoader.js"></script>
	
			<!-- 3. 실행 스크립트 -->
			<script charset="UTF-8">
			new daum.roughmap.Lander({
				"timestamp" : "1576223876110",
				"key" : "w8wc",
				"mapWidth" : "500",
				"mapHeight" : "360"
			}).render();
			</script>
	    </div>
	    <div id="mapInfo">
	    	<div id="subwayInfo">
	    	<table id="maptable">
	    		<tr>
	    			<td>
	    				<img src="<%=request.getContextPath()%>/images/subway.png" id="subway" alt="" />
	    			</td>
	    			<td>
	    				<img src="<%=request.getContextPath()%>/images/line2.png" alt=""  id="line2"/>
			   			<img src="<%=request.getContextPath()%>/images/lineNew.png" alt="" id="lineNew"/>
	    			</td>
	    			<td><p>강남역 1번출구</p></td>
	    		</tr>
	    		<tr>
	    			<td	id="subwayTitle"><p>지하철</p></td>
	    			<td>
	    				<img src="<%=request.getContextPath()%>/images/line2.png" alt=""  id="line2"/>
	    			</td>
	    			<td><p>역삼역 3번출구</p></td>
	    		</tr>
	    	</table>
	    	</div>
	    	<div>
	    	<table id="maptable">
	    		<tr>
	    			<td rowspan="2">
	    				<img src="<%=request.getContextPath()%>/images/bus.png" id="bus" alt="" />
	    			</td>
	    			<td id="first">
	    				<p>역삼역, 포스코타워역삼(23-288)</p>
	    			</td>
	    		</tr>
	    		<tr><td><p>강남역(23-813)</p></td></tr>
	    		<tr>
	    			<td id="busTitle"><p>버스</p></td>
	    			<td>강남역 12번출구(23-814)</td>
	    		</tr>
	    	</table>
	    	</div>
	    </div>
    </div>
    <div id="3"></div>
	<div id="bar"></div>
	<h2 id="title">관람료</h2>
    <table id=price>
        <tr><th>상영시간</th>
            <th>성인</th>
            <th>청소년</th>
            <th>경로<br>(만 65세이상)</th>
            <th>미취학 아동<br>(만 4세~ 만 6세)</th>
        </tr>
        <tr><td>조조(첫 회차)</td>
            <td>7,000</td>
            <td>6,000</td>
            <td>5,000</td>
            <td>5,000</td>
        </tr>
        <tr><td>일반(2회차 ~ 24시 전)</td>
            <td>11,000</td>
            <td>9,000</td>
            <td>5,000</td>
            <td>5,000</td>
        </tr>
<!--         <tr><td>심야(24시~)</td>
            <td>9,000</td>
            <td>7,000</td>
            <td>5,000</td>
            <td>5,000</td>
        </tr> -->
        
    </table> --%>

    <section>
	<div id="div-introduction">
		<h1>PIXAR BOX</h1>
		<div>
			<p>강남역과 역삼역 사이에 있는 젊은이들의</p><p class="highlight">&nbsp;Hot Place!</p><br />
			<p>최고의 사운드의 최상의 공간을 자랑하는</p><p class="highlight">&nbsp;PIXAR BOX!</p><br />
			<p>Pixar Animation Studios의 장편영화만을 상영하는</p><br />
			<p>Pixar Animation 전용 상영관에 오신 걸 환영합니다.</p><br />
		    <p class="highlight">5관/369석</p>		
		</div>
		<img src="<%=request.getContextPath()%>/images/theaterImage.png" alt="" id="img-theaterInfo"/>	
	</div>
	<div id="menu">
        <a href="#1">상영시간표</a>
        <a href="#2">위치/교통</a>
        <a href="#3">관람료</a>
    </div>
    <div id="1"></div>
    <br /><br><br>
    
    <div id="div-scheduleOfShow">
	    <h2 class="title">상영시간표</h2>
	    
	    <div id="div-date">
	    	<img src="<%=request.getContextPath()%>/images/left.png" id="btn-left" style="cursor: pointer;"/>
	        <p id="date"></p>
	        <p id="day"></p>
	        <img src="<%=request.getContextPath()%>/images/right.png" alt="" id="btn-right" style="cursor: pointer;"/>
	        <div id="ratingInfo">
		        <img src="<%=request.getContextPath()%>/images/ratingInfo.png" alt="" id="img-ratingInfo"/>
		        <input type="button" value="관람등급 안내" onclick="ratings();" id="btn-ratings" style="cursor: pointer;"/>	        
	        </div>
	    </div>
		<!-- 상영리스트 -->
		<table id=schedule></table>
    </div>


   
    <div id="2"></div>
   	<br /><br><br>
   	
	<div id="div-location">
		<h2 class="title">위치/교통</h2>
		    <!-- <div id="map"> -->
		    	<!-- * 카카오맵 - 지도퍼가기 -->
				<!-- 1. 지도 노드 -->
				<div id="daumRoughmapContainer1576223876110" class="root_daum_roughmap root_daum_roughmap_landing"></div>
		
				<!--2. 설치 스크립트
				* 지도 퍼가기 서비스를 2개 이상 넣을 경우, 설치 스크립트는 하나만 삽입합니다.-->
				<script charset="UTF-8" class="daum_roughmap_loader_script" src="https://ssl.daumcdn.net/dmaps/map_js_init/roughmapLoader.js"></script>
		
				<!-- 3. 실행 스크립트 -->
				<script charset="UTF-8">
				new daum.roughmap.Lander({
					"timestamp" : "1576223876110",
					"key" : "w8wc",
					"mapWidth" : "500",
					"mapHeight" : "360"
				}).render();
				</script>
		   <!--  </div> -->
		    <div id="mapInfo">
		    	<div id="subwayInfo">
		    	<table id="maptable">
		    		<tr>
		    			<td>
		    				<img src="<%=request.getContextPath()%>/images/subway.png" id="subway" alt="" />
		    			</td>
		    			<td>
		    				<img src="<%=request.getContextPath()%>/images/line2.png" alt=""  id="line2"/>
				   			<img src="<%=request.getContextPath()%>/images/lineNew.png" alt="" id="lineNew"/>
		    			</td>
		    			<td><p>강남역 1번출구</p></td>
		    		</tr>
		    		<tr>
		    			<td	id="subwayTitle"><p>지하철</p></td>
		    			<td>
		    				<img src="<%=request.getContextPath()%>/images/line2.png" alt=""  id="line2"/>
		    			</td>
		    			<td><p>역삼역 3번출구</p></td>
		    		</tr>
		    	</table>
		    	</div>
		    	<div>
		    	<table id="maptable">
		    		<tr>
		    			<td rowspan="2">
		    				<img src="<%=request.getContextPath()%>/images/bus.png" id="bus" alt="" />
		    			</td>
		    			<td id="first">
		    				<p>역삼역, 포스코타워역삼(23-288)</p>
		    			</td>
		    		</tr>
		    		<tr><td><p>강남역(23-813)</p></td></tr>
		    		<tr>
		    			<td id="busTitle"><p>버스</p></td>
		    			<td>강남역 12번출구(23-814)</td>
		    		</tr>
		    	</table>
		    	</div>
		    </div>
	</div>
   	
    <div id="3"></div>
    <div id="div-price">
		<h2 class="title">관람료</h2>
	    <table id=price>
	        <tr><th>상영시간</th>
	            <th>성인</th>
	            <th>청소년</th>
	            <th>경로<br>(만 65세이상)</th>
	            <th>미취학 아동<br>(만 4세~ 만 6세)</th>
	        </tr>
	        <tr><td>조조(첫 회차)</td>
	            <td>7,000</td>
	            <td>6,000</td>
	            <td>5,000</td>
	            <td>5,000</td>
	        </tr>
	        <tr><td>일반(2회차 ~ 24시 전)</td>
	            <td>11,000</td>
	            <td>9,000</td>
	            <td>5,000</td>
	            <td>5,000</td>
	        </tr>
	<!--         <tr><td>심야(24시~)</td>
	            <td>9,000</td>
	            <td>7,000</td>
	            <td>5,000</td>
	            <td>5,000</td>
	        </tr> -->
	        
	    </table>
    
    </div>
</section>
<script>

function ratings(){
	var url="<%=request.getContextPath()%>/theater/movieRatings";
	var title="관람등급 안내";
	var spec="left=500px, top=200px, width=650px, height=350px";
	open(url, title, spec);
}
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>