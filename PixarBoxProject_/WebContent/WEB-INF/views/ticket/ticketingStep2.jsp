<%@page import="theater.model.vo.Seat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ page import="java.util.List"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/ticketStep2.css" />
<link href="http://www.jqueryscript.net/css/jquerysctipttop.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery.seat-charts.css">
<% 
	int showCode = (int)request.getAttribute("showCode");
    //System.out.println(showCode);
    int showTurn = Integer.parseInt((String)request.getAttribute("showTurn"));
    List<Seat> seatList = (List<Seat>)request.getAttribute("seatList");
    int rowsNum = (int)request.getAttribute("rowsNum");
    int colsNum = (int)request.getAttribute("colsNum");
    //System.out.println(seatList);
%>

<script>

$(()=>{
	<%
		//상영관마다 장애인석 표시
  		if(seatList.get(1).getScreenCode() == 1 || seatList.get(1).getScreenCode() == 5){
  	%>
  				$("#A11, #A12").css("background", "rgb(70, 137, 98)");
  				$("#A11, #A12").addClass("disabledSeat");
 	<%
  		}
  		else if(seatList.get(1).getScreenCode() == 2 || seatList.get(1).getScreenCode() == 3 || seatList.get(1).getScreenCode() == 4){
	%>
				$("#A10, #A11").css("background", "rgb(70, 137, 98)");
				$("#A10, #A11").addClass("disabledSeat");
	<%
		}
		for(Seat s : seatList){
			//예매완료 표시		
			if(s.getReservationYN().equals("Y")){
	%>
			 	$("#<%=s.getSeatNo()%>").css("background","rgb(224, 224, 224)")
									 	 .css("cursor", "not-allowed")
									 	 .css("pointer-events", "none");
			 	$("#<%=s.getSeatNo()%>").addClass("Y");
	<%			
			}
		}
	%>
});

var showCode = <%=showCode%>;

