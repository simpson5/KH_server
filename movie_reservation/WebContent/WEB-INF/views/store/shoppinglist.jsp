<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.simp.product.model.vo.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/common/floatingbar.jsp"%>
<%
	ArrayList<Product> pd_list = (ArrayList<Product>)request.getAttribute("pd_list");
	HashMap<String, Integer> cart = ((HashMap<String, Integer>)session.getAttribute("cart"));
	int total_price = 0;
%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/shoppinglist.css" />
<style>
	.header_common {
		color : black;
		position : static;
	}
	
	.header_common a {
		color : black;
	}
</style>
<div class="main_container">
<table class="table_box">
	<tr>
		<td><h3>장바구니</h3></td>
		<td><h3>결재 정보 </h3></td>
	</tr>
	<tr>
		<td class="info_box">
			<div>
				<div class="movie_info" style="text-align: center;">
				<table>
<% if(pd_list != null) { %>				
<% for(Product pd :  pd_list) { %>	
					<input type="hidden" value="<%= pd.getPdImg() %>"/>			
					<input type="hidden" value="<%= cart.get(pd.getProductName()) %>"/>			
					<tr>
						<td>
							<p class="product_name"><%= pd.getProductName() %></p>
							<img src="<%= request.getContextPath() %>/upload/product/<%= pd.getPdImg() %>"
								 alt="<%= pd.getPdImg() %>"
								 style="height : 100px;"/>
						</td>
						<td>
							<div style="width: 200px;">
								<div class="btn-box">
									<button class="minus">
										<i class="fas fa-minus"></i>
									</button>
									<span class="pd_cnt"><%= cart.get(pd.getProductName()) %></span>
									<button class="plus">
										<i class="fas fa-plus"></i>
									</button>
									<p class="pd_price"><%= pd.getProductPrice() %></p>
								</div>
							</div>
						</td>
					</tr>
	<% total_price += pd.getProductPrice() * cart.get(pd.getProductName()); %>					
	<% } %>
<% } else {%>
					<p>장바구니가 비었습니다!</p>
<% } %>	
				</table>
				</div>
			</div>
		</td>
		<td class="info_box" style="text-align:center;">
			<table>
				<tr>
					<td>결제 금액</td>
					<td><p id="total_price"><%= total_price %></p></td>
				</tr>
				<tr>
					<td>무통장입금</td>
					<td>카카오페이</td>
				</tr>
				<tr>
					<td colspan=2>
						<button id="payment_btn">결제하기</button>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>
<script>
$(".plus").click(function(){
	console.log($(".pd_cnt").text())
	var n =  parseInt($(this).siblings(".pd_cnt").text()) + 1;
	var pd_price = parseInt($(this).siblings(".pd_price").text());
	$(this).siblings(".pd_cnt").text(n);
	var total_price = parseInt($("#total_price").text()) + pd_price;
	$("#total_price").text(total_price);
})

$(".minus").click(function(){
	if($(this).siblings(".pd_cnt").text() > 0){
		var n =  parseInt($(this).siblings(".pd_cnt").text()) - 1;
		var pd_price = parseInt($(this).siblings(".pd_price").text());
		$(this).siblings(".pd_cnt").text(n);
		var total_price = parseInt($("#total_price").text()) - pd_price;
		$("#total_price").text(total_price);		
	}
})
$("#payment_btn").click(function(){
	var cnt_list = "";
	$(".pd_cnt").each(function(i, cnt){
		cnt_list += $(cnt).text() + ",";
	})
	var pd_list = "";
	$(".product_name").each(function(i, pd){
		pd_list += $(pd).text() + ",";
	})
	
	console.log(cnt_list);
	
	$.ajax({
		url:'<%= request.getContextPath() %>/payment/product',
		data: {
			member_id : '<%= loginMember.getMember_id() %>',
			pd_name : pd_list,
			pd_cnt : cnt_list,
			total_amount : $("#total_price").text(),
		},
		method:'POST',
		dataType:'json',
		success : function(data){
			// 요청 성공시 실행 콜백 함수, 매개 인자로 응답 message 리턴
			console.log("success call!");
			var box = data.next_redirect_pc_url;
			
			var _width = '650';
		    var _height = '500';
		 
		    // 팝업을 가운데 위치시키기 위해 아래와 같이 값 구하기
		    var _left = Math.ceil(( window.screen.width - _width )/2);
		    var _top = Math.ceil(( window.screen.height - _height )/2); 
		    var pay_pop = window.open(box, 'kakao', 'width='+ _width +', height='+ _height +', left=' + _left + ', top='+ _top );
		    var g_oInterval = null;
		 
		    <%-- var popup = function() {
		    	pay_pop = window.open(box, 'kakao', 'width='+ _width +', height='+ _height +', left=' + _left + ', top='+ _top );
		        // 0.5초 마다 감지
		        g_oInterval = window.setInterval(function() {
		            try {
		                // 창이 꺼졌는지 판단
		                if( pay_pop == null || pay_pop.closed ) {
		                    window.clearInterval(g_oInterval);
		                    pay_pop = null;
		                    window.location.href = "<%= request.getContextPath() %>";
		                }
		            } catch (e) { }
		        }, 500);
			    popup();
		    }; --%>
		    

		},
		error : function(xhr, status, error, data){
			// 요청 오류시 실행 콜백 함수
			console.log("error call!");
			console.log(xhr, status, error);
			console.log(data);
		},
	})
})
</script>
</html>