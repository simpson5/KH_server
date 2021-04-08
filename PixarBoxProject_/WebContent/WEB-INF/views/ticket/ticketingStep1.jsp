<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/ticket.css" />
<script>
var movieCode = "";
var showCode = "";
var showDate = "";

$(function() {
    
    //영화목록
    $.ajax({
    	url: "<%=request.getContextPath() %>/ticket/ticketingStep1/showMovieNow.do",
    	type: "get",
    	dataType: "json",
    	success: data => {
    		//console.log(data);
    		
    		let $list = $("<ul></ul>");
    		
    		$(data).each((idx, movie)=> {
    			let html = "<li id="+movie.movieCode+">"+movie.movieTitle+"</li>";
    			
    			$list.append(html);
    		});
    		
    		$(".movie-list").html($list);
    		
    		$(".movie-list>ul>li").click(function() {
		        $(".movie-list>ul>li").css("background", "white")
		                                .css("color", "black");
		        $(this).css("background", "#261049")
		               .css("color", "white");
		    });
    		
    		$(".movie-list>ul>li").on('click', function(){ 
				movieCode = $(this).attr('id');
				//console.log(movieCode);
				getdate(movieCode);
			});
			
    	},
    	error: (jqxhr, textStatus, errorThrown)=> {
    		console.log("ajax실패!!", jqxhr, textStatus, errorThrown);
    	}
    	
    });
    
    

});

//날짜목록
function getdate(movieCode) {
	$("li.day.day").remove();
	$("li.showtime").remove();
	$("#screenArea").children().remove();
	
	var m = {
		movieCode: movieCode
	};
	
	$.ajax({
		url: "<%=request.getContextPath() %>/ticket/ticketingStep1/showDateList.do",
		type: "get",
		data: m,
		dataType: "json",
		success: data=> {
			//console.log(data);
			
			/* let $list = $("<div class='year'></div>);
			let html = "<div>"; */
			
			
			let $list1 = $("<div class='month'></div>");
			let html = "<div>";
			html += "<span class='year'>"+data[0].showDate.substring(0, 4)+"</span>";
			html += "<br>";
			html += "</div>"
			html += "<div>"
			html += "<span class='month'>"+data[0].showDate.substring(5, 7)+"</span>";
			html += "</div>";
			
			//console.log(dateList.showDate.substring(0, 4));
			$list1.append(html);
			$("#show-date1").html($list1);
			
			let $list2 = $("<ul id='show-date2'></ul>");
			$(data).each((idx, dateList)=> {
				let html = "<li class='day day' id='"+dateList.showDate+"'>";
				html += "<span class='dayweek'>"+dateList.showDate.substring(8)+"일</span>"
				html += "</div>";
				html += "</li>";
				
				$list2.append(html);
			});
			
			$("#showDateDiv").html($list2);
			
			$("#showDateDiv>ul>li").click(function() {
		        $("#showDateDiv>ul>li").css("background", "white")
		                               .css("color", "black")
		        					   .css("font-weight", "bold");
		        $("#2020-01-11").css("background", "white")
								.css("color", "#31597e");
		        $("#2020-01-12").css("background", "white")
								.css("color", "#ad2727");
		        $(this).css("background", "#261049")
		               .css("color", "white")
		        	   .css("font-weight", "bold");
		        console.log($(this));
		    });
			
			$("#2020-01-10").css("font-weight", "bold");
			
			$("#2020-01-11").css("background", "white")
							.css("color", "#31597e")
							.css("font-weight", "bold");
			
			$("#2020-01-12").css("background", "white")
							.css("color", "#ad2727")
							.css("font-weight", "bold");
			
			$("#showDateDiv>ul>li").on('click', function(){ 
				showDate = $(this).attr('id');
				
				gettime(movieCode, showDate);
			});
		},
		error: (xhr, textStatus, errorThrown)=> {
			console.log("ajax실패!!", xhr, textStatus, errorThrown);
		}
		
	});
}

//시간목록
function gettime(movieCode, showDate) {
	$("li.showtime").remove();
	
	var m = {
		movieCode: movieCode,
		showDate: showDate
	};
	
	$.ajax({
		url: "<%=request.getContextPath() %>/ticket/ticketingStep1/showTimeList.do",
		type: "get",
		data: m,
		dataType: "json",
		success: data=> {
			//console.log(data);
			
			let $list = $("<ul></ul>");
			
			let html1 = "<span id='screenCode'>"+data[0].screenCode+"관</span>";
			$(data).each((idx, timeList)=>{
				
				let html2 = "<li class='showtime' id="+timeList.showCode+">"+timeList.startTime+"</li>";
				$list.append(html2);
			});
			
			$("#screenArea").html(html1);
			$("#showTime").html($list);
			
			$("#screenCode").css("font-weight", "bold")
							.css("margin-left", "10px");
							
			$("#showTime>ul>li").on("click", function() {
				$("#showTime>ul>li").css("background", "white")
                .css("color", "black");
				$(this).css("background", "#261049")
				.css("color", "white");
			});
			
			$("#showTime>ul>li").on('click', function(){ 
				/* movieCode = $(".movie-list>ul>li").attr('id'); */
				showCode = $(this).attr('id');
				
				//console.log(showCode);
			});
		},
		error: (xhr, status, error)=> {
			console.log("ajax실패!!!", xhr, status, error);
		}
	});
}

function getStep2() {
	if(<%=memberLoggedIn == null %>) {
		loginAlert();
	}
	else if(movieCode == "") {
		alert("영화를 선택해주세요.");
	}
	else if(showDate == "") {
		alert("날짜를 선택해주세요.");
	}
	else if(showCode == "") {
		alert("시간을 선택해주세요.");
	}
	else {
		location.href='<%=request.getContextPath()%>/ticket/ticketingStep2/step2.do?showCode='+showCode;
	}
}
function loginAlert() {
	alert("로그인 후 이용하실 수 있습니다.");
	location.href='<%=request.getContextPath()%>/member/memberLogin';
}
</script>
<section>
<div id="container">
    <h2>티켓예매</h2>
    <div id="ticketing">
        <div class="section section-movie">
            <div id="movie-list" class="col-head">영화</div>
            <div class="col-body" style="height: 560px;">
                <div class="sortmenu">
                    <a href="#">예매율순</a>
                    <a href="#">가나다순</a>
                </div>
                <div class="movie-list">
                </div>
            </div>
        </div>
        <div class="section section-date">
            <div id="date-list" class="col-head">날짜</div>
            <div class="col-body" style="height: 560px;">
                <ul id="show-date1">
                </ul>
                <div id="showDateDiv"></div>
                <!-- <ul id="show-date2"></ul> -->
            </div>
        </div>
        <div class="section section-time">
            <div id="time-list" class="col-head">시간</div>
            <br />
                <div id="screenArea"></div>
                <hr />
                <div id="showTime"></div>
            <div class="col-body" style="height: 560px;"></div>
        </div>
        <input type="button" value="좌석선택" onclick="getStep2();" />
    </div>
</div>

</section>
	
<%@ include file="/WEB-INF/views/common/footer.jsp" %>