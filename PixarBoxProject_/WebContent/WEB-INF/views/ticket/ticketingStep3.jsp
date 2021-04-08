<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/ticketStep3.css" />

<%
	int showCode = (int)request.getAttribute("showCode");
	int total = (int)request.getAttribute("total");
	String selectedSeat = (String)request.getAttribute("selectedSeat");
	String[] seatResult = selectedSeat.split(" ");
	String result = "";
	if(seatResult!=null) result = String.join(",", seatResult);
	int adult = (int)request.getAttribute("adult");
	int child = (int)request.getAttribute("child");
	int special = (int)request.getAttribute("special");
	Member m = (Member)request.getAttribute("member");
%>
<script>
	var showCode = <%=showCode%>;
	console.log(showCode);
	var movieTitle = '';
	var screenCode = 0;
	var showDate = '';
	var startTime = '';
	var endTime = '';
	var movieImg = '';
	var memberId = '<%=memberLoggedIn.getMemberId()%>';
	var result = '<%=result%>';
	var paymentWay2;
	var finalPrice = 0;
	var point = 0;
	
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
				console.log(data);//json문자열, javascript object
				
				$(data).each((idx, movieSeat)=>{
					let html="<tr><td colspan='2'><img src='<%=request.getContextPath()%>/images/"+movieSeat.movieImg+"' id='movieImg'></td></tr>";
					html+="<tr><td colspan='2'>"+movieSeat.movieTitle+"</td></tr>";
					html+="<td>일시</td><td>"+movieSeat.showDate+"</td></tr>";
					html+="<tr><td>상영시간</td><td>"+movieSeat.startTime+"~"+movieSeat.endTime+"</td></tr>";
					html+="<tr><td>상영관</td><td>"+movieSeat.screenCode+"관"+"</td></tr>";
					html+="<tr><td>남은좌석</td><td>"+movieSeat.remainSeats+"/"+movieSeat.seatCnt+"</td></tr>";
					$("#movieInfo").append(html);
					movieTitle = movieSeat.movieTitle;
					screenCode = movieSeat.screenCode;
					showDate = movieSeat.showDate;
					startTime = movieSeat.startTime;
					endTime = movieSeat.endTime;
					movieImg = movieSeat.movieImg;
				});
				
				
				
			},
			error : (jqxhr, textStatus, errorThrown)=>{
				console.log(jqxhr, textStatus, errorThrown);
			}
		});
		
	});
	
	function payment(){	
		var paymentWay = $("input[type=radio][name=paymentWay]:checked").val() 
		paymentWay2 = '';
		var card = $("select[name='cardChoice']").val();
		console.log($("select[name='cardChoice']").val());
		if(paymentWay=='card' && $("select[name='cardChoice']").val() == ""){
			alert("신용카드 종류를 선택해 주세요");
			return;
		}
		$("#form").css("display", "block");
		console.log(movieTitle);
		$("#movieTitle").html(movieTitle);
		$("#movieScreen").html(screenCode);
		$("#showDate").html(showDate);
		$("#startTime").html(startTime);
		$("#endTime").html(endTime);
		
		$("#finalPrice").html($("#totalPrice").html());
		finalPrice = $("#totalPrice").html();
		console.log(finalPrice);
		
		if(paymentWay == 'card'){paymentWay2 = '신용카드'}
		else if(paymentWay == 'phone'){paymentWay2 = '휴대폰결제'}
		else{paymentWay2 = '계좌이체'};
		$("#wayOfPayment").html(paymentWay2);
		
		point = $("#salePrice").html();
	
	}
	
	function submit(){
		location.href='<%=request.getContextPath()%>/ticket/ticketingStep3/ticketingEnd.do?memberId='+memberId+"&paymentMethod="+paymentWay2+"&paymentPrice="+finalPrice+"&seatNo="+result+"&screenCode="+screenCode+"&movieTitle="+movieTitle+"&startTime="+startTime+"&endTime="+endTime+"&point="+point+"&showCode="+showCode+"&showDate="+showDate;
	}
	
</script>
<section>
<div id=all>
        <div id="payment-box">
            <div id="bar"></div>
            <h2>결제선택</h2><br><br>
            <div id=point><p id=title>포인트</p><img src="<%=request.getContextPath()%>/images/down.png" alt="" id="pointDown">
                                <img src="<%=request.getContextPath()%>/images/up.png" alt="" id="pointUp"></div>
            <div id="pointDiv">
                <p>보유 포인트</p>
                <p id="avaliablePoint"><%=m.getPoint() %></p>
