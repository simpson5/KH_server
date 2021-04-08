<%@page import="member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//로그인한 경우
	Member memberLoggedIn = (Member)session.getAttribute("memberLoggedIn");
	//System.out.println("memberLoggedIn@header.jsp="+memberLoggedIn);

	//쿠키관련
	boolean saveId = false;
	String loginId = "";
	Cookie[] cookies = request.getCookies();
	if(cookies != null) {
		for(Cookie c : cookies) {
			String k = c.getName();
			String v = c.getValue();
			
			if("saveId".equals(k)) {
				saveId = true;
				loginId = v;
			}
		}
	}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>PIXARBOX</title>

<!-- css -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<!--웹폰트추가  -->
<link href="https://fonts.googleapis.com/css?family=Anton|Do+Hyeon|Nanum+Gothic+Coding&display=swap&subset=latin-ext,vietnamese" rel="stylesheet">

<!-- icon -->
<link rel="stylesheet" href="http://cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">

<!-- jquery -->
<script src="<%=request.getContextPath() %>/js/jquery-3.4.1.js"></script>

<!-- script slick, bxslider -->
<!-- <script type="text/javascript" src="http://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
<script type="text/javascript" src="http://kenwheeler.github.io/slick/slick/slick.min.js"></script>
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script> -->

<!--슬라이드코드추가--> 
<link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<!-- 파비콘추가-->
<link rel="shortcut icon" href="<%=request.getContextPath() %>/images/favicon.ico" type="image/x-icon" />
<link rel="icon" href="<%=request.getContextPath() %>/images/favicon.ico" type="image/x-icon" />

</head>
<body>
<!-- 헤더  -->
<header id="header" class="header_main">

<!-- 두번째 헤더 -->
    <div class="sec_haed">
	    <!-- 고정헤더 -->
	    <nav id="topUtil" class="top_util">
	        <span>PIXAR</span>
	        <ul class="nav">
	        <%-- 로그인하지 않았을 경우 --%>
	        <% if(memberLoggedIn == null) { %>
	            <li>
	                <a href="<%=request.getContextPath() %>/member/memberLogin" id="login_btn">로그인</a>        
	            </li>
	            <li>
	                <a href="<%=request.getContextPath() %>/member/memberEnroll" id="sign_btn">회원가입</a>   
	
	            </li>
	            <li>
	                <a href="<%=request.getContextPath() %>/customer/notice/notice.do" id="customer_btn">고객센터</a>
	            </li>
	        <%-- 관리자로 로그인했을 경우 --%>
	        <% }
	        else if(memberLoggedIn!=null && "admin".equals(memberLoggedIn.getMemberId())) { 
	        %>
	            <li>
	                <a href="<%=request.getContextPath() %>" id="home">홈</a>   
	
	            </li>
	        	<li><a href="<%=request.getContextPath() %>/member/memberLogout" id="sign_out_btn">로그아웃</a></li>
				<li><a href="<%=request.getContextPath()%>/admin/memberList" id="admin_btn">관리자페이지</a></li>
				<li><a href="<%=request.getContextPath() %>/customer/notice/notice.do" id="customer_btn">고객센터</a></li>
	        <% }
	        else { %>
	            <li>
	                <a href="<%=request.getContextPath() %>" id="home">홈</a>   
	
	            </li>
	            <li>
	                <a href="<%=request.getContextPath() %>/member/memberLogout" id="sign_out_btn">로그아웃</a>   
	
	            </li>
	            <li>
	                <a href="<%=request.getContextPath() %>/member/my/mypage.do?memberId=<%=memberLoggedIn.getMemberId() %>" id="mypage_btn">마이페이지</a>   
	            </li>
	            <li>
	                <a href="<%=request.getContextPath() %>/customer/notice/notice.do" id="customer_btn">고객센터</a>
	            </li>
	    <% } %>
	        </ul>
	    </nav>
	<!-- 고정헤더 끝 -->
        <nav id="secUtil" class="sec_util">
            
            <span>
                <a href="<%=request.getContextPath() %>">PIXAR<br><br>BOX</a>
            </span>
                
            <ul class="nav-sec">
                <li>
                    <a href="<%=request.getContextPath() %>/ticket/ticketingStep1/step1.do">예매</a>
                </li>
                <li>
                    <a href="<%=request.getContextPath() %>/movie/boxOffice/order1.do">영화</a>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/theater/theaterInfo">영화관</a>
                </li>
                <li>
                    <a href="#">이벤트</a>
                </li>
                <li>
                    <a href="#">스토어</a>
                </li>
                <li>
                    <a href="#">VOD</a>
                </li>
            </ul>
        </nav>
    </div><!--div.sec_haed-->
    <!-- 두번째 헤더 끝 -->
</header>
<!-- 헤더 끝 -->

<!-- <section id="content"> -->
