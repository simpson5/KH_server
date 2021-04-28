<%@page import="com.simp.board.model.vo.Review"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Date"%>
<%@page import="com.simp.product.model.vo.PaymentHistory"%>
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
    

    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/content.css" />
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/defult.css" />
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/dev.css" />
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/souce.css" />
    
    
    <script src="<%= request.getContextPath() %>js/jquery-3.6.0.js"></script>
    <script src="<%= request.getContextPath() %>js/jquery.easing.1.3.js"></script>
    <title>resetMypage</title>
    
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/css_1050.css" type="text/css" media="screen"></head>
</head>    

<body id="KOR" data-device="0" oncontextmenu="return false" onselectstart="return false" ondragstart="return false"> <!-- KOR / ENG / JPN / CHN-CN / CHN-TW -->
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%
	String memberId = loginMember.getMember_id();
	//String password = loginMember.getPassword();
	String memberName = loginMember.getMember_name();
	String email = loginMember.getEmail() != null ? loginMember.getEmail() : "";
	String phone = loginMember.getPhone();
	String address = loginMember.getAddress() != null ? loginMember.getAddress() : "";
	int point = loginMember.getPoint();
	
	
	// 결제정보 가져오기
	
	List<Review> list = (List<Review>)request.getAttribute("list");
	int amount = (int)request.getAttribute("amount");
	int level = Integer.parseInt(loginMember.getMember_grade());
	
	int next = 0;
	for(int i = 1; i <= level; i ++){
		next = next + i;
	}
	
	int prev = 0;
	for(int i = 1; i < level; i ++){
		prev = prev + i;
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
      width: 1024px
   }
</style>
<body>
    <div id="contents" class="contents_mypage">
        <!-- 마이페이지 공통부분 -->
        <div class="mypage_top_infor" id="mypage_top_infor">
            <h2 class="hidden">마이페이지</h2>
            <div class="mypage_box">
                <h3 class="hidden">회원 등급 및 포인트</h3>
                <div class="my_info"><div class="grade_area">
                    <span class="txt_rank_level1 ml5">Lv.<%= level %></span>
                </div>
                <p class="name">
                    <strong id="memberId"><%= memberId %>님!</strong>반가워요!
                </p>
                <% if(level != 5) { %>
	                <div class="next_rank">
	                    <p>3분기 Lv.<%= level + 1 %>까지 
	                        <em class="font22"><%= next * 100000 - amount %>원 남았다!</em>
	                    </p>
	                </div>
            </div>
            <div class="bx_grade merge2020">
                <div class="area_gauge friendslv">
                    <div class="gauge" style="width: <%= (((next - prev) * 100000.0) - (next * 100000 - amount)) * 100 / ((next - prev) * 100000.0)  %>%;">
                    </div>
                    <ul class="infograde">
                        <li>
                            <strong class="" style="font-weight: normal;">lv.<%= level %></strong>
                            <em><%= prev * 100000 %>원</em>
                        </li>
                        <li>
                            <strong class="">lv.<%= level + 1 %></strong>
                            <em><%= next * 100000 %>원</em>
                        </li>
                    </ul>
                </div>
                <% } else {%>
					<div class="next_rank">
						<p>
							<em style="font-size:30px">만렙입니다!</em>
						</p>
					</div>
				</div>
				<div class="bx_grade merge2020">
					<div class="area_gauge friendslv">
						<div class="gauge" style="width: 100%;">
					</div>
					<ul class="infograde">
						<li>
						<strong class="" style="font-weight: normal;">lv.<%= level %></strong>
						<em><%= prev * 100000 %>원</em>
						</li>
						<li>
						<strong class="">lv.???</strong>
						 <em><%= amount %>원</em>
						</li>
					</ul>
				</div>
                <% } %>
                <a class="btn_col5 ty5 rnd">MEMBERSHIP ZONE</a>
            </div>
            <div class="btn_wrap">
                <a href="" target="_blank" title="">
                    <span class="txt_img">
                        <!-- <img src="" alt="POINT"> -->
                    </span> 
                    <em><%= point %>P</em>
                </a>
                <a href="" title="쿠폰함 페이지 이동">쿠폰함 
                    <em class="txt_color">0</em>
                </a>
            </div>
        </div>
        </div>
        <!-- //마이페이지 공통부분 -->
        <!-- 마이페이지 메뉴 공통부분 -->
        <div id="mypage_top_menu">
            <ul class="tab_wrap_lnk">
                <li class="">
                    <a href="" style="width: 25%; left: 0%;">
                        <span>결제내역</span>
                    </a>
                </li>
                <li class="active">
                    <a href="<%= request.getContextPath()%>/member/my_review" style="width: 25%; left: 25%">
                        <span>My 내역</span>
                    </a>
                </li>
                <li class="">
                    <a href="<%= request.getContextPath()%>/member/mypageMyMovie" style="width: 25%; left: 50%;" onclick="myMovie()">
                        <span>찜한 영화</span>
                    </a>
                    
                </li>
                <li class="">
                    <a href="<%= request.getContextPath()%>/member/mypageview" style="width: 25%; left: 75%;">
                        <span>MY 정보 관리</span>
                    </a>
                </li>
            </ul>
        </div>
        <!-- //마이페이지 메뉴 공통부분 -->