$(function() {
	var m = {
			showCode: showCode
	}
	$.ajax({
		url: "<%=request.getContextPath()%>/ticket/ticketingStep2/selectMovieInfo.do",
		type: "get",
		dataType: "json",
		data: m,
		success: data => {
			//console.log(data);//json문자열, javascript object
			
			$(data).each((idx, movieSeat)=>{
				let html="<tr><td colspan='2'><img src='<%=request.getContextPath()%>/images/"+movieSeat.renamedFileName+"' id='movieImg'></td></tr>";
				html+="<tr><td colspan='2' class='bold'>"+movieSeat.movieTitle+"<span style='padding-left: 20px; font-weight: bold; color: #35256B;'><%=showTurn==1?"조조":""%></span></td></tr>";
				html+="<td>일시</td><td class='bold'>"+movieSeat.showDate+"</td></tr>";
				html+="<tr><td>상영시간</td><td class='bold'>"+movieSeat.startTime+"~"+movieSeat.endTime+"</td></tr>";
				html+="<tr><td>상영관</td><td class='bold'>"+movieSeat.screenCode+"관"+"</td></tr>";
				html+="<tr><td>남은좌석</td><td class='bold'><p id='red'>"+movieSeat.remainSeats+"</p>/"+movieSeat.seatCnt+"</td></tr>";
				$("#movieInfo").append(html);
				
				
			});
			
		},
		error : (jqxhr, textStatus, errorThrown)=>{
			console.log(jqxhr, textStatus, errorThrown);
		}
	});
	
});
function getStep3(){
	selectedSeat = $(".getResult").text();
	adult = $(".adultSeat").length;
	child = $(".childSeat").length;
	special = $(".specialSeat").length;
	total = $("#total").html();
	
	if(0 < currentVal < 9 && currentVal == $(".clicked").length && selectedSeat != ""){		
		location.href='<%=request.getContextPath()%>/ticket/ticketingStep3/step3.do?showCode='+showCode+"&selectedSeat="+selectedSeat+"&total="+total+"&adult="+adult+"&child="+child+"&special="+special+"&memberId=<%=memberLoggedIn.getMemberId()%>";			
	}
	else{
		if(currentVal == 0)
			alert("최소 1명 이상의 인원을 선택하세요.");
		else
			alert("예매 인원과 선택 인원이 일치하지 않습니다.");
	}
}
function getPrevious(){
	location.href='<%=request.getContextPath()%>/ticket/ticketingStep1/step1.do';
}
</script>
<section id="content-ticketingStep2">
		  <h2>인원/좌석 선택</h2>
	<div class="jquery-script-clear"></div>
	<div class="wrapper">
	  	<div class="step2Container">
	    <div id="seat-map">
		  <br><br>
			<div id="sort">
				<form action="">
				<p>일반</p>
				<input type="number" class="sort" id="adult" max="8" min="0" placeholder="0">
				<p>청소년</p>
				<input type="number" class="sort" id="child" max="8" min="0" placeholder="0">
				<p>우대</p>
				<input type="number" class="sort" id="special" max="8" min="0" placeholder="0">
				&nbsp;
				<input type="button" value="다시선택" id="btn-reset" style="background: #35256B; color: white; border:0; border-radius: 2px; height: 23px"/>
				</form>
				<span style="padding-left: 35px; font-size: 12px; font-weight: bold;">* 최대 선택 인원은 8명입니다.</span>
			</div>
		  <div class="front-indicator">Front</div>
		  <fieldset>
              <legend>좌석 설정</legend>
              <p><label>행 : <input type="number" name="cols" value="12" /></label></p>
              <p><label>열 : <input type="number" name="rows" value="7" /></label></p>
          </fieldset>
            
           
            <div class="wrap">
              	<ul class="rowsHead"></ul>
                <ol class="seat"></ol>
           </div>
            
		 
	</div>
    <div id="seat-charts">
		 <ul>
		    <li>
		        <div id="select-seat"></div>
		        <span>선택좌석</span>
		    </li>
		    <li>
		        <div id="ticketingEnd-seat"></div>
		    	<span>예매완료</span>
		    </li>
		    <li>
		         <div id="avaliable-seat"></div>
		         <span>선택가능</span>
		    </li>
		    <li>
		         <div id="special-seat"></div>
		         <span>장애인석</span>
		    </li>
		</ul>
   </div>
	<div id="movie-box">
		<table id="movieInfo">
			
			</table>
		</div>
	</div>
	<hr>
	</div>
	    <table id=priceInfo>
	        <tr>
	            <td rowspan="1">
	                <div id="btn-previous" onclick="getPrevious()">
	                    <img src="<%=request.getContextPath()%>/images/previous.png" alt="" id="img-previous">
	                    <p>이전</p>                    
	                </div>
	            </td>
	            <td>인원</td>
	            <td id=personnel>일반 <span id="adult_counter">0</span>명</td>
	            <td id=blank></td>
	            <td>좌석</td>
	            <td id="selected-seats"><div class="result"></div></td>
	            <td id=blank></td>
	            <td id=blank></td>
	            <td>결제금액</td>
	            <td id="totalPrice"><span id="total">0</span></td>
				<td>원</td>
				<td id=blank></td>
				<td>
					<input type="button" value="결제선택" id="paymentButton"
					       onclick=getStep3()>
				</td>
	        	</tr>
	        <tr>
	        	<td></td>
	        	<td></td>
	            <td style="padding-left: 10px; font-weight: bold; font-size: 20px;">청소년 <span id="child_counter">0</span>명</td>
	        </tr>
	        <tr>
	        	<td></td>
	        	<td></td>
	            <td style="padding-left: 11px; font-weight: bold; font-size: 20px;">우대 <span id="special_counter">0</span>명</td>
	        </tr>
	    </table>
	    
</section>

<script>
var adultVal = Number($("#adult").val());
var childVal = Number($("#child").val());
var specialVal = Number($("#special").val());
var currentVal = currentVal = adultVal + childVal + specialVal;
var selectedSeat = '';
var total = 0;
var adult = 0;
var child = 0;
var special = 0;

var $cols = $('input[name=cols]'),
    $rows = $('input[name=rows]'),
    $result = $('.result'),
    $seat = $('.wrap .seat'),
    $colsHead = $('.wrap .colsHead'),
    $rowsHead = $('.wrap .rowsHead'),
    $counter = $("#counter"),
    colsNum = 0, rowsNum = 0;

var result = '';

//가격
if(<%=showTurn%> == 1){
	var adultPrice = 7000;
	var childPrice = 6000;
	var specialPrice = 5000;
}
else{
	var adultPrice = 11000;
	var childPrice = 9000;
	var specialPrice = 5000;
}

var totalPrice = 0;

$("#btn-reset").click(function(){
	//인원 초기화
	$("#adult_counter").html(0);
	$("#child_counter").html(0);
	$("#special_counter").html(0);
	//가격
	$("#total").html(0);
	totalPrice = 0;
	//좌석
	$(".seat li").not($(".Y, .disabledSeat")).css("background", "gray");
	$(".disabledSeat").not($(".Y")).css("background", "rgb(70, 137, 98)");
	$(".seat li").removeClass("clicked");
	$(".seat li").removeClass("adultSeat");
	$(".seat li").removeClass("childSeat");
	$(".seat li").removeClass("specialSeat");
	$result.html("");
	result="";
	
});

