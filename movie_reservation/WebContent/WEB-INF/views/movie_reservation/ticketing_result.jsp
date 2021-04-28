<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/common/floatingbar.jsp"%>
<style>
   .header_common {
      color : black;
      position : static;
   }
   
   .header_common a {
      color : black;
   }
   
   .scroll_menu {
      margin-top = -20vh;
   }
</style>
<!DOCTYPE html>
<lang="ko">
  <head>
    <title>결제완료 - TheSimpsonMovie</title>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />

    <link rel="stylesheet" href="<%= request.getContextPath() %>/Content/css/default.css" />
    <link rel="stylesheet" href="<%= request.getContextPath() %>/Content/css/common.css" />
    <link rel="stylesheet" href="<%= request.getContextPath() %>/Content/css/content.css" />

    <script src="<%= request.getContextPath() %>/Content/js/jquery-1.12.3.min.js"></script>
    <script src="<%= request.getContextPath() %>/Content/js/jquery.jplayer.min.js"></script>
    <script src="<%= request.getContextPath() %>/Content/js/jquery.lazy.min.js"></script>
    <script src="<%= request.getContextPath() %>/Content/js/jquery-ui.min.js"></script>
    <script src="<%= request.getContextPath() %>/Content/js/jquery.cookie.js"></script>
    <script src="<%= request.getContextPath() %>/Content/js/jquery.mCustomScrollbar.concat.min.js"></script>
    <script src="<%= request.getContextPath() %>/Content/js/owl.carousel-2.3.4-custom.js"></script>
    <script src="<%= request.getContextPath() %>/Content/js/plugin.js"></script>
    <script src="<%= request.getContextPath() %>/Content/js/pl_custom.js"></script>
    <script src="<%= request.getContextPath() %>/Scripts/Library/moment.js"></script>
    <script
      type="text/javascript"
      src="https://members.lpoint.com/api/js/lotte.sso.api.js"
    ></script>

    <script src="<%= request.getContextPath() %>/Content/js/common.js"></script>
    <script src="<%= request.getContextPath() %>/Scripts/common/Cookie.js"></script>

    <script src="<%= request.getContextPath() %>/Scripts/Library/jquery.blockUI.min.js"></script>
    <script src="<%= request.getContextPath() %>/Scripts/Library/React/remarkable.min.js"></script>
    <script src="<%= request.getContextPath() %>/Scripts/Library/React/axios.min.js"></script>
    <script src="<%= request.getContextPath() %>/Scripts/common/URLSearchParamsCustom.js"></script>
    <script src="<%= request.getContextPath() %>/Scripts/common/Util.js"></script>
    <script src="<%= request.getContextPath() %>/Scripts/common/Common.js"></script>

    <script src="<%= request.getContextPath() %>/Scripts/common/ShareSNS.js"></script>
    <script src="<%= request.getContextPath() %>/Scripts/Member/Member.js"></script>
    <script type="<%= request.getContextPath() %>/text/javascript">
      var MypageCommonQuery = RequestParams(document.location.search); //페이지 파라메터
    </script>

    <!-- Google Tag Manager -->

    <script src="<%= request.getContextPath() %>/Scripts/common/lcga.js"></script>
    <!-- Google Tag Manager -->
  </head>

  <!--헤더 끝-->

  <div id="contents" class="contents_full contents_reserve" style="">
    <div class="wrap_reserve">
      <h2 class="hidden">결제완료</h2>
      <div class="section_step_tit">
        <ul>
          <li class="step01 prev">
            <a href="#"
              ><strong class="tit"><span>01</span><br />상영시간</strong>
              <div class="bx_con">
                <dl>
                  <dt>선택한 영화 제목</dt>
                  <dd>자산어보(디지털)</dd>
                  <dt>선택한 상영관</dt>
                  <dd>노원8관</dd>
                  <dt>선택한 상영 날짜</dt>
                  <dd>2021-04-11 (일 )</dd>
                  <dt>선택한 시간</dt>
                  <dd>15:30 ~ 17:46</dd>
                </dl>
              </div></a
            >
          </li>
          <li class="step02 prev">
            <a href="#"
              ><strong class="tit"><span>02</span><br />인원/좌석</strong>
              <div class="bx_con">
                <dl>
                  <dt>선택한 인원</dt>
                  <dd>성인1</dd>
                  <dt>선택한 좌석</dt>
                  <dd>K17</dd>
                </dl>
              </div></a
            >
          </li>
          <li class="step03 prev">
            <a href="#"
              ><strong class="tit"><span>03</span><br />결제</strong>
              <div class="bx_con">
                <dl>
                  <dt>티켓금액</dt>
                  <dd>13,000원</dd>
                  <dt>할인금액</dt>
                  <dd>0원</dd>
                  <dt>총합계</dt>
                  <dd>13,000원</dd>
                </dl>
              </div></a
            >
          </li>
          <li class="step04 active">
            <a href="#"
              ><strong class="tit"><span>04</span><br />결제완료</strong></a
            >
          </li>
        </ul>
      </div>
      <div id="reserveStep01" class="section_step_con step01">
        <h3 class="hidden">상영시간</h3>
      </div>
      <div id="reserveStep02" class="section_step_con step02">
        <h3 class="hidden">인원/좌석</h3>
      </div>
      <div id="reserveStep03" class="section_step_con step03">
        <h3 class="hidden">결제</h3>
      </div>
      <div id="reserveStep04" class="section_step_con step04 active">
        <h3 class="hidden">결제완료</h3>
        <div class="article article_payment_fin">
          <div class="group_top"><h4 class="tit">결제완료</h4></div>
          <div class="inner">
            <div class="payment_fin_wrap">
              <div class="top_notice_ic ty1">
                <strong
                  >전유진 회원님, 결제가 성공적으로 완료되었습니다.</strong
                >
              </div>
              <div class="reserve_result_wrap">
                <div class="infor_wrap">
                  <div class="bx_thm">
                    <img
                      src="https://caching.lottecinema.co.kr//Media/MovieFile/MovieImg/202102/16756_102_1.jpg"
                      alt="자산어보"
                    />
                  </div>
                  <div class="group_infor">
                    <dl>
                      <dt>예매번호</dt>
                      <dd><strong class="txt_num">2541811</strong></dd>
                    </dl>
                    <dl>
                      <dt>상영일시</dt>
                      <dd>2021-04-11 (일 )<span>15:30 ~ 17:46</span></dd>
                      <dt>상영관</dt>
                      <dd>노원8관</dd>
                      <dt>관람인원</dt>
                      <dd>성인1</dd>
                      <dt>좌석</dt>
                      <dd>K17</dd>
                    </dl>
                    
                  </div>
                </div>
                <div class="payment_wrap new2020">
                  <div class="group_price case1">
                    <dl>
                      <dt>주문금액</dt>
                      <dd><strong>13,000</strong>원</dd>
                    </dl>
                  </div>
                  <div class="group_price case2">
                    <dl class="minus">
                      <dt>할인금액</dt>
                      <dd><strong>0</strong>원</dd>
                    </dl>
                  </div>
                  <div class="group_price case3">
                    <dl>
                      <dt>총 결제 금액</dt>
                      <dd><strong>13,000</strong>원</dd>
                    </dl>
                    <div class="pay_method">
                      <dl class="sml">
                        <dt>결제방법</dt>
                        <dd><span class="txt_card1 ty1">무통장입금</span></dd>
                        <dt>카드번호</dt>
                        <dd class="sml last">국민은행 </dd>
                      </dl>
                    </div>
                  </div>
                </div>
              </div>
              <ul class="list_txt sml">
                <li>
                  <strong
                    >온라인 예매 및 추가상품 구매 취소는 상영 시작 20분 전까지
                    온라인에서 가능합니다.</strong
                  >
                </li>
                <li>
                  <strong
                    >상영시작 20분전 이후 부터는 영화관 현장에서만 취소
                    가능합니다.</strong
                  >
                </li>
                <li>
                  <strong
                    >반드시 전체 취소만 가능하며, 예매나 추가상품중 부분 취소는
                    불가능합니다.</strong
                  >
                </li>
                <li>
                  <strong
                    >추가상품 수령 완료 시 예매 및 상품 취소 모두
                    불가능합니다.</strong
                  >
                </li>
               
                <li>
                  <strong
                    >무대인사 예매 취소는 상영시작 24시간 전까지
                    가능합니다.</strong
                  >
                </li>
               
                <li>
                  예고편 상영 등 사정에 의해 본 영화 시작이 10여분 정도 차이 날
                  수 있습니다.
                </li>
              
                <li>
                  스토어에서 구매한 상품은 마이페이지 &gt; 예매/구매 내역에서
                  확인 및 사용할 수 있습니다.
                </li>
              </ul>
              <div class="btn_btm_wrap mb50 pb50">
                <a href="#none" class="btn_col3 ty5">결제내역</a
                ><a href="/store.html" class="btn_col2 ty5">스토어 바로가기</a
                ><a href="#none" class="btn_col1 ty5">홈으로 바로가기</a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>

