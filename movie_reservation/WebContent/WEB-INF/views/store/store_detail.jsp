<%@page import="com.simp.product.model.vo.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/common/floatingbar.jsp"%>
<%
Product p= (Product)request.getAttribute("product");
%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/Content/css/giftstore.css" />
<style>
	.header_common {
		color : black;
		position : static;
	}
	
	.header_common a {
		color : black;
	}
	
	.bxslider {
		margin-left: 0;
		padding-inline-start: 0px;
	}
	
	 .bxslider li {
	 	list-style-type: none;
	 }
</style>

 <%
    int productNo = p.getProductNo(); //상품번호
	String pdCategory = p.getPdCategory(); //상품분류
	String productName = p.getProductName(); //상품이름
	int productPrice = p.getProductPrice(); //상품가격
	int stock = p.getStock(); //상품재고
	String pdStatus = p.getPdStatus(); //판매상태
	String pdImg = p.getPdImg(); //상품이미지
	String pdInfo = p.getPdInfo(); //상품정보

%>



    

        <!-- Contents Area -->
        <div class="gift_store">
          <!-- Contents Start -->

          <div id="divAction"></div>
          <!-- /Contaniner -->
          <!-- S 카테고리 메뉴 & 받은선물/장바구니 -->

          <script
            type="text/javascript"
            src="<%= request.getContextPath() %>/Content/js/giftstore.js"
          ></script>
          <script
            type="text/javascript"
            src="<%= request.getContextPath() %>/Content/js/commonstore.js"
          ></script>

          <link
            rel="stylesheet"
            media="all"
            type="text/css"
            href="<%= request.getContextPath() %>WebContent/Content/css/giftstore.css"
          />

         

         

          <!-- E 카테고리 메뉴 & 받은선물/장바구니 -->

          <!-- 상품정보 -->
  <div class="category_product_detail_wrap">
            <strong class="category_product_detail_title" 
              ><%= p.getProductName() %><span>기프티콘</span></strong>
            <div class="category_product_detail_contents">
              <div class="category_product_detail_contents_img_wrap">
                <ul class="bxslider">
                  <li>
                    <img
                      src="<%= request.getContextPath() %>/Content/images/store/<%= p.getPdImg() %>"/>
                  </li>
                </ul>
              </div>
              <div class="category_product_detail_contents_wrap">
                <p class="category_product_detail_sale_price_wrap">
                  <span class="store_deatail_sale_price" id="spnSalePrice"
                    ><%= p.getProductPrice() %></span
                  >
                </p>
                <dl class="category_product_detail_add_info">
  
                  <dt>상품정보</dt>
                  <dd><%=p.getPdInfo() %></dd>
                  <dt>유효기간</dt>
                  <dd>구매일로부터 24개월 이내</dd>
                  <dt>구매제한</dt>
                  <dd>10장 이하</dd>
                  <dt>상품재고</dt>
                  <dd><%=p.getStock() %></dd>
                </dl>
                
               
                <div class="category_product_detail_price_wrap">
                
                    
                  
                  
                  <div class="com_form_number"> 
                    <a
                      href="#none"
                      onclick="javascript:$.fn.comFormNumberCnt('com_form_count0', 'com_total_price' ,'com_total_price0', -1, 1, 10);"
                      class="com_btn_minus"
                      id="btn_cnt">-</a
                    >
                    <span class="com_form_count com_form_count0" id="pd_cnt">1</span>
                    <a
                      href="#none"
                      onclick="javascript:$.fn.comFormNumberCnt('com_form_count0','com_total_price', 'com_total_price0', 1, 1, 10);"
                      class="com_btn_plus"
                      id="btn_cnt">+</a>
                    
                  </div>
                  <div class="category_product_detail_total_price">
                    <p class="com_form_total_price"> 총 구매금액 <span
                        class="com_total_price0 com_product_total_price"><%=p.getProductPrice() %></span>
                    </p>
                  </div>
                </div>
                <div class="btn_box">
                  <button id="add_cart" class="add_cart_btn">담기</button>
                  <button class="add_cart_btn" onclick="location.href='<%= request.getContextPath()%>/product/shoppinglist';">구매하기</button>                
                </div>
              </div>
            </div>
            <dl class="category_product_detail_dlist">
              <dt>이용안내</dt>
              <dd>
                • 해당 기프트콘은 모바일, 홈페이지, 오프라인 극장에서 사용가능한 온라인 상품권입니다.<br />
             <br><br><br><br><br><br><br>
          </div>
        </div>
      </div>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
<form action="<%= request.getContextPath() %>/product/shoppinglist"
id="payment_store">
<input type="hidden" name="" />
<input type="hidden" name="" />
</form>        
</body>
<style>
.add_cart_btn{
display: block;
    float: left;
    width: 245px;
    height: 60px;
    margin-left: 10px;
    font-weight: 500;
    font-size: 18px;
    color: #fff;
    line-height: 60px;
    text-align: center;
    background-color: #373e46;
    display:inline-block;
}
.btn_box{
width: 605px;
}
</style>
<script>
$("#btn_cnt").click(function(){
	$(".com_product_total_price").text($(".com_form_count0").text() * <%= productPrice %>);
});
$("#add_cart").click(function(){
	console.log(1)
	$.ajax({
		url: "<%= request.getContextPath() %>/product/shoppinglist",
		method: "POST",
		data: {
			pd_title : "<%= productName %>",
			no : $("#pd_cnt").text()
		},
		success: function(data){
			swal("장바구니","장바구니에 물건을 담았습니다.","success");
		},
		error: function(xhr, status, err){
			console.log(xhr, status, err);
		}
	});
})
</script>
</html>