<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Expires" content="-1">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">

<link rel="stylesheet"
	href="<%= request.getContextPath() %>/css/reset/common.css" />
<link rel="stylesheet"
	href="<%= request.getContextPath() %>/css/content.css" />

<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<script src="<%= request.getContextPath() %>/js/jquery.easing.1.3.js"></script>
<title>join</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<script
	src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"> </script>

</head>

<body id="KOR" data-device="0" oncontextmenu="return false"
	onselectstart="return false" ondragstart="return false">
	<!-- KOR / ENG / JPN / CHN-CN / CHN-TW -->
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
	<style>
.header_common {
	color: black;
	position: static;
	margin-left: 0;
	width: 1024px
}

.header_common a {
	color: black;
}

.scroll_menu {
	margin-top: -10vh;
	width: 1024px
}

.scroll_menu ul {
	margin-left: 244px;
}

.screen-poster-container {
	margin-top: 5vh;
	width: 1024px
}

input {
	color: black;
}
</style>
<div id="mast-body">
	<div class="container">
		<div class="toparea">
			<h2 class="title">
				<br> &emsp; 화원가입
			</h2>
			<div class="headline">
				&emsp;회원정보를 <br> &emsp;정확하게 입력해주세요. <br> &emsp;
			</div>
		</div>
		<form action="<%= request.getContextPath()%>/member/enroll" method="POST" id="memberEnrollFrm">
			<div class="contents">
				<div class="section __half">
					<h3 class="subject __underline">
						<em>회원정보입력
							<span class="__require-info __point-color"> <small>*는 필수입력 항목입니다.</small></span>
						</em>
					</h3>
					<div class="row" id="div-cstNm">
						<div class="col-md">
							<label><em class="__point-color">*</em>아이디</label>
						</div>
						<div class="col-md">
							<div class="inner">
								<div class="ui-input active">
									<input type="text" name="member_id" placeholder="아이디를 입력해주세요." required size=30>
								</div>
								<button class="id_check_btn">중복</button>
							</div>
						</div>
					</div>
					<div class="row" id="div-pswd">
						<div class="col-md">
							<label><em class="__point-color">*</em>비밀번호</label>
						</div>
						<div class="col-md">
							<div class="inner">
								<div class="ui-input active">
									<input type="password" name="password" placeholder="비밀번호를 입력해주세요" required size=30>
								</div>
							</div>
						</div>
					</div>
					<div class="row" id="div-onlId">
						<div class="col-md">
							<label><em class="__point-color">*</em>이름</label>
							<!-- 이름 -->
						</div>
						<div class="col-md">
							<div class="inner">
								<div class="ui-input active">
									<input type="text" name="member_name"
										placeholder="이름을 입력해주세요." required size=30>
								</div>
							</div>
						</div>
					</div>
					<!-- 생년월일 -->
					<div class="row" id="div-pswd">
						<div class="col-md">
							<label><em class="__point-color">*</em>생년월일</label>
						</div>
						<div class="col-md">
							<div class="inner">
								<div class="ui-input active">
									<input type="text" name="member_ssn"
										placeholder="주민번호 7자리를 입력해주세요." required size=30>
								</div>
							</div>
						</div>
					</div>
					<!-- //생년월일 -->

					<!-- 휴대폰 번호 -->
					<div class="row __depth" id="div-mblNo">
						<div class="col-md">
							<label><em class="__point-color">*</em>휴대폰 번호</label>
							<!-- 휴대폰 번호 -->
						</div>
						<div class="col-md">
							<div class="form-wrap __mobile __telecom type1"
								id="celPhoneView" type="1">
								<div class="inner">
	
									<div class="ui-input active">
										<input type="tel" name="phone" placeholder="전화번호를 입력해주세요."
											required size=30>
									</div>
	
								</div>
							</div>
	
							<span class="__point-color"><small>*휴대폰 번호를 정확하게
									입력하지 않을 경우 회원 혜택이 제한될 수 있습니다.<!-- 휴대폰 번호를 정확하게 입력하지 않을 경우 회원 혜택이 제한될 수 있습니다. -->
							</small></span>
						</div>
					</div>
					<!-- 휴대폰 번호 선택 -->

				<!-- 이메일  -->
				<div class="row __depth" id="div-elcAdd">
					<div class="col-md">
						<label><em class="__point-color">*</em>이메일 주소</label>
						<!-- 이메일 주소 -->
					</div>
					<div class="col-md">
						<div class="form-wrap __normal type1" id="elcAddView" type="1">
							<div class="ui-input active">
								<input type="email" id="user-email" name="email"
									title="이메일주소를 입력해주세요."> <span class="placeholder">이메일주소를
									입력해주세요.</span>
								<!-- 이메일 주소를 입력해주세요. -->
							</div>
						</div>
					</div>
				</div>

				<!-- 주소 -->
				<div class="row __depth" id="div-add">
					<div class="col-md">
						<label for="post-code-home"><em class="__point-color">*</em>주소
							<br> <br> <br> (상세주소) </label>
						<!-- 주소 -->
					</div>
					<div class="col-md">
						<div class="form-wrap__choice __post">

							<!-- 자택 주소입력 -->
							<div class="postwrap type1" id="homeAddView" data-type="1">
								<div class="inner postcode">
									<button type="button"
										class="ui-button __square-small __black" name="postCall"
										onclick="sample6_execDaumPostcode();">우편번호</button>
									<!-- 우편번호 -->

									<div class="ui-input active">
										<input type="text" id="sample6_postcode" name="postcode"
											placeholder="우편번호">
										<!-- 우편번호 -->
									</div>
								</div>
								<div class="inner">
									<div class="ui-input active">
										<input type="text" id="sample6_address" name="adress"
											placeholder="주소" size=30>
										<!-- 상세주소 1 -->
									</div>
								</div>
								<div class="inner">
									<div class="ui-input active">
										<input type="text" id="sample6_detailAddress"
											name="dt_adress" placeholder="상세주소" size=30>
										<!-- 상세주소 2 -->
									</div>
								</div>
								<div class="inner">
									<div class="ui-input active">
										<input type="text" id="sample6_extraAddress" name="refer"
											placeholder="참고항목" size=30>
										<!-- 상세주소 2 -->

									</div>
								</div>
							</div>

						</div>
					</div>
				</div>
				<!-- 주소선택 -->

				<!-- 장르선택 -->
				<div class="row __depth" id="div-telNo">
					<div class="col-md">
						<label for="user-phone-0"><em
							class="__point-color __point-color">*</em>선호 장르 선택<br>&emsp;(최대
							3개)</label>
						<!-- 전화번호 -->
					</div>
					<div class="col-md">

						<input type="checkbox" name="genre" value="드라마"><label
							for="genre">드라마</label> <input type="checkbox" name="genre"
							value="판타지"><label for="genre">판타지</label> <input
							type="checkbox" name="genre" value="서부"><label
							for="genre">서부</label> <input type="checkbox" name="genre"
							value="공포"><label for="genre">공포</label> <input
							type="checkbox" name="genre" value="로맨스"><label
							for="genre">로맨스</label> <br /> <input type="checkbox"
							name="genre" value="스릴러"><label for="genre">스릴러</label>
						<input type="checkbox" name="genre" value="컬트"><label
							for="genre">컬트</label> <input type="checkbox" name="genre"
							value="다큐멘터리"><label for="genre">다큐멘터리</label> <input
							type="checkbox" name="genre" value="코미디"><label
							for="genre">코미디</label> <input type="checkbox" name="genre"
							value="가족"><label for="genre">가족</label> <br /> <input
							type="checkbox" name="genre" value="미스터리"><label
							for="genre">미스터리</label> <input type="checkbox" name="genre"
							value="전쟁"><label for="genre">전쟁</label> <input
							type="checkbox" name="genre" value="애니메이션"><label
							for="genre">애니메이션</label> <input type="checkbox" name="genre"
							value="범죄"><label for="genre">범죄</label> <input
							type="checkbox" name="genre" value="뮤지컬"><label
							for="genre">뮤지컬</label> <br> <input type="checkbox"
							name="genre" value="SF"><label for="genre">SF</label> <input
							type="checkbox" name="genre" value="액션"><label
							for="genre">액션</label>

					</div>
				</div>
				<!-- 장르 선택 -->

					<div class="description"></div>
					<div class="btn_btm_wrap">
						<input type="submit" value="회원가입"
							style="background-color: #ffd509; border: 1px solid #ffd509;">
					</div>
				</div>
			</div>
		</form>

	</div>
	<!-- //contents -->
