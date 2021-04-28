<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- footer 리스트 -->
<div id="footer_section" class="footer">
  <div class="inner">
   <span class="f_logo">
     <img
       src="<%= request.getContextPath() %>/img/logo_footer.gif"
       alt="TheSimpsonMovie"/>
   </span>
   <div class="bx_address">
     <address>서울특별시 강남구 테헤란로 10길 9 그랑프리빌딩 5F </address>
     <span class="bar">고객센터 0101-7777</span><br /><span
       >대표이사 더심슨</span
     ><span class="bar">사업자등록번호 777-77-77777</span
     ><span class="bar">통신판매업신고번호 제7777호</span
     ><span class="bar">개인정보 보호 최고 책임자 박정현</span>
   </div>
   <p class="copyright">COPYRIGHT© THE SIMPSON MOVIE ALL RIGHT RESERVED.</p>
  </div>
  
 <style>
 	/* footer */
.footer {
  background-color: gray;
  box-shadow: 0px -5px 5px 5px gray;
  position: relative;
  text-align: center;
  color: white;
  padding-bottom: 1vh;
  z-index: 5;
}
 </style>
 
 <script>
 window.addEventListener('scroll', () => {
	  let scrollLocation = document.documentElement.scrollTop; // 현재 스크롤바 위치
	  let windowHeight = window.innerHeight; // 스크린 창
	  let fullHeight = document.body.scrollHeight; //  margin 값은 포함 x
	  
	  if (scrollLocation > 0.1 * windowHeight) {
	    $(".scroll_menu").css("display", "block");
	  } else {
	    $(".scroll_menu").css("display", "none");
	  }
	});
 </script>