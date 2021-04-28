<%@page import="com.simp.product.model.service.ProductService"%>
<%@page import="com.simp.product.model.vo.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/common/floatingbar.jsp"%>
<%
	List<Product> list = (List<Product>) request.getAttribute("list");
%>
<style>
	.header_common {
		color : black;
		position : static;
	}
	
	.header_common a {
		color : black;
	}
	
	ul {
	 list-style:none;
	 padding: 0;
	}
	
	.banner {
		margin-top: 50px;
		margin-bottom : -50px;
		text-align: center;
	}
</style>
</head>
	<div class="gift_store">
	<div class="banner">
		<img src="<%= request.getContextPath() %>/Content/images/store/store.jpg" alt="기프트샵" />
	</div>
          
<link rel="stylesheet" media="all" type="text/css" href="https://img.cgv.co.kr/R2014/css/giftstore/giftstore.css" />
<!-- b포인트 -->
<div class="category_product_wrap">
	<ul class="category_product_list">
		<li>
			<strong class="category_product_title">포인트카드</strong>
			<ul class="category_product_inner_list">
<%
if(list != null) {
	int n = 0;
	for(Product p : list) {
		if(ProductService.PRODUCT_POINT.equals(p.getPdCategory())) {
			n ++;
%>
					<li class="">
                    	<a class="btn_category_product">
							<span class="best_product_img_wrap">
								<img src="<%= request.getContextPath() %>/Content/images/store/<%= p.getPdImg() %>"
	                                 alt="포인트"/>
							</span>
							<span class="best_product_text_wrap">
								<span class="best_product_text_title"><%= p.getProductName() %></span>
								<span class="store_deatail_source_price"><%=p.getProductPrice() %>원</span>
								<span class="best_product_text_name"><%=p.getPdInfo() %></span>
							</span>
						</a> 
						<a href="#none" class="btn_category_product_cart cart_btn">1</a>
						<a href="#none" class="btn_category_product_buy buy_btn">3</a>
						<br>
					</li>
<%
		}
	}

	
	for(int i = 0; i < 5 - n; i ++) {

%>
                 <li class="">
                    <div class="btn_category_product">
                      <span class="">
                      <img
                          src="<%= request.getContextPath() %>/Content/images/store/comingsoon.png"
                          alt="준비중" style="width: 170px;"/></span>
                      <span class="best_product_text_wrap">
                       			<span class="best_product_text_title">상품 준비 중 입니다</span>
                       <span class="best_product_text_name"></span>
                       </span>
                  	</div> 
                  </li>
<%
	
	}
}
%>
                </ul>
              </li>
              
              
              
             <!-- 스낵 -->
              <li>
                <strong class="category_product_title"
                  >스낵</strong>
                <ul class="category_product_inner_list">
<%
if(list != null) {
	int n = 0;
	for(Product p : list) {
		if(ProductService.PRODUCT_SNACK.equals(p.getPdCategory())) {
			n ++;
%>  
                  <li class="">
                    <a class="btn_category_product">
                      <span class="best_product_img_wrap">
                      <img
                          src="<%= request.getContextPath() %>/Content/images/store/<%= p.getPdImg() %>"
                          alt="<%= p.getPdImg() %>"/></span>
                       <span class="best_product_text_wrap">
                       		 <span class="best_product_text_title"><%= p.getProductName() %></span>
                          	 <span class="store_deatail_source_price"><%=p.getProductPrice() %>원</span>
                       		 <span class="best_product_text_name"><%=p.getPdInfo() %></span>
                        </span>
                         </a> 
                       <a href="#none" class="btn_category_product_cart cart_btn">1</a>
                    	<a href="#none" class="btn_category_product_buy buy_btn">3</a>
                      <br>
                  </li>             			
  <%
		}
	}

	for(int i = 0; i < 4 - n; i ++) {

%>
                    <li class="">
                    <div class="btn_category_product">
                      <span class="">
                      <img
                          src="<%= request.getContextPath() %>/Content/images/store/comingsoon.png"
                          alt="준비중" style="width: 170px;"/></span>
                      <span class="best_product_text_wrap">
                       			<span class="best_product_text_title">상품 준비 중 입니다</span>
                       <span class="best_product_text_name"></span>
                       </span>
                  	</div> 
                  </li>     
<%
	}
}
%> 
                </ul>
              </li>	
				  <!-- 음료 -->
              <li>
                <strong class="category_product_title">음료</strong>
                <ul class="category_product_inner_list">
<%
if(list != null) {
	int n = 0; 
	for(Product p : list) {
		if(ProductService.PRODUCT_DRINK.equals(p.getPdCategory())) {
			n ++;
%>
				<li class="">
					<a class="btn_category_product">
 						<span class="best_product_img_wrap">
							<img src="<%= request.getContextPath() %>/Content/images/store/<%= p.getPdImg() %>"alt="<%= p.getPdImg() %>"/>
						</span>
						 <span class="best_product_text_wrap">
                       		 <span class="best_product_text_title"><%= p.getProductName() %></span>
                          	 <span class="store_deatail_source_price"><%=p.getProductPrice() %>원</span>
                       		 <span class="best_product_text_name"><%=p.getPdInfo() %></span>
                        </span>
					</a> 
					<a href="#none" class="btn_category_product_cart cart_btn">1</a>
					<a href="#none" class="btn_category_product_buy buy_btn">3</a>
					<br>     
                  </li>
                  
 <%
		}
	}

	for(int i = 0; i < 4 - n; i ++) {

%>
                  <li class="">
                    <div class="btn_category_product">
                      <span class="">
                      <img
                          src="<%= request.getContextPath() %>/Content/images/store/comingsoon.png"
                          alt="준비중" style="width: 170px;"/></span>
                      <span class="best_product_text_wrap">
                       			<span class="best_product_text_title">상품 준비 중 입니다</span>
                       <span class="best_product_text_name"></span>
                       </span>
                  	</div> 
                  </li>       
<%
	}
}
%>          
                </ul>
              </li>
            </ul>
          </div>

        </div>
        <!-- /Contents Area -->
      <!-- /Contaniner -->
      <div class="com_pop_wrap">
        <div class="com_pop_fog"></div>
      </div>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>\
<form action="<%= request.getContextPath() %>/product/productDetail"
	  method-"GET"
	  id="pd_detail">
	<input type="hidden" name="pd_name" />
</form>
</body>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script>
$(".cart_btn").click(function(){
	console.log($(this).prev().find(".best_product_text_title").text());
	
	$.ajax({
		url: "<%= request.getContextPath() %>/product/shoppinglist",
		method: "POST",
		data: {
			pd_title : $(this).prev().find(".best_product_text_title").text(),
			no : 1
		},
		success: function(data){
			swal("장바구니","장바구니에 물건을 담았습니다.","success");
		},
		error: function(xhr, status, err){
			console.log(xhr, status, err);
		}
	});
})
$(".buy_btn").click(function(){
	$("[name=pd_name]").val($(this).prevAll(".btn_category_product").find(".best_product_text_title").text());
	$("#pd_detail").submit();
})
</script>
</html>