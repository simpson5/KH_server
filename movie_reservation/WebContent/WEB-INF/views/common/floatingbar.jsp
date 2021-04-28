<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/foaltingbar_link.jsp"%>
    
 <noscript>
      <iframe
        src="https://www.googletagmanager.com/ns.html?id=GTM-K8MP435"
        height="0"
        width="0"
        style="display: none; visibility: hidden"
      ></iframe>
    </noscript>

    <div class="quick_wrap" style="padding-top: 50px; right: 0px">
      <div id="quick_menu" class="quick_menu" style="top: 0px;">
        	<a href="<%= request.getContextPath() %>/member/mypageMyMovie">
            <img src="<%= request.getContextPath() %>/Content/images/icon/zzim.png" alt="찜한영화"/>
          </a>
          <a href="<%= request.getContextPath() %>/product/productList">
            <img src="<%= request.getContextPath() %>/Content/images/icon/store.png" alt="스토어"/>
          </a>
          <a href="#none">
          	<img src="<%= request.getContextPath() %>/Content/images/icon/qna.png" alt="1:1문의"/>
          </a>
          
          <a href="<%= request.getContextPath() %>/product/shoppinglist">
          	<img src="<%= request.getContextPath() %>/Content/images/icon/basket.png" alt="장바구니"/>
          </a>
        
        <a href="#" class="qick_top">TOP</a>
      </div>
    </div>

    <body>