$seat.on('click', 'li', function(){
	if(currentVal == 0){
		alert("최소 1명 이상의 인원을 선택하세요.");
		$result.html("");
		result="";
	}
	if(currentVal > 8){
		alert("인원 선택은 최대 8명까지 가능합니다.");
    	$result.html("");
    	result="";
    	$(".sort").val(0);
	}
	
	if($(".clicked").length == currentVal && currentVal != 0){
		alert("이미 좌석을 모두 선택하셨습니다!");
	} 
});
	
//인원 선택 할 때
$(".sort").change(function(){
	adultVal = Number($("#adult").val());
	childVal = Number($("#child").val());
	specialVal = Number($("#special").val());
	currentVal = adultVal + childVal + specialVal;
	//console.log("선택 인원: "+currentVal);
	
	//현재 선택된 좌석보다 인원 선택 수 작게 선택할 시
	if(currentVal < $(".clicked").length && currentVal != 0){
		alert("선택한 좌석의 수가 예매인원보다 많습니다.");
		//전부 초기화
	    //인원 초기화
		$("#adult_counter").html(0);
		$("#child_counter").html(0);
		$("#special_counter").html(0);
		//가격
		$("#total").html(0);
		totalPrice = 0;
		//좌석
		$(".seat li").not($(".Y, .disabledSeat")).css("background", "gray");
		$(".disabledSeat").not($(".Y")).css("background", "rgb(70, 137, 98)");
		$(".seat li").removeClass("clicked");
		$(".seat li").removeClass("adultSeat");
		$(".seat li").removeClass("childSeat");
		$(".seat li").removeClass("specialSeat");
		$result.html("");
		result="";
	}
	
	if(currentVal == 0){
		alert("최소 1명 이상의 인원을 선택하세요.");
		$("#adult_counter").html(0);
		$("#child_counter").html(0);
		$("#special_counter").html(0);
		//가격
		$("#total").html(0);
		totalPrice = 0;
		//좌석
		$(".seat li").not($(".Y, .disabledSeat")).css("background", "gray");
		$(".disabledSeat").not($(".Y")).css("background", "rgb(70, 137, 98)");
		$(".seat li").removeClass("clicked");
		$(".seat li").removeClass("adultSeat");
		$(".seat li").removeClass("childSeat");
		$(".seat li").removeClass("specialSeat");
		$result.html("");
		result="";
	}
	
	if(currentVal > 8){
		alert("인원 선택은 최대 8명까지 가능합니다.");
		$(".sort").val(0);
		$("#adult_counter").html(0);
		$("#child_counter").html(0);
		$("#special_counter").html(0);
		//가격
		$("#total").html(0);
		totalPrice = 0;
		//좌석
		$(".seat li").not($(".Y, .disabledSeat")).css("background", "gray");
		$(".disabledSeat").not($(".Y")).css("background", "rgb(70, 137, 98)");
		$(".seat li").removeClass("clicked");
		$(".seat li").removeClass("adultSeat");
		$(".seat li").removeClass("childSeat");
		$(".seat li").removeClass("specialSeat");
		$result.html("");
		result="";
	}
});
/* 2. 좌석 선택 */
$seat.on('click', 'li', function() {
var $this = $(this),
    index = $this.index();
		//선택 좌석
			if(currentVal<9 && $this.hasClass("clicked") === false && $(".clicked").length < currentVal){
				//장애인석일 경우
				if($this.hasClass("disabledSeat") === true){
					alert("장애인석을 선택하셨습니다.");
				}
			$(this).addClass("clicked");
			$this.css("background", "rgb(53, 37, 107)");
			result += "<p class='getResult'>"+getSeatName(index)+" "+"</p>";
	  		$result.html(result);		
		}
		//인원 입력
		//성인이 0이 아닐 때
		if(currentVal<9 && adultVal != 0 && $(".adultSeat").length < adultVal){
			$this.addClass("adultSeat");
			$("#adult_counter").html($(".adultSeat").length);
			totalPrice += adultPrice;
			$("#total").html(totalPrice);
			//console.log(totalPrice);
		}
	 	if(currentVal<9 && adultVal == $(".adultSeat").length && $(this).attr('class') == 'clicked'){
			if(childVal != 0 && $(".childSeat").length < childVal){
				$this.addClass("childSeat");
				$("#child_counter").html($(".childSeat").length);
				totalPrice += childPrice;
				$("#total").html(totalPrice);
				//console.log(totalPrice);
			}
		}
	 	if(currentVal<9 && childVal == $(".childSeat").length && $(this).attr('class') == 'clicked'){
	 		if(specialVal != 0 && $(".specialSeat").length < specialVal){
				$this.addClass("specialSeat");
				$("#special_counter").html($(".specialSeat").length);
				totalPrice += specialPrice;
				$("#total").html(totalPrice);
				//console.log(totalPrice);
	 		}
	 	}
}); //seat.on - click 끝