<%--                 <p id="avaliablePoint"><%=memberLoggedIn.getPoint()%></p> --%>
                <p>point</p>
                <input type="button" value="모두사용" onclick=allUseOfPoint()><br>
                <p>적용 포인트</p>
                <input type="number" name="" id="pointOfapplication">
                <input type="button" value="적용" onclick=applyPoint()>
            </div>
            
            <br><br><br>
        
            <div id=paymentWay><p id="title">결제수단</p><img src="<%=request.getContextPath()%>/images/down.png" alt="" id="paymentDown">
                                      <img src="<%=request.getContextPath()%>/images/up.png" alt="" id="paymentUp"></div><br>
            <div id=paymentDiv>
                <input type="radio" name="paymentWay" onclick="showDiv();" id="card" checked value="card">신용카드
                <input type="radio" name="paymentWay" onclick="showDiv();" id="phone" value="phone">휴대폰 결제
                <input type="radio" name="paymentWay" onclick="showDiv();" id="account" value="account">계좌이체
                <div id="cardDiv" class="paymentWayDiv">
                    <select name="cardChoice" id="cardChoice">
                        <option value="">카드를 선택하세요.</option>
                        <option value="BC카드">BC카드</option>
                        <option value="현대카드">현대카드</option>
                        <option value="KEB하나카드(구, 외환)">KEB하나카드(구,외환)</option>
                        <option value="삼성카드">삼성카드</option>
                        <option value="신한카드">신한카드</option>
                        <option value="KB국민카드">KB국민카드</option>
                        <option value="카카오뱅크카드">카카오뱅크카드</option>
                        <option value="NH카드">NH카드</option>
                        <option value="스탠다드차타드은행카드">스탠다드차타드은행카드</option>
                        <option value="시티카드(구, 한미)">시티카드(구,한미)</option>
                        <option value="롯데/아멕스카드">롯데/아멕스카드</option>
                        <option value="K뱅크">K뱅크</option>
                        <option value="우리카드">우리카드</option>
                        <option value="신세계카드">신세계카드</option>
                        <option value="하나카드(구, 하나SK)">하나카드(구,하나SK)</option>
                        <option value="광주은행카드">광주은행카드</option>
                        <option value="산은캐피탈">산은캐피탈</option>
                        <option value="수협카드">수협카드</option>
                        <option value="KDB산업은행카드">KDB산업은행카드</option>
                        <option value="전북은행카드">전북은행카드</option>
                        <option value="제주은행카드">제주은행카드</option>
                        <option value="우체국카드">우체국카드</option>
                        <option value="현대카드">현대카드</option>
                        <option value="MG체크카드">MG체크카드</option>
                        <option value="KB증권카드(구, 현대증권)">KB증권카드(구,현대증권)</option>
                        <option value="기업은행카드">기업은행카드</option>
                        <option value="SSG카드">SSG카드</option>
                    </select>
                </div>
                <div id="phoneDiv" class="paymentWayDiv">
                    <p>상품명</p>
                    <p>영화티켓예매</p><br>
                    <p>아래 결제하기 버튼 클릭 후 다음 단계로 이동</p>
                </div>
                <div id="accountDiv" class="paymentWayDiv">
                    <p>아래 결제하기 버튼 클릭 후 다음 단계로 이동</p>
                </div>
            </div>
        </div>
        <div id="movie-box">
            <table id="movieInfo">
                <!-- <tr>
                    <td colspan="2"><img src="image/겨울왕국.jpg" alt="" id="movieImg"></td></tr>
                <tr>
                    <td colspan="2">겨울왕국2</td>
                </tr>
                <tr>
                    <td>일시</td>
                    <td>2019.12.07 토</td>
                </tr>
                <tr>
                    <td>상영시간</td>
                    <td>10:15~12:00</td>
                </tr>
                <tr>
                    <td>상영관</td>
                    <td>1관</td>
                </tr>
                <tr>
                    <td>남은좌석</td>
                    <td>226/232</td>
                </tr> -->
            </table>
        </div>
    </div>
    <hr>
    <table id=priceInfo>
        <tr>
            <td rowspan="2">
                <div id="btn-previous" onclick="getPrevious()">
	               <img src="<%=request.getContextPath()%>/images/previous.png" alt="" id="img-previous">
	               <p>이전</p>                    
	            </div>
            </td>
            <td rowspan="2">인원</td>
            <td rowspan="2" class=personnel>일반 <%=adult %>명</td>
            <td rowspan="2" id=blank></td>
            <td rowspan="2">좌석</td>
            <td rowspan="2">
            <%for(int i=0; i<seatResult.length; i++){ %>
            	<div id="selectedSeat"><%=seatResult[i]%></div>
            <%} %>
            </td>
            <td rowspan="2" id=blank></td>
            <td>할인금액</td>
            <td id=salePrice>0</td>
            <td>원</td>
            <td rowspan="2" id=blank></td>
            <td rowspan="2">
                <input type="button" value="결제하기" id="paymentButton" onclick="payment()">
            </td>
            <tr>
                <td>총 결제금액</td>
                <td id=totalPrice><%=total%></td>
                <td>원</td>
            </tr>
            <tr>
            	<td></td>
            	<td></td>
            	<td class=personnel>청소년 <%=child %>명</td>
            </tr>
            <tr>
            	<td></td>
            	<td></td>
            	<td class=personnel>우대 <%=special %>명</td>
            </tr>
        </tr>
    </table>
    	<div id="form">
    		<div id="titleDiv">예매내역 확인</div>
    		<div>
    			<table>
    				<tr>
	    				<th colspan=2>예매정보</th>
	    				<th colspan=2>결제정보</th>    				
    				</tr>
    				<tr>
    					<td>
    						<p>영화제목</p><br />
    						<p>상영관</p><br />
    						<p>일시</p><br />
    						<p>인원</p><br />
    						<p>좌석</p>
    					</td>
						<td>
							<p id="movieTitle"></p><br />
							<p id="movieScreen"></p>관<br />
							<p id="showDate"></p>
							<p id="startTime"></p>~<p id="endTime"></p><br />
							<p>일반 <%=adult%>명 청소년 <%=child%>명 우대<%=special%>명</p><br />
							<p><%=selectedSeat%></p>
						</td>
						<td>
							<p>결제금액</p><br />
							<p>결제수단</p><br />
							<p></p><br />
							<p></p><br />
							<p></p>
						</td>
						<td>
							<p id="finalPrice"></p><br />
							<p id="wayOfPayment"></p><br />
							<p></p><br />
							<p></p><br />
							<p></p>
						</td>
    				</tr>
    			</table>
    			<input type="button" value="결제하기" onclick="submit()"/>
    			<input type="button" value="취소" onclick= "cancel()"/>
    		</div>
    	</div>
    </form>
