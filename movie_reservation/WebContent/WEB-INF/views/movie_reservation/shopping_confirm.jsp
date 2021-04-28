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
    <title>결제</title>
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

    <link rel="stylesheet" href="<%= request.getContextPath() %>/Content/css/default.css?v=202104201248" />
    <link rel="stylesheet" href="<%= request.getContextPath() %>/Content/css/owl.css?v=202104201248" />
    <link rel="stylesheet" href="<%= request.getContextPath() %>/Content/css/swiper.css?v=202104201248" />
    <link
      rel="stylesheet"
      href="/Content/css/jquery.mCustomScrollbar.css?v=202104201248"
    />
    <link rel="stylesheet" href="<%= request.getContextPath() %>/Content/css/common.css?v=202104201248" />
    <link rel="stylesheet" href="<%= request.getContextPath() %>/Content/css/content.css?v=202104201248" />
    <link rel="stylesheet" href="<%= request.getContextPath() %>/Content/css/dev.css?v=202104201248" />

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

    <script src="<%= request.getContextPath() %>/Content/js/common.js?v=202104201248"></script>
    <script src="<%= request.getContextPath() %>/Scripts/common/Cookie.js?v=202104201248"></script>

    <script src="<%= request.getContextPath() %>/Scripts/Library/jquery.blockUI.min.js"></script>
    <script src="<%= request.getContextPath() %>/Scripts/Library/React/remarkable.min.js"></script>
    <script src="<%= request.getContextPath() %>/Scripts/Library/React/axios.min.js"></script>
    <script src="<%= request.getContextPath() %>/Scripts/common/URLSearchParamsCustom.js"></script>
    <script src="<%= request.getContextPath() %>/Scripts/common/Util.js?v=202104201248"></script>
    <script src="<%= request.getContextPath() %>/Scripts/common/Common.js?v=202104201248"></script>

    <script src="<%= request.getContextPath() %>/Scripts/common/ShareSNS.js?v=202104201248"></script>
    <script src="<%= request.getContextPath() %>/Scripts/Member/Member.js"></script>
    <script type="text/javascript">
      var MypageCommonQuery = RequestParams(document.location.search); //페이지 파라메터
    </script>

    <!-- Google Tag Manager -->

    <script src="<%= request.getContextPath() %>/Scripts/common/lcga.js?v=202104201248"></script>
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
    <script src="<%= request.getContextPath() %>/Scripts/common/sso.js?v=202104201248"></script>
    <script src="<%= request.getContextPath() %>/Scripts/common/LcCommon.js?v=202104201248"></script>
    <script src="<%= request.getContextPath() %>/Scripts/common/crossDomainStorage.js?v=202104201248"></script>

    <style>
      .smallScreen .floor_bx .seat_area .sel.myseat span {
        background: #ff243e !important;
      }

      /* Chrome, Safari, Edge, Opera */
      input::-webkit-outer-spin-button,
      input::-webkit-inner-spin-button {
        -webkit-appearance: none;
        margin: 0;
      }

      /* Firefox */
      input[type="number"] {
        -moz-appearance: textfield;
      }
    </style>
    <!-- 20161006 Facebook Pixel 적용 -->

    <div id="divMinimap" class="screen_preview_con" style="display: none">
      <div class="screen_info cineCont">
        <div class="smallScreen">
          <span class="title_screen1">SCREEN</span>
          <div class="minimap"></div>
        </div>
      </div>
    </div>

    <div id="sub_section">
      <div id="contents" class="contents_full contents_reserve">
        <div class="wrap_reserve mall">
          <h2 class="hidden">예매하기</h2>
          <div id="reserveStep03" class="section_step_con step03 active">
            <h3 class="hidden">결제</h3>
            <div class="article article_sum_infor">
              <div class="group_top"><h4 class="tit">상품정보</h4></div>
              <div class="inner">
                <div class="movie_infor new2020">
                  <span class="thm"
                    ><img
                      src="<%= request.getContextPath() %>/Content/images/store/coke.jpg"
                      alt="콜라 L" /></span
                  ><strong class="tit">콜라 L</strong>
                  <dl class="dlist_infor">
                    <dt class="hidden">내용</dt>
                    <dd>스위트샵 상품권 1개</dd>
                    <dt>수량</dt>
                    <dd>1개</dd>
                  </dl>
                </div>
              </div>
            </div>
            <div class="article article_pay_method">
              <div class="group_top">
                <h4 class="tit">결제수단</h4>
                <button type="button" class="btn_txt_reset">초기화</button>
              </div>
              <div class="inner">
                <div
                  class="mCustomScrollbar _mCS_1 mCS-autoHide mCS_no_scrollbar"
                  data-mcs-theme="minimal-dark"
                  style="position: relative; overflow: visible"
                >
                  <div
                    id="mCSB_1"
                    class="mCustomScrollBox mCS-minimal-dark mCSB_vertical mCSB_outside"
                    style="max-height: none"
                    tabindex="0"
                  >
                    <div
                      id="mCSB_1_container"
                      class="mCSB_container mCS_y_hidden mCS_no_scrollbar_y"
                      style="position: relative; top: 0; left: 0"
                      dir="ltr"
                    >
                      <div class="group_discount" style="display: none">
                        <h3 class="tit_payment">할인/포인트</h3>
                        <div class="wrap_coupon">
                          <div class="bx_cate">
                            <ul class="list_pay_item cate4"></ul>
                          </div>
                        </div>
                        <div
                          class="toggle_wrap no couplechk"
                          style="display: none"
                        ></div>
                      </div>
                      <div class="group_payment">
                        <h5 class="tit_payment">최종 결제수단</h5>
                        <div class="bx_cate">
                          <ul class="list_pay_item cate4">
                            <li>
                              <button type="button" class="cate1">
                                무통장입금
                              </button>
                            </li>

                            <li>
                              <button type="button" class="cate3">
                                카카오페이
                              </button>
                            </li>
                          </ul>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div
                    id="mCSB_1_scrollbar_vertical"
                    class="mCSB_scrollTools mCSB_1_scrollbar mCS-minimal-dark mCSB_scrollTools_vertical"
                    style="display: none"
                  >
                    <div class="mCSB_draggerContainer">
                      <div
                        id="mCSB_1_dragger_vertical"
                        class="mCSB_dragger"
                        style="
                          position: absolute;
                          min-height: 50px;
                          height: 0px;
                          top: 0px;
                        "
                      >
                        <div
                          class="mCSB_dragger_bar"
                          style="line-height: 50px"
                        ></div>
                      </div>
                      <div class="mCSB_draggerRail"></div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="article article_payment">
              <div class="group_top"><h4 class="tit">결제하기</h4></div>
              <div class="inner">
                <div class="select_item_wrap"></div>
                <div class="payment_sum_wrap">
                  <dl>
                    <dt>상품금액</dt>
                    <dd><strong>3,000</strong>원</dd>
                  </dl>
                  <dl>
                    <dt>할인금액</dt>
                    <dd>-<strong>0</strong>원</dd>
                  </dl>
                  <dl>
                    <dt>결제금액</dt>
                    <dd>총<strong>3,000</strong>원</dd>
                  </dl>
                  <a href="<%= request.getContextPath() %>/WEB-INF/views/movie_reservation/shopping_confirm.jsp" class="btn_col1 btn_confirm">결제하기</a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
 

<!-- /Contaniner -->
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>