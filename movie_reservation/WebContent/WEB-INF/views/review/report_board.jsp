<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/report_board.css" />

<style>
   .header_common {
      color : black;
      position : static;
   }
   
   .header_common a {
      color : black;
   }
   
   .scroll_menu {
      margin-top : -145px;
   }
</style>

<div class="total_content">
            <h1>신고 목록 게시판</h1>
            <div class="main_container">
                <div class="search_container">
                    <span class="report_search">
                        <select name="report_search" class="report_search_select">
                            <option value="분류">분류</option>
                            <option value="신고자">신고자</option>
                            <option value="피신고자">피신고자</option>
                        </select>
                        <input type="search" name="report_search" class="report_search_text" placeholder="검색어를 입력하세요.">
                        <input type="button" value="검색" class="report_search_sumit">
                    </span>
                    <span class="total_report_cnt_box">
                        총
                        <span class="total_report_cnt"> 15 </span>
                        개의 신고가 있습니다.
                    </span>
                </div>
                <div class="search_result">
                    <table class="report_tb">
                        <thead>
                            <tr>
                            <th scope="col">제목</th>
                            <th scope="col">분류</th>
                            <th scope="col">신고자</th>
                            <th scope="col">피신고자</th>
                            <th scope="col">날짜</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>             
                                <td class="report_header" ><a href="report_result.html">신고글 제목</a></td>
                                <td class="report_kind">신고 분류</td>
                                <td class="report_client">sinsa</td>
                                <td class="report_target">honggd</td>     
                                <td class="report_date">2021.04.15</td>     
                            </tr>
                            <tr>             
                                <td class="report_header" ><a href="report_result.html">신고글 제목</a></td>
                                <td class="report_kind">신고 분류</td>
                                <td class="report_client">sinsa</td>
                                <td class="report_target">honggd</td>     
                                <td class="report_date">2021.04.15</td>     
                            </tr>
                            <tr>             
                                <td class="report_header" ><a href="report_result.html">신고글 제목</a></td>
                                <td class="report_kind">신고 분류</td>
                                <td class="report_client">sinsa</td>
                                <td class="report_target">honggd</td>     
                                <td class="report_date">2021.04.15</td>     
                            </tr>
                            <tr>             
                                <td class="report_header" ><a href="report_result.html">신고글 제목</a></td>
                                <td class="report_kind">신고 분류</td>
                                <td class="report_client">sinsa</td>
                                <td class="report_target">honggd</td>     
                                <td class="report_date">2021.04.15</td>     
                            </tr>
                            <tr>             
                                <td class="report_header" ><a href="report_result.html">신고글 제목</a></td>
                                <td class="report_kind">신고 분류</td>
                                <td class="report_client">sinsa</td>
                                <td class="report_target">honggd</td>     
                                <td class="report_date">2021.04.15</td>     
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="page_bar">< 1 2 3 4 5 ></1></div>
        </div>
</body>