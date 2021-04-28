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
	
	List<PaymentHistory> list = (List<PaymentHistory>)request.getAttribute("list");
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
                <li class="active">
                    <a href="" style="width: 25%; left: 0%;">
                        <span>결제내역</span>
                    </a>
                </li>
                <li class="">
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
    


<div class="mypage_wrap" id="mypage_wrap">
    <div class="tab_con">
        <h4 class="hidden">예매내역</h4>
        <div class="mypage_top_area">
            <p class="txt_notice">
                <span class="txt_caution1">최근 70일 이내 정보만 조회가 가능합니다.</span>
            </p>
        </div>
        <% if(list == null || list.isEmpty()){ %>
			<div class="ticket_list">
            <ul class="ticket_info new2020">
                <li class="new_day" id="key0">
                    <div class="date">
                        <span class="week"></span>
                    </div>
                    <button type="button" id="title0" aria-expanded="false">
                        <strong class="tit"> </strong>
                        <span class="sum">
                            <em>조회된 내역이 없습니다</em>
                        </span>
                        <span class="num">
                            <em></em>
                        </span>
                    </button>
                   
                    </li>
                </ul>
            </div> 
		
		<% } else { %>
        <div class="my_ticket_list">
	        <table>
			<% for(PaymentHistory p : list){ %>
	        	<tr>
	        		<td class="pay_date" style="width:150px;"><%=p.getPayDate() %></td>
	        		<td class="pay_content" style="width:450px;"><%= p.getPayContent() %></td>
	        		<td class="pay_amount" style="width:100px;"><%=p.getAmount() %></td>
	        		<td class="pay_point" style="width:100px;"><%=p.getPoint() %></td>
	        		<td class="pay_no"><%= p.getPaymentNo() %></td>
	        		<% if(p.getPaymentNo().charAt(0) == 'M') { %>
	        			<td><button class="refund_btn">환불</button></td>
	        		<% } %>
	        	</tr>
			<% } %>
	        </table>
        </div>  
		<% } %>     
            <div class="txt_list_wrap">
                <div class="list_bdr_box">
                   <h5 class="title txt_caution2">유의사항</h5>
                    <div class="list_inner_box">
                        <strong class="tit">환불 안내</strong>
                        <dl class="list_define">
                            <dt>신용카드</dt>
                            <dd>결제 후 3일 이내 취소 시 승인 취소 가능합니다.</dd>
                            <dd>3일 이후 예매 취소 시 영업일 기준 3일 ~ 7일 이내 카드사에서 환불됩니다.</dd>
                        </dl>
                        <dl class="list_define">
                            <dt>체크카드</dt>
                            <dd>결제 후 3일 이내 취소 시 당일 카드사에서 환불 처리됩니다.</dd>
                            <dd>3일 이후 예매 취소 시 카드사에 따라 3일 ~ 10일 카드사에서 환불됩니다.</dd>
                        </dl>
                        <dl class="list_define">
                            <dt>휴대폰 결제</dt>
                            <dd>결제 일자 기준 당월(1일 ~ 말일까지) 취소만 가능합니다.</dd>
                            <dd>익월 취소의 경우 롯데시네마 고객센터(0000-0000)로 문의 주시기 바랍니다.</dd>
                        </dl><dl class="list_define">
                            <dt>모바일캐시비/티머니</dt>
                            <dd>모바일캐시비(선불형): 모바일캐시비 APP 선물함으로 취소금액이 충전됩니다.
                            </dd>
                            <dd>모바일티머니(선불형): 환불 SMS 수신 후 URL 클릭하시면 모바일티머니 APP이 자동으로 실행되어 취소금액이 충전됩니다.                                
                            </dd>
                            <dd>모바일캐시비/티머니(후불형): 취소금액은 환불되지 않고, 신용카드 대금에서 청구 취소됩니다. (취소일 7일 이내 카드사 청구내역에서 확인 가능합니다.)</dd><dd>모바일캐시비/티머니(플라스틱카드): 교통카드충전결제 APP 실행 후, [환불내역]에서 카드 태깅하여 카드로 충전됩니다.</dd></dl><dl class="list_define"><dt>예매 및 추가상품 구매 취소 안내</dt><dd>온라인 예매 및 추가상품 구매 취소는 상영 20분 전까지 가능하며, 20분 이전부터는 현장 취소만 가능합니다.(단, 무대인사 취소는 영화 시작 24시간 전 까지만 가능합니다.)</dd><dd>온라인 예매 후 현장에서 티켓 발권 시 온라인으로 예매 취소는 불가능하며, 현장 취소만 가능합니다.</dd><dd>적립 예정 L.POINT는 영화 관람 다음 날 적립됩니다.</dd><dd>예고편과 광고 상영으로 실제 영화 시작 시간이 10분 정도 차이 날 수 있습니다.</dd><dd>SMS 수신을 이용하시면 예매내역을 휴대폰으로 받을 수 있습니다.</dd><dd>반드시 전체 취소만 가능하며, 예매나 추가상품 중 부분 취소는 불가능합니다.</dd><dd>추가상품 수령 완료 시 예매 및 상품 취소 모두 불가능합니다.</dd><dd>포토티켓 발권 완료 시 환불은 발권된 포토티켓 모두 지참 후 해당 영화관에서만 가능합니다.</dd></dl><dl class="list_define"><dt>상품취소 안내</dt><dd>스토어 상품의 취소기한은 구매일로부터 70일입니다. (취소 관련문의 : 고객센터 1544-8855)</dd><dd>구매취소는 취소기한 내 마이시네마 &gt; 예매/구매내역에서만 가능합니다.(현장취소 불가)</dd><dd>구매하신 상품은 부분환불 및 현금환불이 되지 않습니다.</dd>
                            <dd>좌석 구매 시 함께 구매한 매점 상품은 영화 관람 완료 후에는 취소가 불가합니다.</dd>
                            <dd>얼리버드 상품은 특별관람권이므로 구매 후 7일 이내 환불 가능합니다.</dd>
                        </dl>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<form action="<%= request.getContextPath() %>/payment/refund"
	  method="post"
	  id="refundFrm">
	<input type="hidden" name="input_pay_no" />
</form>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>
<script>
$(".refund_btn").click(function(){
	console.log(1);
	
	$("[name=input_pay_no]").val($(this).parent().prev().text());
	
	console.log($("[name=input_pay_no]").val());
	
	$("#refundFrm").submit();
})
</script>
<style>
.footer{
	margin-top : 10vh;
}

.my_ticket_list table tr{
	height: 50px;
}
.my_ticket_list table tr td{
	font-size: 14px;
}
</style>
  