</section>
<script type="text/javascript">
function showDiv(){
   
    var checked = document.getElementsByName("paymentWay"); 

		if(checked[0].checked==true){
			document.getElementById("cardDiv").style.display = "block";
			document.getElementById("phoneDiv").style.display = "none"; 
			document.getElementById("accountDiv").style.display = "none"; 
		}
		if(checked[1].checked==true){
			document.getElementById("cardDiv").style.display = "none";
			document.getElementById("phoneDiv").style.display = "block"; 
			document.getElementById("accountDiv").style.display = "none"; 
		}
		if(checked[2].checked==true){
			document.getElementById("cardDiv").style.display = "none";
			document.getElementById("phoneDiv").style.display = "none"; 
			document.getElementById("accountDiv").style.display = "block";
		}
}
(()=>{
    $("#pointDown").click(function(){
        $("#pointDiv").css('display', 'block');
        $("#pointDown").css('display', 'none');
        $('#pointUp').css('display', 'block');
    })

    $("#pointUp").click(function(){
        $("#pointDiv").css('display', 'none');
        $("#pointDown").css('display','block');
        $("#pointUp").css('display', 'none');
    })
    
    $("#paymentDown").click(function(){
        $("#paymentDiv").css('display', 'block');
        $("#paymentDown").css('display', 'none');
        $('#paymentUp').css('display', 'block');
    })

    $("#paymentUp").click(function(){
        $("#paymentDiv").css('display', 'none');
        $("#paymentDown").css('display','block');
        $("#paymentUp").css('display', 'none');
    })
})();
function getPrevious(){
	location.href='<%=request.getContextPath()%>/ticket/ticketing/step2?showCode='+<%=showCode%>;
}
function allUseOfPoint(){
	$("#pointOfapplication").val(<%=m.getPoint()%>);
<%-- 	$("#pointOfapplication").val(<%=memberLoggedIn.getPoint()%>); --%>
	$("#avaliablePoint").html(0);
}

function applyPoint(){
	$total = <%=total%>;
	$point = <%=m.getPoint()%>;
<%-- 	$point = <%=memberLoggedIn.getPoint()%>; --%>
	if($point < $("#pointOfapplication").val()){
		alert("포인트가 부족합니다.")
	}else{
		$("#avaliablePoint").html($point-$("#pointOfapplication").val());		
		$("#salePrice").html($("#pointOfapplication").val());
		$("#totalPrice").html($total-$("#salePrice").html());
	}
}

function cancel(){
	$("#form").css("display", "none");
}
</script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>