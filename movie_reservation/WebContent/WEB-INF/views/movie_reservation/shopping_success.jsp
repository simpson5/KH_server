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
</style>

<html lang="ko">
  <head>
    <title>결제완료 - 롯데시네마</title>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />

    <script
      async=""
      type="text/javascript"
      src="https://tag-deepad.lpoint.com/cookie?mid=4&amp;cid=973545548.1617673780"
    ></script>
    <script
      type="text/javascript"
      async=""
      src="https://www.google-analytics.com/analytics.js"
    ></script>
    <script
      async=""
      src="//dsp.dmcmedia.co.kr/selper/js/selperevt.js?ver=0.1.4.1"
    ></script>
    
    <script
      async=""
      src="https://www.googletagmanager.com/gtag/js?id=UA-164790289-1"
    ></script>
    <script
      type="text/javascript"
      async=""
      src="https://www.google-analytics.com/analytics.js"
    ></script>
    <script
      type="text/javascript"
      async=""
      src="https://www.google-analytics.com/plugins/ua/ec.js"
    ></script>
    <script
      async=""
      src="https://www.googletagmanager.com/gtm.js?id=GTM-PJ6LCKF"
    ></script>
    <script
      async=""
      src="https://www.googletagmanager.com/gtm.js?id=GTM-K8MP435"
    ></script>
    <script
      async=""
      src="https://www.google-analytics.com/analytics.js"
    ></script>

    <link rel="stylesheet" href="<%= request.getContextPath() %>/Content/css/default.css?v=202104200126" />
    <link rel="stylesheet" href="<%= request.getContextPath() %>/Content/css/owl.css?v=202104200126" />
    <link rel="stylesheet" href="<%= request.getContextPath() %>/Content/css/swiper.css?v=202104200126" />
    <link
      rel="stylesheet"
      href="<%= request.getContextPath() %>/Content/css/jquery.mCustomScrollbar.css?v=202104200126"
    />
    <link rel="stylesheet" href="<%= request.getContextPath() %>/Content/css/common.css?v=202104200126" />
    <link rel="stylesheet" href="<%= request.getContextPath() %>/Content/css/content.css?v=202104200126" />
    <link rel="stylesheet" href="<%= request.getContextPath() %>/Content/css/dev.css?v=202104200126" />

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
    
    <script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>

    <script src="<%= request.getContextPath() %>/Content/js/common.js?v=202104200126"></script>
    <script src="<%= request.getContextPath() %>/Scripts/common/Cookie.js?v=202104200126"></script>

    <script src="<%= request.getContextPath() %>/Scripts/Library/jquery.blockUI.min.js"></script>
    <script src="<%= request.getContextPath() %>/Scripts/Library/React/remarkable.min.js"></script>
    <script src="<%= request.getContextPath() %>/Scripts/Library/React/axios.min.js"></script>
    <script src="<%= request.getContextPath() %>/Scripts/common/URLSearchParamsCustom.js"></script>
    <script src="<%= request.getContextPath() %>/Scripts/common/Util.js?v=202104200126"></script>
    <script src="<%= request.getContextPath() %>/Scripts/common/Common.js?v=202104200126"></script>

    <script src="<%= request.getContextPath() %>/Scripts/common/ShareSNS.js?v=202104200126"></script>
    <script src="<%= request.getContextPath() %>/Scripts/Member/Member.js"></script>
    <script type="text/javascript">
      var MypageCommonQuery = RequestParams(document.location.search); //페이지 파라메터
    </script>

    <!-- Google Tag Manager -->

    <script src="<%= request.getContextPath() %>/Scripts/common/lcga.js?v=202104200126"></script>
    <!-- Google Tag Manager -->
  </head>
  <body style="">
    <noscript>
      <iframe
        src="https://www.googletagmanager.com/ns.html?id=GTM-K8MP435"
        height="0"
        width="0"
        style="display: none; visibility: hidden"
      ></iframe>
    </noscript>

    <script src="<%= request.getContextPath() %>/Scripts/common/aes.js"></script>
    <script src="<%= request.getContextPath() %>/Scripts/common/sso.js?v=202104200126"></script>
    <script src="<%= request.getContextPath() %>/Scripts/common/LcCommon.js?v=202104200126"></script>
    <script src="<%= request.getContextPath() %>/Scripts/common/crossDomainStorage.js?v=202104200126"></script>
<br />
<br />
<br />
    <div id="sub_section">
      <div id="contents" class="contents_full contents_reserve" style="">
        <div class="wrap_reserve mall">
          <h2 class="hidden">결제완료</h2>
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
                        <img src="<%= request.getContextPath() %>/Content/images/store/coke.jpg" />
                      </div>
                      <div class="group_infor">
                        <dl>
                          <dt>구매번호</dt>
                          <dd>
                            <strong class="txt_num">109821042090000009</strong>
                          </dd>
                        </dl>
                        <dl>
                          <dt>상품명</dt>
                          <dd>콜라 L</dd>
                          <dt>수량</dt>
                          <dd>1개</dd>
                          <dt>교환번호</dt>
                          <dd>1621000606710</dd>
                        </dl>
                      </div>
                    </div>
                    <div class="payment_wrap new2020">
                      <div class="group_price case1">
                        <dl>
                          <dt>주문금액</dt>
                          <dd><strong>3,000</strong>원</dd>
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
                          <dd><strong>3,000</strong>원</dd>
                        </dl>
                        <div class="pay_method">
                          <dl class="sml">
                            <dt>결제방법</dt>
                            <dd><span class="txt_card1 ty1">신용카드</span></dd>
                            <dt>카드번호</dt>
                            <dd class="sml last">신한 449914******0061</dd>
                          </dl>
                        </div>
                      </div>
                    </div>
                  </div>
                  <ul class="list_txt sml">
                    <li>
                      <strong
                        >온라인 예매 및 추가상품 구매 취소는 상영 시작 20분
                        전까지 온라인에서 가능합니다.</strong
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
                        >반드시 전체 취소만 가능하며, 예매나 추가상품중 부분
                        취소는 불가능합니다.</strong
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
                        >포토티켓 발권 완료 시 환불은 발권된 포토티켓 모두 지참
                        후 해당 영화관에서만 가능합니다.</strong
                      >
                    </li>
                    <li>
                      <strong
                        >무대인사 예매 취소는 상영시작 24시간 전까지
                        가능합니다.</strong
                      >
                    </li>
                    <li>적립 예정 L.POINT는 영화 관람 다음 날 적립 됩니다.</li>
                    <li>
                      예고편 상영 등 사정에 의해 본 영화 시작이 10여분 정도 차이
                      날 수 있습니다.
                    </li>
                    <li>
                      개인정보 보호 정책에 따라 주민번호로 예매 티켓을 찾을 수
                      없습니다. 꼭 예매번호를 확인해 주세요.
                    </li>
                    <li>
                      스토어에서 구매한 상품은 마이페이지 &gt; 예매/구매
                      내역에서 확인 및 사용할 수 있습니다.
                    </li>
                  </ul>
                  <div class="btn_btm_wrap mb50 pb50">
                    <a href="#none" class="btn_col3 ty5">결제내역</a
                    ><a href="<%= request.getContextPath() %>/product/productList" class="btn_col2 ty5">스토어 바로가기</a
                    ><a href="#none" class="btn_col1 ty5">홈으로 바로가기</a>
                  </div>
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
