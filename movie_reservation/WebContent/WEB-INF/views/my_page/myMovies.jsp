 <%@page import="java.util.List"%>   
<%@page import="com.simp.movie.model.service.MovieService"%>
<%@page import="com.simp.movie.model.vo.ScreenMovie"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% List<ScreenMovie> dip_List = (List<ScreenMovie>)request.getAttribute("dip_list"); %>
    
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
    
    
    <script src="js/jquery-3.6.0.js"></script>
    <script src="js/jquery.easing.1.3.js"></script>
    <title>resetMypage</title>
    
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    
   
    
    <script type="text/javascript">
            if(window.console == undefined){console={log:function(){}};};
           //초기화 데이터(from 서버)
            Common.init('eyJmbHdObyI6IlFRcW5KcWRwWEIiLCJha1NyblZkdFZhbHVlIjoic0EwOFBHa1VQczNOSFJKV21sck5xaE9TN2kwZ3VkUGpGaVl0V3RSbHlpb1x1MDAzZCJ9.ejFmZzRkWlU0QjJndkdFc3oyQ2M3aFFSOFFWU1FZVTl1MGxQcWRxZU0va2VPMUR5ejdFTUhCWFF0bHNrdVNvM1RmWkphT3pPaDY0QVRuL3VQN1oyMEdERlkxckVBSy9KMlN4VUptVXJNUlg5VVgvekcwV255d0FkRHJUbVZ2MUR3YXB4L2xuZnBSVlYvb3NHdzRBWm9QVTBHKzBMMTQ4bkkzUDlHUXZjZlBKdVArYU8zTjJaSDZFVWhLYUtabmlIQnUyb1ZOT1pHR0E4bGZydTgzYVQwUDJFOUhwS2ZhS3pPL21yc3dLOStjQmpQVmI5SFBxdG8xVlI1bjNaYzJGeEEvME9PK283N1JlNHRHaXJtL2o2RGpyRWlkZEo3UVZQcjc1aUtseDdORTNoMmEyTlJRcWlGOVZuR1N0Wkt5Y3BuN0ZndXNBdkV5emRiTDlWNjlHaStwOE8xQlpHQjFxZEhJTXRSSTN3QS9Od21jazkvcjhEZ0FobkZxclRUMUtrYnRMb1ZGcGQ1WHVjdk1nWWduaVRzNVF2VDdkOVBYa0NsK0RQM3dwdnZRbWFENlJRN3VjTk91dzJJRm5MbDk0ZmFWSGV4cmVuTngrZXNtc1IyYS9UQ0JsYldLRE9lR1FnYnB3aEdOUFFzdHpUMGVpenBrQm5PMmZUU0JvSjg5V3JQb0lSam93UG95L2k1STFvbUVSODlPTDNTQmcwNGJIRkUxREQ5QzZNdU5ZQSt2L2lMU0dYKzVsSGlQcWJkWG1aSGkwenJWNThxRSs1cTUrTHFjYWowYmRDeG1RM3R6U1d2MU1uVTVvOFpnbWdCb1Nqd3VwbzhrbDdRcnNydWlCdVhyb0ozZHUwMjdMTnY1WkRTYnFKRTF4YTJReVFjTExtZ0lvWW9DSjVBRFZGeVpSUWhvdzVlaExNL1lXdUpJa0E4ejZWTys3K2xjNW5oNGtDKy9wb3RnPT0=',"N");
            
           var langCode = Common.getChannelData().langCode; 	// 언어코드
            /*
            document.oncontextmenu=function(e){if(e !== undefined ){return false;}}
            document.onselectstart=function(e){if(e !== undefined ){return false;}}
            document.ondragstart=function(e){if(e !== undefined ){return false;}}
            
           //우측 클릭 방지
            document.onmousedown = function (e){
            if(e !== undefined && e.button == 2){
                    return false;
            }
        };
           */
    </script>
    

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
<%

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
   
   .dip_movie_list .tit {
    display: block;
    padding-top: 8px;
    font-size: 20px;
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
                    <span class="txt_rank_level1 ml5">Lv.1</span>
                </div>
                <p class="name">
                    <strong><%= memberId %>님!</strong>반가워요!
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
                <li class="active">
                    <a href="<%= request.getContextPath()%>/member/mypage" style="width: 25%; left: 0%;">
                        <span>결제내역</span>
                    </a>
                </li>
                <li class="">
                    <a href="<%= request.getContextPath()%>/member/my_review" style="width: 25%; left: 25%">
                        <span>My 리뷰</span>
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
    

<div class="mypage_wrap_2" id="mypage_wrap" style="display : flex;">
<%
int n = 0;
if(dip_List != null) {
	for(ScreenMovie sm : dip_List) {
		n ++;
%>
	<div class="poster" style="border: 1px solid gold; width:33.3vw; margin: 10px auto;">
    	<div>
    		<a href="<%= request.getContextPath() %>/movie/movie_info?movie_title=<%= sm.getMovieTitle() %>">
	    		<img src="<%= request.getContextPath() %>/upload/movie/<%= sm.getMainPoster() %>" style="width:10vw;">
    		</a>
    	</div>
    	<p>
    		<strong class="tit" style="cursor: pointer;">
    			<%= sm.getMovieTitle() %>
    		</strong>
    	</p>
	</div>
	<% if(n == 3){ %>
<%  
			n=0;
		} 
	}
}
%>	
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
</style>
</body>
</html>