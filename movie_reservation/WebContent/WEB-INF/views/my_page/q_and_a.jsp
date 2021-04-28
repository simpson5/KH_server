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
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>QandA</title>
    
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/souce.css" />
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/content.css" /> <!--전체적인 디자인-->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/defult.css" />
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/dev.css" />
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
    <script src="<%= request.getContextPath() %>/js/contentscript.js"></script>
    <script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
    <script src="<%= request.getContextPath() %>/js/commom.js"></script>
</head>
<body>
    <div id="contents" class="contents_customer" style="margin-top: 120px;">
        <div class="title_top">
            <h2 class="tit">&emsp;고객센터</h2>
        </div>
        <ul class="tab_wrap outer">
            <li>
                <a href="notice.html" class="tab_tit" style="width: 30%; left: 20%; text-align: center;" >
                <span>공지사항</span>
            </a>
        </li>
        <li class="tab_tit" style="width: 20%; left: 40%;">
        </li>
        <li class="active">
            <button type="button" class="tab_tit" style="width: 30%; left: 40%;">
                <span>1:1문의</span>
            </button>
            <div class="tab_con">
                <h3 class="hidden">1:1문의</h3>
                <div class="con_top">
                    <div class="ico_tit qus">FAQ를 이용하시면 궁금증을 더 빠르게 해결하실 수 있습니다.
                        <ul class="list_txt mt10">
                            <li>1:1 문의글 답변 운영시간 10:00 ~ 19:00</li>
                            <li>접수 후 48시간 안에 답변 드리겠습니다.</li>
                        </ul>
                    </div>
                    <div class="btn_wrap">
                        <a href="#none" class="btn_col4 ty5"> MY 문의내역</a>
                    </div>
                </div>
                <dl class="contxt_type1">
                    <dt class="tit">고객님의 문의에 
                        <span class="txt_color02">답변하는 직원은 고객 여러분의 가족 중 한 사람</span>
                        일 수 있습니다.
                    </dt>
                    <dd class="desc">
                        고객의 언어폭력(비하, 욕설, 반말, 성희롱 등)으로부터
                        직원을 보호하기 위해 관련 법에 따라 수사기관에 필요한 조치를 요구할 수 있으며, 
                        형법에 의해 처벌 대상이 될 수 있습니다.</dd>
                    </dl>
                    <div class="con_tit ty2">
                        <h4 class="tit">문의내용</h4>
                        <div class="group_rgt">
                            <span class="txt_req">필수입력</span>
                        </div>
                    </div>
                    <table class="tbl_form" summary="문의내용작성 테이블">
                        <caption>문의 내용을 작성해주세요</caption>
                        <colgroup>
                            <col style="width: 15%;">
                            <col style="width: auto;">
                        </colgroup>
                        <tbody>
                            <tr>
                                <th scope="row" class="req">분류</th>
                                <td>
                                    <select title="문의내용 분류선택" id="iDdivisionCode">
                                        <option value="0" selected="">분류 선택</option>
                                        <option value="600">영화관</option>
                                        <option value="700">영화</option>
                                        <option value="800">포인트</option>
                                        <option value="900">예매/결제</option>
                                        <option value="1100">홈페이지/모바일</option>
                                        <option value="1200">개인정보</option></select>
                                        <select title="문의내용 문의종류" id="inquiryType">
                                            <option value="0"> 문의 종류 </option>
                                            <option value="3">문의</option>
                                            <option value="1">칭찬</option>
                                            <option value="2">불만</option>
                                            <option value="4">건의</option>
                                        </select>
                                    </td>
                                </tr>
                                    <th scope="row" class="req">제목</th>
                                    <td>
                                        <div class="bx_textarea"><input type="text" class="ty2 w_full" placeholder="제목을 입력해주세요" title="문의내용 제목입력" id="iDtitle">
                                            <span class="txt_count">
                                                <em id="strongContentsCount_Title">0</em>/한글<em> 50</em>자
                                            </span>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row" class="req">내용</th>
                                    <td>
                                        <div class="bx_textarea">
                                            <textarea class="ty2" cols="10" rows="10" id="iDcontents" title="문의내용을 입력해주세요" placeholder="내용을 입력해주세요"></textarea>
                        
                                                <span class="txt_count">
                                                    <em id="strongContentsCount">0</em>/한글<em> 2,000</em>자</span>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th scope="row">첨부파일</th>
                                            <td>
                                                <div class="bx_file ">
                                                    <input type="file" id="file" name="file" accept="">
                                                    <label class="" for="file">파일선택</label>
                                                    <div class="file_item">
                                                
                                                </div>
                                                <span class="txt_caution1 fl_r with_inp">
                                                    첨부 파일형식 : jpg / jpeg / png / bmp / gif / pdf (5MB X 1개)</span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th scope="row">답변수신 여부</th>
                                            <td>
                                                <input type="checkbox" id="iDemailReplyYN">
                                                <label for="iDemailReplyYN">이메일 알림받기</label>
                                                <input type="checkbox" id="iDsmsReplyYN">
                                                <label for="iDsmsReplyYN">SMS 알림받기</label>
                                                <span class="txt_caution1 fl_r ">비회원 문의 시 이메일로 답변 내용이 발송되므로 이메일로 답변 알림 받기는 필수입니다. </span>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                                <div class="con_tit ty2">
                                    <h4 class="tit">고객정보</h4>
                                    <div class="group_rgt"><span class="txt_req">필수입력</span>
                                    </div>
                                </div>
                                <table class="tbl_form" summary="고객정보작성 테이블">
                                    <caption>고객정보를 작성해주세요</caption>
                                    <colgroup>
                                        <col style="width: 15%;">
                                        <col style="width: auto;">
                                    </colgroup>
                                    <tbody>
                                        <tr>
                                            <th scope="row" class="req">성명</th>
                                            <td>
                                                <input type="text" class="ty2 inp_name" id="iDregistrationName" title="성명을 입력해주세요" value=""></td>
                                            </tr>
                                            <tr><th scope="row" class="req">연락처</th>
                                                <td>
                                                    <select class="ty2" title="연락처" id="iDtelephoneNo01">
                                                        <option value="">선택</option>
                                                        <option value="010" selected="">010</option>
                                                        <option value="011">011</option>
                                                        <option value="016">016</option>
                                                        <option value="017">017</option>
                                                        <option value="018">018</option>
                                                        <option value="019">019</option>
                                                    </select>
                                                    <input type="text" class="ty2 inp_phon" id="iDtelephoneNo02" title="연락처 가운데 숫자" maxlength="4" value="">
                                                    <input type="text" class="ty2 inp_phon" id="iDtelephoneNo03" title="연락처 끝 숫자" maxlength="4" value="">
                                                </td>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="req">이메일</th>
                                                <td>
                                                    <input type="text" class="ty2 inp_id" title="이메일 아이디" id="iDemail1" value=""> @
                                                    <input type="text" class="ty2 inp_emai" title="이메일 주소" id="iDemail2" value="">
                                                    <span class="txt_caution1 fl_r with_inp">답변 등록시 해당 이메일로 자동 발송 됩니다.</span>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                    <div class="con_tit ty2">
                                        <h4 class="tit">개인정보 수집에 대한 동의</h4>
                                        <div class="group_rgt">
                                            <p class="txt_form">
                                            문의를 통해 아래의 개인정보를 수집합니다.
                                            수집된 개인정보는 문의 외 목적으로 사용하지 않습니다.
                                            </p>
                                        </div>
                                    </div>
                                    <div class="privacy_wrap">
                                        <div class="txtarea">
                                            <a class="focus_textarea" href="javascript:void(0);">
                                                개인정보의 수집목적 및 항목</a>
                                                <br>① 수집 목적 : 원활한 고객 상담, 불편사항 및 문의사항 관련 의사소통 경로 확보
                                                <br>② 수집 항목
                                                <br>*필수입력사항
                                                <br>- 이용자 식별을 위한 항목 : 성명, 연락처, 이메일, 아이디(로그인 시 수집)
                                                <br>
                                                <span class="color_red">
                                                    <br>
                                                    개인정보의 보유 및 이용기간
                                                    <br>
                                                    입력하신 개인정보는 문의 접수 후 처리 완료 시점으로 부터 3년간 보유 합니다.
                                                    (단, 생년월일은 이용자 식별 목적으로 이용되며 별도 보관되지 않습니다.)
                                                    <br>다만, 소비자보호에 관한 법률 등 관계 법률에 의해 보유할 필요가 있는 경우에는 
                                                    다음과 같이 보유합니다.
                                                    <br>보유기간 : 소비자의 불만 또는 분쟁처리에 관한 기록 3년</span>
                                                    <br>
                                                    <br>
                                                    ※ 1:1문의 서비스 제공을 위한 최소한의 개인정보이며 거부할 수 있습니다.
                                                    다만, 수집에 동의하지 않을 경우 서비스 이용이 제한됩니다.
                                                </div>
                                                <div class="bx_inp">
                                                    <input type="radio" name="rdo01" id="radio10" class="iDrdoAgreeY">
                                                    <label for="radio10">동의 </label>
                                                    <input type="radio" name="rdo01" id="radio11" checked="">
                                                    <label for="radio11">동의하지않음</label>
                                                </div>
                                                <div class="btn_btm_wrap">
                                                    <a href="" class="btn_col3 ty6">취소</a>
                                                    <button type="submit" class="btn_col2 ty6">
                                                        확인
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                            
  <%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>