var updateView = function() {
    var makeTag = '', i = 1, leng = 0;

    colsNum = <%=colsNum%>;
    rowsNum = <%=rowsNum%>;
	
    var a = 64;
    var b = 64;
    for(i = 1, leng = colsNum * rowsNum; i <= leng; i++) {
        if( i % colsNum === 1 ) {
	        a++;
	        b++;
            makeTag += "<li style='clear:both;' name="+i+" id="+String.fromCharCode(a)+1+">"+1+"</li>";
            var j = 1;
        } else{
            j++;
            makeTag += "<li name="+j+" id="+String.fromCharCode(b)+j+">"+j+"</li>";
        }
        

    }
    $seat.html(makeTag);
    
    if(rowsNum == 7 && colsNum == 12){
	    $("li[name='7']").css("margin-left", "50px");
	    $("li[name='11']").first().css("background", "rgb(70, 137, 98)");
	    $("li[name='12']").first().css("background", "rgb(70, 137, 98)");
	    $("li[name='12']").last().css("display", "none");    	
    }
    if(rowsNum == 6 && colsNum == 11){
    	$("li[name='3']").css("margin-left", "50px");
    	$("li[name='10']").css("margin-left", "50px");
    }
    if(rowsNum == 7 && colsNum == 11){
    	$("li[name='5']").css("margin-left", "50px");
    	$("li[name='10']").css("margin-left", "50px");
    	$("li[name='10']").last().css("display", "none");
    	$("li[name='11']").last().css("display", "none");
    }
    if(rowsNum == 8 && colsNum == 11){
    	$("li[name='3']").eq(3).css("display", "none");
    	$("li[name='4']").eq(3).css("display", "none");
    	$("li[name='5']").eq(3).css("display", "none");
    	$("li[name='6']").eq(3).css("display", "none");
    	$("li[name='7']").eq(3).css("display", "none");
    	$("li[name='8']").eq(3).css("display", "none");
    	$("li[name='9']").eq(3).css("display", "none");
    	$("li[name='10']").eq(3).css("display", "none");
    	$("li[name='11']").eq(3).css("display", "none");
    	$("li[name='12']").eq(3).css("display", "none");
    	$("li[name='3']").css("margin-left", "50px");
    	$("li[name='8']").css("margin-left", "50px");
    	$("li[name='4']").eq(3).css("margin-right", "194px");
    }
    if(rowsNum == 6 && colsNum == 12){
    	$("li[name='7']").css("margin-left", "50px");
    	
		$("li[name='12']").eq(3).css("display", "none");
		$("li[name='12']").eq(4).css("display", "none");
		$("li[name='12']").eq(5).css("display", "none");
		$("li[name='11']").eq(4).css("display", "none");
		$("li[name='11']").eq(5).css("display", "none");
		$("li[name='10']").eq(5).css("display", "none");
    }
    
    for(makeTag = '', i = 1, leng = colsNum; i <= leng; i++) {
        makeTag += '<li>' + i + '</li>';
    }
    $colsHead.html(makeTag);

    for(makeTag = '', i = 65, leng = 65 + rowsNum; i < leng; i++) {
        makeTag += '<li>' + String.fromCharCode(i) + '</li>';
    }
    $rowsHead.html(makeTag);
    
};

var getSeatName = function( index ) {
    var colsIndex = (index % colsNum) + 1,
        rowsIndex = Math.ceil((index + 1) / colsNum) - 1,
        rowsName = String.fromCharCode((65 + rowsIndex));

    return rowsName+colsIndex;
};

$('fieldset').on('input', 'input', function() {
    updateView();
    return false;
});


updateView();

<!-- @수정@ -->
<%-- <%
	for(SeatInfo si : seatInfo){
		if(si.getReservationYN().equals("Y")){
%>
 	$("#<%=si.getSeatNo()%>").css("background","rgb(224, 224, 224)")
						 	 .css("cursor", "not-allowed")
						 	 .css("pointer-events", "none");
<%			
		}
	}
%> --%>

</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>