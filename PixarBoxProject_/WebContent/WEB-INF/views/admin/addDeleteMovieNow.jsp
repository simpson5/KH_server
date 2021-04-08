<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/addDeleteMovieNow.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/movie_boxOffice.css" />
<style>
form{
max-width:620px;
border:1px solid lightgray;
border-radius:10px;
top:30px;
left:250px;
padding:50px;
}

</style>
<!-- <link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
	integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
	crossorigin="anonymous"></script> -->
<script>

var movieTitleVal = '';
$(function(){

	var $autoComplete = $("#autoComplete");
	$autoComplete.hide();//페이지 최초 로딩시 조회결과 ul태그는 안보임처리한다.
	
	$("#srchMovie").on('keyup', function(e){
		//방향키 제어 ArrowDown, ArrowUp, Enter
		//console.log(e.key);
		
		var $sel = $(".sel");
		var $li = $autoComplete.children("li");

		if(e.key == 'ArrowDown'){
			
			if($sel.length == 0){
				$li.first().addClass("sel");
			}
			else{
				$sel.removeClass("sel")
					.next()
					.addClass("sel");
			}
		}
		else if(e.key == 'ArrowUp'){
			if($sel.length != 0){
				$sel.removeClass("sel")
					.prev()
					.addClass("sel");
			}
			else{
				$li.last().addClass("sel");
			}
			
		}
		else if(e.key == 'Enter'){
			$(this).val($sel.text());
			$autoComplete.hide()
						 .children()
						 .remove();
		}
		else {
			var srchMovieVal = $(this).val().trim();
			
			//공백 입력시 ajax요청 보내지 않음.
			if(srchMovieVal.length == 0) return;
			
			$.ajax({
				url: "<%=request.getContextPath()%>/admin/autoComplete",
				type: "post",
				//data: "srchName="+srchNameVal,
				data: {srchMovie: srchMovieVal},//객체로 전달해도 jquery가 직렬화처리
				success: function(data){
					//console.log(data);
					
					//조회된 결과가 없는 경우
					if(data.trim().length==0){
						$autoComplete.hide();
					}
					//조회결과가 있는 경우
					else{
						var dataArr = data.split(',');
						var html = "";
						$.each(dataArr, (idx, val) => {
							//html += "<li>"+val+"</li>";
							html += "<li>"+val.replace(srchMovieVal, '<span class="srchval">'+srchMovieVal+'</span>')+"</li>";
						});
						$autoComplete.html(html).fadeIn(300);
						
					}
				},
				error: function(jqxhr, textStatus, errorThrown){
					console.log("ajax처리실패!", jqxhr, textStatus, errorThrown);
				}
			});//end of $.ajax
			
			
		}//end of else
		
			
		//이벤트버블링(자식 =>부모)을 이용한 처리
		//요소를 선택한 경우
		$autoComplete.on('click', 'li', e => {
			//화살표함수 내에서 this는 무조건 window
			$("#srchMovie").val($(e.target).text());
			$autoComplete.hide()
						 .children()
						 .remove();
		}).on('mouseover','li', e=>{
			$(e.target).addClass("sel")
					   .siblings()
					   .removeClass("sel");
		}).on('mouseout','li', e=>{
			$(e.target).removeClass("sel");
		});
	
		
	});
});
function applicationClick(){
	movieTitleVal = $("#srchMovie").val();
	if(movieTitleVal == ""){
		alert("영화명을 입력해주세요.");
	}else{
		$("#applicationCheck").val(1);
		$(function() {
			var m = {
					movieTitle: movieTitleVal
			}
			$.ajax({
				url: '<%=request.getContextPath()%>/admin/selectMovieCode',
				type: "get",
				dataType: "json",
				data: m,
				success: data => {
					//console.log(data);//json문자열, javascript object
					
					$(data).each((idx, movieCode)=>{
						$("#srchMovie").val(movieCode.movieCode);
					});
					
				},
				error : (jqxhr, textStatus, errorThrown)=>{
					console.log(jqxhr, textStatus, errorThrown);
				}
			});
			
		});
		
		
	}
}
function applicationChk(){
	var $applicationCheck = $("#applicationCheck");
	if($applicationCheck.val() == 0){
		alert("적용버튼을 클릭해주세요.");
		return false;
	}
	else{
		return true;
	}
}
function deleteMovieNow(){
	var $applicationCheck = $("#applicationCheck");
	movieTitleVal = $("#srchMovie").val();
	if(movieTitleVal == ""){
		alert("영화명을 입력해주세요.");
	} else if($applicationCheck.val() == 0){
		alert("적용버튼을 클릭해주세요.");
	} else{
		location.href = '<%=request.getContextPath()%>/admin/deleteMovieNow?movieCode='+movieTitleVal;
	}
}
</script>
<section id="content-boxOffice">
	<div id="menuLine">
		<ul id="movieMenu">
			<li><a
				href="<%=request.getContextPath()%>/movie/boxOffice?memberId=<%=memberLoggedIn.getMemberId()%>"
				id="m1">박스오피스</a></li>
			<li><a href="" id="m2">현재상영작</a></li>
			<li><a
				href="<%=request.getContextPath()%>/movie/scheduleMovie?memberId=<%=memberLoggedIn.getMemberId()%>"
				id="m3">상영예정작</a></li>
			<li><a href="" id="m4">영화찾기</a></li>
		</ul>
	</div>
