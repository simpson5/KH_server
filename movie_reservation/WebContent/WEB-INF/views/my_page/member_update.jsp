<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Expires" content="-1">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/reset/common.css"/>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/content.css" />
    
    <script src="js/jquery-3.6.0.js"></script>
    <script src="js/jquery.easing.1.3.js"></script>
    <title>resetMypage</title>
    
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    
  <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
     <script src= "https://unpkg.com/sweetalert/dist/sweetalert.min.js"> </script> 
    
</head>    

<body id="KOR" data-device="0" oncontextmenu="return false" onselectstart="return false" ondragstart="return false"> <!-- KOR / ENG / JPN / CHN-CN / CHN-TW -->
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%
	String memberId = loginMember.getMember_id();
	String password = loginMember.getPassword();
	String memberName = loginMember.getMember_name();
	String memberSsn = loginMember.getMember_ssn()!= null ? loginMember.getMember_ssn(): "";
	String email = loginMember.getEmail() != null ? loginMember.getEmail() : "";
	String phone = loginMember.getPhone();
	String address = loginMember.getAddress() != null ? loginMember.getAddress() : "";
	String grade =loginMember.getMember_grade();
	String genre = loginMember.getPreference_genre(); 
	
	List<String> genreList = null;
	if(genre != null){
		String[] arr = genre.split(",");
		genreList = Arrays.asList(arr); // String[] -> List<String>
	}

%>
<style>
   .header_common {
      color : black;
      position : static;
      margin-left: 0;
      width: 1024px
   }
   
   .header_common a {
      color : black;
   }
   
   .scroll_menu {
      margin-top: -10vh;
      width: 1024px
   }
   
   .scroll_menu ul{
   	  margin-left: 244px;
   }
   
   .screen-poster-container {
   	 margin-top: 5vh;
   	 width: 1024px
   }
   
   input{
   
   color : black;
   }