</div>
	<style>
.footer {
	margin-top: 10vh;
}
</style>
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>
<script> 
 function sample6_execDaumPostcode() {
      new daum.Postcode({
          oncomplete: function(data) {
              // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

              // 각 주소의 노출 규칙에 따라 주소를 조합한다.
              // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
              var addr = ''; // 주소 변수
              var extraAddr = ''; // 참고항목 변수

              //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
              if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                  addr = data.roadAddress;
              } else { // 사용자가 지번 주소를 선택했을 경우(J)
                  addr = data.jibunAddress;
              }

              // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
              if(data.userSelectedType === 'R'){
                  // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                  // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                  if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                      extraAddr += data.bname;
                  }
                  // 건물명이 있고, 공동주택일 경우 추가한다.
                  if(data.buildingName !== '' && data.apartment === 'Y'){
                      extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                  }
                  // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                  if(extraAddr !== ''){
                      extraAddr = ' (' + extraAddr + ')';
                  }
                  // 조합된 참고항목을 해당 필드에 넣는다.
                  document.getElementById("sample6_extraAddress").value = extraAddr;
              
              } else {
                  document.getElementById("sample6_extraAddress").value = '';
              }

              // 우편번호와 주소 정보를 해당 필드에 넣는다.
              document.getElementById('sample6_postcode').value = data.zonecode;
              document.getElementById("sample6_address").value = addr;
              // 커서를 상세주소 필드로 이동한다.
              document.getElementById("sample6_detailAddress").focus();
          }
      }).open();
  }
 
 var check_id = false;
 
 $(".id_check_btn").click(function(){
	 console.log(1);
		$.ajax({
			url : "<%= request.getContextPath() %>/member/checkId",
			data : {
				memberId : $("[name=member_id]").val()
			},
			method : "GET", // 기본값(생략시) GET
			dataType : "text", // 응답 데이터 형식 text | html | xml | json
			success : function(data){
				// 요청 성공시 실행 콜백 함수, 매개 인자로 응답 message 리턴
				console.log("success call!");
				if(data == 0){
					swal({
						title: "사용 불가",
						text: "같은 아이디가 존재합니다.",
						icon: "warning",
						buttons: true,
						dangerMode: true,
					})
					.then((value) => {
						$("[name=member_id]").focus();
						check_id = false;
					});				
				} else {
					swal("사용 가능합니다!");
					check_id = true;
				}
			},
			error : function(xhr, status, error){
				// 요청 오류시 실행 콜백 함수
				console.log("error call!");
				console.log(xhr, status, error);
			},
		}); 
 })
 
$("#memberEnrollFrm").submit(function(){
    var id = $("[name=member_id]").val();
	var password = $("[name=password]").val();
	var member_ssn = $("[name=member_ssn]").val();
	var phone = $("[name=phone]").val();
	
	if(/^.{4,}$/.test(id) == false) {
       swal("아이디가 유효하지 않습니다.");
       return false;
    }
	
	if(/^.{4,}$/.test(password) == false) {
		swal("비밀번호가 유효하지 않습니다.");
	   return false;
	}

	if(/^[0-9]{0,7}$/.test(member_ssn) == false) {
		swal("주민번호 7자리를 적어주세요");
		return false;
	}
	
	if(/^[0-9]{0,11}$/.test(phone) == false) {
		swal("전화번호는 숫자로만 적어주세요");
		return false;
	}
	
	if(!check_id){
		swal("아이디 중복검사 해주세요")
		return false;
	}
	
	return true;
})

//장르 수 체그
var cnt = 0;
$("[name=genre]").click(function(){
	if(cnt < 3){
		if($(this).is(":checked")){
			cnt++;
		} else {
			cnt--;
		}		
	} else {
		swal("선호 장르는 3개 까지 선택이 가능합니다")
		return false;
	}
	console.log(cnt);
})
</script>