<section>
<h2>현재상영작 등록 및 삭제</h2>
	<div id="title">
		<hr />
	</div>
	<form action="<%=request.getContextPath()%>/admin/insertMovieNow" onsubmit="return applicationChk()">
     <!--    <div class="input-group mb-3"> -->
        	<div class="input-group-prepend">
        		<span class="input-group-text">영화명</span>
        	</div>
        	<input type="text" name="movieCode" id="srchMovie" required>
        	<ul id="autoComplete"></ul>
        	<input type="button" name="application" id="application" value="적용" onclick=applicationClick()>
        	<input type="hidden" id="applicationCheck" value="0">
       <!--  </div> -->
      <!--   <div class="input-group mb-3"> -->
        	<div class="input-group-prepend">
        		<span class="input-group-text">회차</span>
        	</div>
        	<input type="number" name="showTurn" id="showTurn" min="1" required>
       <!--  </div> -->
      <!--   <div class="input-group mb-3"> -->
        	<div class="input-group-prepend">
        		<span class="input-group-text">상영관</span>
        	</div>
        	<select name="screenCodeAndRemainSeat" id="screenCode">
	        	<option value="1_83">1</option>
	        	<option value="2_66">2</option>
	        	<option value="3_75">3</option>
	        	<option value="4_79">4</option>
	        	<option value="5_66">5</option>
       		</select>
        <!-- </div> -->
      	<!--  <div class="input-group mb-3"> -->
        	<div class="input-group-prepend">
        		<span class="input-group-text">상영시간</span>
        	</div>
        	<input type="text" name="startTime" id="startTime" placeholder="00:00" required>
        	<ladbe id="stet">~</ladbe>
        	<input type="text" name="endTime" id="endTime" placeholder="00:00" required>
       <!--  </div> -->
        <!-- <div class="input-group mb-3"> -->
        	<div class="input-group-prepend">
        		<span class="input-group-text">날짜</span>
        	</div>
        	 <input type="text" name="showDate" id="showDate" placeholder="0000-00-00" required>
        <!-- </div> -->
        
        <input type="submit" name="enroll" id="enroll" value="등록하기" class="btn btn-secondary btn-lg"/>
        <input type="button" name="delete" id="delete" value="삭제하기" onclick=deleteMovieNow() class="btn btn-secondary btn-lg">

    </form>
</section>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>