<!-- 리뷰 리스트 -->
<table class="review_tb">
			<thead>
				<tr>
					<th scope="col">제목</th>
					<th scope="col" class="al">글쓴이·날짜</th>
					<th scope="col" class="al"><a
						href="?st=code&sword=80219&od=goodcnt" class="fs_11 u">추천</a> <img
						src="https://ssl.pstatic.net/imgmovie/2007/img/common/bullet_arrow_bottom_1.gif"
						alt="" width="5" height="3"></th>
					<th scope="col"><a href="?st=code&sword=80219&od=readcnt"
						class="fs_11 u">조회</a> <img
						src="https://ssl.pstatic.net/imgmovie/2007/img/common/bullet_arrow_bottom_1.gif"
						alt="" width="5" height="3"></th>
				</tr>
			</thead>
			<tbody>
			<% if(list != null) { %>
			<% 	for(Review r : list) {
			%>
				<tr>
					<td class="title"><a href="<%= request.getContextPath() %>/review/board?no=<%= r.getReviewNo() %>"><%= r.getTitle() %></a></td>
					<td class="author num"><a href=""><%= r.getWriter() %></a> <br /> <%= r.getReviewDate() %></td>
					<td class="num c_ff4200"><img
						src="https://ssl.pstatic.net/imgmovie/2007/img/common/icon_recommend_1.gif"
						alt="" width="14" height="13"> <%= r.getRecomCnt() != 0 ? r.getRecomCnt() : "" %></td>
					<td class="num"><%= r.getReadCnt() != 0 ? r.getReadCnt() : "" %></td>
				</tr>
				<% 	} %>
				<% } else { %>
					<tr>
						<td colsapn=4>
							없음
						</td>
					</tr>
				<% 	} %>
			</tbody>
		</table>    
        
</div>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
<%@ include file="/WEB-INF/views/common/foaltingbar_link.jsp"%>
<style>
.footer{
margin-top : 10vh;
  }
  
.mypage_wrap_2{
	height: auto;
	overflow:hidden;
}

.poster {
	text-align:center;
}

/* 리뷰 테이블 부분 */
.review_tb {
	padding: 0;
	border-top: 1px solid rgb(44, 44, 44);
	border-bottom: 1px solid rgb(44, 44, 44);
	width: 100%;
	border-collapse: collapse;
	text-align: center;
}

table.review_tb th, td {
	/* border-bottom: 1px solid gray; */
	padding: 0;
	margin: 0;
}

.review_tb td a {
	text-decoration: none;
	color: rgb(61, 61, 61);
}

.review_tb th {
	height: 35px;
	color: rgb(53, 52, 52);
	border-bottom: 1px solid rgb(202, 202, 202);
}

.review_tb td {
	border-bottom: 1px solid rgb(235, 235, 235);
}

.review_tb th a {
	color: rgb(80, 80, 80);
}

td.title {
	text-align: left;
	font-size: 14px;
}
</style>
</body>
</html>        