</style>
	
    
    <!-- mast body -->
    <div id="mast-body">
        <div class="container">
            <!-- toparea -->
            <div class="toparea">
                <h2 class="title">
                    <br>
                    &emsp; 회원정보변경<!-- 회원정보변경 -->
                </h2>
                <div class="headline">
                    &emsp;회원정보를
                    <br>
                    &emsp;정확하게 입력해주세요.
                    <br>
                    &emsp;
                </div>
            </div>
            <!-- //toparea -->
    
            <!-- contents -->
  <form id="memberUpdateFrm" method="post" > 
  <input type="hidden" name="address"  />
  <input type="hidden" name="phone"  />
  <input type="hidden" name="password"  />
  <input type="hidden" name="memberId"  />
  <input type="hidden" name="email"  />
  <input type="hidden" name="grade"  />
            <div class="contents">
    
                <!-- section : 회원정보 수정 -->
                <div class="section __half">
                    <h3 class="subject __underline">
                        <em>회원정보입력<!-- 회원정보입력 -->
                            <span class="__require-info __point-color">
                                <small>*는 필수입력 항목입니다.</small><!-- *는 필수입력 항목입니다. -->
                            </span>
                        </em>
                    </h3>
    
                    <!-- 이름 -->
                    <div class="row" id="div-cstNm">
                        <div class="col-md">
                            <label><em class="__point-color">*</em>이름</label><!-- 이름 -->
                        </div>
                        <div class="col-md">
                            <div class="inner">
                                   <div class="ui-input active">
                                       <input type="text" name="memberName" value="<%=memberName %>" size=30 > 
                                   </div>
                            </div>
                        </div>
                    </div>
                    
                    <!-- 등급 -->
                     <div class="row" id="div-cstNm">
                        <div class="col-md">
                            <label>회원 등급</label>
                        </div>
                        <div class="col-md">
                             <div class="inner">
                                   <div class="ui-input active">
                                       <input type="text" id="grade1" value="<%=grade %>" size=30 readonly> 
                                   </div>
                            </div>
                        </div>
                    </div>
    
    
                    <!-- 아이디 -->
                    <div class="row" id="div-onlId">
                        <div class="col-md">
                            <label>아이디</label> <!-- 아이디 -->
                        </div>
                        <div class="col-md">
                            <div class="inner">
                                   <div class="ui-input active">
                                       <input type="text" id="memberId1" value="<%=memberId %>" size=30 readonly> 
                                   </div>
                            </div>
                        </div>
                    </div>
                   				
   					 <!-- 생년월일 -->
                    <div class="row" id="div-pswd">
                        <div class="col-md">
                            <label>생년월일</label> 
                        </div>
                        <div class="col-md">
                             <div class="inner">
                                   <div class="ui-input active">
                                       <input type="text" name="member_ssn" maxlength="20" id="member_ssn"  value="<%=memberSsn %>" size=30 readonly> 
                                   </div>
                            	</div>
                           </div>
                       </div>
                    <!-- //생년월일 -->
                    
                    
                    <!-- 비밀번호 -->
                    <div class="row" id="div-pswd">
                        <div class="col-md">
                            <label>비밀번호</label> <!-- 비밀번호 -->
                        </div>
                        <div class="col-md">
                             <div class="inner">
                                   <div class="ui-input active">
                                       <input type="password" name="oldpassword" maxlength="20" id="oldpassword"  value="<%=password %>" size=30 > 
                                   </div>
                            	</div>
                           </div>
                       </div>
                    <!-- //비밀번호 -->
                    
                     <!--새 비밀번호 -->
                    <div class="row" id="div-pswd">
                        <div class="col-md">
                            <label><em class="__point-color">*</em> 새 비밀번호 <br><br></label> <!-- 비밀번호 -->
                            <span><em class="__point-color">*</em> 비밀번호 확인 </span> <!-- 비밀번호 -->
                        </div>
                        <div class="col-md">
                             <div class="inner">
                                   <div class="ui-input active">
                                       <input type="password" name="newPassword" maxlength="20" id="newPassword"  value="" size=30 > 
                                   </div>
                            	</div>
                            	<div class="inner">
                                   <div class="ui-input active" style="margin: 15px 5px;">
                                       <input type="password" name="password2" maxlength="20" id="passwordCheck"  value="" size=30  > 
                                   </div>
                            	</div>
                           </div>
                       </div>
                    <!-- //새 비밀번호 -->
    
                  
    
                    <!-- 휴대폰 번호 -->
                    <div class="row __depth" id="div-mblNo">
                        <div class="col-md">
                            <label><em class="__point-color">*</em>휴대폰 번호</label>  <!-- 휴대폰 번호 -->
                        </div>
                        <div class="col-md">
                            <div class="form-wrap __mobile __telecom type1" id="celPhoneView" type="1">
                                <div class="inner">
                                    <select title="통신사" id="celphone_no0" name="celphone_carrier" class="ui-select"><!-- 통신사 -->
                                        <option value="">선택<!-- 선택 --></option>
                                        
                                            <option value="1">SKT</option>
                                        
                                            <option value="2">KT</option>
                                        
                                            <option value="3">LGU+</option>
                                        
                                            <option value="4">SKT알뜰폰</option>
                                        
                                            <option value="5">KT알뜰폰</option>
                                        
                                            <option value="6">LGU+알뜰폰</option>
                                        
                                            <option value="9">ETC</option>
                                    </select>
                                    <select title="통신사번호" id="celphone_no1" name="celphone_no1" class="ui-select">  <!-- 통신사번호 -->
                                        <option value="">선택<!-- 선택 --></option>
                                        
                                            <option value="010">010</option>
                                        
                                            <option value="011">011</option>
                                        
                                            <option value="016">016</option>
                                        
                                            <option value="017">017</option>
                                        
                                            <option value="018">018</option>
                                        
                                            <option value="019">019</option>
                                        
                                    </select>
                                    <div class="ui-input active">
                                        <input id="celphone_no2" name="celphone_no2" type="tel" onkeydown="return fnNumOnly(event);" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" style="ime-mode:disabled;" maxlength="4" title="휴대폰 번호 중간자리" >  <!-- 휴대폰 번호 중간자리 -->
                                    </div>
                                    <div class="ui-input active">
                                        <input id="celphone_no3" name="celphone_no3" type="tel" maxlength="4" onkeydown="return fnNumOnly(event);" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" style="ime-mode:disabled;" title="휴대폰 번호 뒷자리" > <!-- 휴대폰 번호 뒷자리 -->
                                    </div>
                                </div>
                            </div>
 
                            <span class="__point-color"><small>*휴대폰 번호를 정확하게 입력하지 않을 경우 회원 혜택이 제한될 수 있습니다.<!-- 휴대폰 번호를 정확하게 입력하지 않을 경우 회원 혜택이 제한될 수 있습니다. --></small></span>
                        </div>
                    </div>
                    <!-- 휴대폰 번호 선택 -->
    
                    <!-- 이메일  -->
                    <div class="row __depth" id="div-elcAdd">
                        <div class="col-md">
                            <label><em class="__point-color">*</em>이메일 주소</label>  <!-- 이메일 주소 -->
                        </div>
                        <div class="col-md">
                            <div class="form-wrap __normal type1" id="elcAddView" type="1">
                                <div class="ui-input active">
                                    <input type="email" id="user-email" name ="email" title="이메일주소를 입력해주세요." value="<%=email %>"> 
                                    <span class="placeholder">이메일주소를 입력해주세요.</span> <!-- 이메일 주소를 입력해주세요. -->
                                </div>
                            </div> 
                        </div>
                     </div>
                    
                    <!-- 주소 -->
                    <div class="row __depth" id="div-add">
                        <div class="col-md">
                            <label for="post-code-home"><em class="__point-color">*</em>주소
                            <br>
                            <br>
                            <br>
                            (상세주소)
                            </label>  <!-- 주소 -->
                        </div>
                        <div class="col-md">
                            <div class="form-wrap__choice __post">
                                
                                <!-- 자택 주소입력 -->
                                <div class="postwrap type1" id="homeAddView" data-type="1">
                                    <div class="inner postcode">
                                        <button type="button" class="ui-button __square-small __black" name="postCall" onclick="sample6_execDaumPostcode();">
                                          	 우편번호
                                        </button> <!-- 우편번호 -->
        
                                        <div class="ui-input active">
                                            <input type="tel" id="h-post-code" title="우편번호" maxlength="6" > <!-- 우편번호 -->
                                        </div>
                                    </div>
                                    <div class="inner">
                                        <div class="ui-input active">
                                            <input type="text" id="h-post-address-1" title="상세주소 1"  size=30 >  <!-- 상세주소 1 -->
                                        </div>
                                    </div>
                                    <div class="inner">
                                        <div class="ui-input active">
                                         <input type="text" id="h-post-address-2" title="상세주소 2"  size=30  >  <!-- 상세주소 2 -->
                                           
                                        </div>
                                    </div>
                                </div>
                                
                            </div>
                        </div>
                    </div>
                    <!-- 주소선택 -->
    
                    <!-- 전화번호 -->
                    <div class="row __depth" id="div-telNo">
                        <div class="col-md">
                        <label for="user-phone-0"><em class="__point-color __point-color">*</em>선호 장르 선택<br>&emsp;(최대 3개)</label> <!-- 전화번호 -->
                        </div>
                        <div class="col-md">
                        <input type="checkbox" name="genre"  value="드라마"<%= genreChecked(genreList, "드라마") %>><label for="genre">드라마</label>
						<input type="checkbox" name="genre"  value="판타지"<%= genreChecked(genreList, "판타지") %>><label for="genre">판타지</label>
						<input type="checkbox" name="genre"  value="서부"<%= genreChecked(genreList, "서부") %>><label for="genre">서부</label>
						<input type="checkbox" name="genre"  value="공포"<%= genreChecked(genreList, "공포") %>><label for="genre">공포</label>
						<input type="checkbox" name="genre"  value="로맨스"<%= genreChecked(genreList, "로맨스") %>><label for="genre">로맨스</label>
						<br />
						<input type="checkbox" name="genre"  value="스릴러"<%= genreChecked(genreList, "스릴러") %>><label for="genre">스릴러</label>
						<input type="checkbox" name="genre"  value="컬트"<%= genreChecked(genreList, "컬트") %>><label for="genre">컬트</label>
						<input type="checkbox" name="genre"  value="다큐멘터리"<%= genreChecked(genreList, "다큐멘터리") %>><label for="genre">다큐멘터리</label>
						<input type="checkbox" name="genre"  value="코미디"<%= genreChecked(genreList, "코미디") %>><label for="genre">코미디</label>
						<input type="checkbox" name="genre"  value="가족"<%= genreChecked(genreList, "가족") %>><label for="genre">가족</label>
						<br />
						<input type="checkbox" name="genre"  value="미스터리"<%= genreChecked(genreList, "미스터리") %>><label for="genre">미스터리</label>
						<input type="checkbox" name="genre"  value="전쟁"<%= genreChecked(genreList, "전쟁") %>><label for="genre">전쟁</label>
						<input type="checkbox" name="genre"  value="애니메이션"<%= genreChecked(genreList, "애니메이션") %>><label for="genre">애니메이션</label>
						<input type="checkbox" name="genre"  value="범죄"<%= genreChecked(genreList, "범죄") %>><label for="genre">범죄</label>
						<input type="checkbox" name="genre"  value="뮤지컬"<%= genreChecked(genreList, "뮤지컬") %>><label for="genre">뮤지컬</label>
						<br>
						<input type="checkbox" name="genre"  value="SF"<%= genreChecked(genreList, "SF") %>><label for="genre">SF</label>
						<input type="checkbox" name="genre"  value="액션"<%= genreChecked(genreList, "액션") %>><label for="genre">액션</label>
                      
                        </div>
                    </div>
                    <!-- 전화번호 선택 -->
    
                    <div class="description"></div>
                        <div class="btn_btm_wrap">
                            <a href="<%= request.getContextPath() %>/member/mypage" class="btn_col3 ty6" >취소</a>
                            <button type="button" class="btn_col2 ty6" onclick="updateMember();"> 확인 </button>
                            <button type="button" class="btn_col2 ty6" onclick="deleteMember();" style="background-color: #ea0000; border: 1px solid #ea0000;"> 탈퇴 </button>
                        </div>
                    </div>
                </div>
               </form>
    
            </div>
            <!-- //contents -->
        </div>
 	
 	
        <!-- 탈퇴처리-->
 	<form 	
	name="memberDelFrm" 
	action="<%= request.getContextPath() %>/member/memberDelete" 
	method="POST">
	<input type="hidden" name="memberId" value="<%= loginMember.getMember_id()%>" />
</form>
    <!-- //탈퇴처리 -->
   


    
  <script>
  //전화번호 나눠서 입력처리
  var tmp = "<%=loginMember.getPhone()%>";
  
  var cutTmp = tmp.substr(0,3);
  var cutTmp1 = tmp.substr(3,4);
  var cutTmp2 = tmp.substr(7,4);
 
  var $phone1 = $("#celphone_no1");
  var $phone2 = $("#celphone_no2");
  var $phone3 = $("#celphone_no3");
  
  $phone1.attr('value',cutTmp);
  $phone2.attr('value',cutTmp1);
  $phone3.attr('value',cutTmp2);
  
  
  
  
//신규비밀번호 일치 검사
  $("#passwordCheck").blur(passwordValidate);

  function passwordValidate(){
  	var $newPassword = $("#newPassword");
  	var $newPasswordCheck = $("#passwordCheck");
  	if($newPassword.val() != $newPasswordCheck.val()){
  		swal("입력한 비밀번호가 일치하지 않습니다.","다시 입력해 주세요","warning");
  		$newPassword.select();
  		return false;
  	}
  	return true;	
  }

  //확인버튼 눌렀을 때
  function updateMember(){
	  
		$("#memberUpdateFrm")
			.attr("action","<%=request.getContextPath() %>/member/memberUpdate")
			.submit();
	}
  
  //탈퇴 버튼
  function deleteMember(){
	  swal({
		  title: "정말 탈퇴하시겠습니까?",
		  text: "탈퇴하시면 개인정보는 완전히 삭제됩니다.",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
		}).then((willDelete) => {
		if (willDelete) {
		$(document.memberDelFrm).submit();
		swal("탈퇴 완료! 이용해주셔서 감사합니다.", {
		icon: "success",
		});
		} else {
		swal("탈퇴 실패!");
		}
	});
		
	}
	
 
  
  
  //업데이트 전달값
  
 $("#memberUpdateFrm").submit(function(){ 
	 
	  
	
	 //이메일보내기
	 var $email1 = $("#user-email").val();
	 console.log($email1);
	 $("input[name=email]").attr('value',$email1 );
	 
	 
	 //등급값 보내기
	 var $grades = $("#grade1").val();
	 console.log($grades);
	 $("input[name=grade]").attr('value',$grades );
	 
	 //아이디 보내기
	 var $memberIds = $("#memberId1").val();
	 console.log($memberIds);
	 $("input[name=memberId]").attr('value',$memberIds );

	 //비밀번호 값 넣기 //비밀번호 보내기 성공

	 if ($("#newPassword").val() == ""){
	 var $lastPwd = $("#oldpassword").val();
	 console.log($lastPwd);
	 $("input[name=password]").attr('value',$lastPwd);
	 }else{
		 var $lastPwd = $("#newPassword").val();
		 console.log($lastPwd);
		 $("input[name=password]").attr('value',$lastPwd);
	 }
	 
	 
	 //주소 합치기
	 var $arrAll = $("#h-post-code").val() + $("#h-post-address-1").val() + $("#h-post-address-2").val();
	 console.log($arrAll);
	 $("input[name=address]").attr('value', $arrAll);
	 
	//전화번호 합치기
		var $phones = $phone1.val() + $phone2.val() + $phone3.val();
	   
	   console.log($phones);
	   
	   $("input[name=phone]").attr('value', $phones);
	  
	 
	 
	 //비밀번호
	 var $oldPassword = $("#oldpassword");
		var $newPassword = $("#newPassword");
		
		if(!passwordValidate()){
			return false;
		}
		
		if($oldPassword.val() == $newPassword.val()){
			
			swal("기존비밀번호와 신규비밀번호는 같을 수 없습니다.","다시 입력해 주세요","warning");
			$oldPassword.select();
			return false;
			
		} else{
			//다를 경우 새 비밀번호 값을 password에 넣어줘야함
			
		}
	
	 
	 
 	//이름
	 var $memberName = $("#memberName");
 	if($memberName == null){
		if(/^[가-힣]{2,}$/.test($memberName.val()) == false){
			swal("이름은 한글 2글자 이상이어야 합니다.","다시 입력해 주세요","warning");
			$memberName.select();
			return false;
		}
 	}
	//-제거하기
	$phone2.val($phone2.val().replace(/[^0-9]/g, ""));//숫자아닌 문자(복수개)제거하기
	$phone3.val($phone3.val().replace(/[^0-9]/g, ""));//숫자아닌 문자(복수개)제거하기
	
	if(/^[0-9]{4}$/.test($phone2.val()) == false){
		swal("유효한 전화번호가 아닙니다.","다시 입력해 주세요","warning");
		$phone2.select();
		return false;
	}
	
	if(/^[0-9]{4}$/.test($phone3.val()) == false){
		swal("유효한 전화번호가 아닙니다.","다시 입력해 주세요","warning");
		$phone3.select();
		return false;
	}
	return true;
});

  
//input박스 입력안되서  사용
  $("input[type=text]:not([disabled])").first().focus(); 
  
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
                    document.getElementById("h-post-address-2").value = extraAddr;
                
                } else {
                    document.getElementById("h-post-address-2").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById("h-post-code").value = data.zonecode;
                document.getElementById("h-post-address-1").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("h-post-address-2").focus();
            }
        }).open();
    }
    
    //장르 갯수 스크립트
    var cnt = $("[name=genre]:checked").length;
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
    
    
<style>
.footer{margin-top : 10vh;}

</style>
<%!
	//메소드 선언문
	public String genreChecked(List<String> genreList, String genre){
		return genreList != null && genreList.contains(genre) ? 
				"checked" : 
					"";
	}

%> 
 
 
  <%@ include file="/WEB-INF/views/common/footer.jsp"%>