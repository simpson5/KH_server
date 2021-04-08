/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.47
 * Generated at: 2020-01-06 08:42:15 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import member.model.vo.Member;

public final class memberList_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/WEB-INF/views/common/header.jsp", Long.valueOf(1578022508000L));
    _jspx_dependants.put("/WEB-INF/views/common/footer.jsp", Long.valueOf(1577923558000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("java.util");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("member.model.vo.Member");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write('\n');
      out.write('\n');

	//로그인한 경우
	Member memberLoggedIn = (Member)session.getAttribute("memberLoggedIn");
	//System.out.println("memberLoggedIn@header.jsp="+memberLoggedIn);

	//쿠키관련
	boolean saveId = false;
	String loginId = "";
	Cookie[] cookies = request.getCookies();
	if(cookies != null) {
		for(Cookie c : cookies) {
			String k = c.getName();
			String v = c.getValue();
			
			if("saveId".equals(k)) {
				saveId = true;
				loginId = v;
			}
		}
	}

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("<head>\n");
      out.write("<meta charset=\"UTF-8\">\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("<meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n");
      out.write("<title>PIXARBOX</title>\n");
      out.write("\n");
      out.write("<!-- css -->\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath() );
      out.write("/css/style.css\" />\n");
      out.write("<!--웹폰트추가  -->\n");
      out.write("<link href=\"https://fonts.googleapis.com/css?family=Anton|Do+Hyeon|Nanum+Gothic+Coding&display=swap&subset=latin-ext,vietnamese\" rel=\"stylesheet\">\n");
      out.write("\n");
      out.write("<!-- icon -->\n");
      out.write("<link rel=\"stylesheet\" href=\"http://cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css\">\n");
      out.write("\n");
      out.write("<!-- jquery -->\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/js/jquery-3.4.1.js\"></script>\n");
      out.write("\n");
      out.write("<!-- script slick, bxslider -->\n");
      out.write("<!-- <script type=\"text/javascript\" src=\"http://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"http://kenwheeler.github.io/slick/slick/slick.min.js\"></script>\n");
      out.write("<script src=\"https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js\"></script> -->\n");
      out.write("\n");
      out.write("<!--슬라이드코드추가--> \n");
      out.write("<link href=\"//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css\" rel=\"stylesheet\" id=\"bootstrap-css\">\n");
      out.write("<script src=\"//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js\"></script>\n");
      out.write("<script src=\"//code.jquery.com/jquery-1.11.1.min.js\"></script>\n");
      out.write("\n");
      out.write("<!-- 파비콘추가-->\n");
      out.write("<link rel=\"shortcut icon\" href=\"");
      out.print(request.getContextPath() );
      out.write("/images/favicon.ico\" type=\"image/x-icon\" />\n");
      out.write("<link rel=\"icon\" href=\"");
      out.print(request.getContextPath() );
      out.write("/images/favicon.ico\" type=\"image/x-icon\" />\n");
      out.write("\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<!-- 헤더  -->\n");
      out.write("<header id=\"header\" class=\"header_main\">\n");
      out.write("\n");
      out.write("<!-- 두번째 헤더 -->\n");
      out.write("    <div class=\"sec_haed\">\n");
      out.write("\t    <!-- 고정헤더 -->\n");
      out.write("\t    <nav id=\"topUtil\" class=\"top_util\">\n");
      out.write("\t        <span>PIXAR</span>\n");
      out.write("\t        <ul class=\"nav\">\n");
      out.write("\t        ");
      out.write("\n");
      out.write("\t        ");
 if(memberLoggedIn == null) { 
      out.write("\n");
      out.write("\t            <li>\n");
      out.write("\t                <a href=\"");
      out.print(request.getContextPath() );
      out.write("/member/memberLogin\" id=\"login_btn\">로그인</a>        \n");
      out.write("\t            </li>\n");
      out.write("\t            <li>\n");
      out.write("\t                <a href=\"");
      out.print(request.getContextPath() );
      out.write("/member/memberEnroll\" id=\"sign_btn\">회원가입</a>   \n");
      out.write("\t\n");
      out.write("\t            </li>\n");
      out.write("\t            <li>\n");
      out.write("\t                <a href=\"");
      out.print(request.getContextPath() );
      out.write("/customer/notice/notice.do\" id=\"customer_btn\">고객센터</a>\n");
      out.write("\t            </li>\n");
      out.write("\t        ");
      out.write("\n");
      out.write("\t        ");
 }
	        else if(memberLoggedIn!=null && "admin".equals(memberLoggedIn.getMemberId())) { 
	        
      out.write("\n");
      out.write("\t            <li>\n");
      out.write("\t                <a href=\"");
      out.print(request.getContextPath() );
      out.write("\" id=\"home\">홈</a>   \n");
      out.write("\t\n");
      out.write("\t            </li>\n");
      out.write("\t        \t<li><a href=\"");
      out.print(request.getContextPath() );
      out.write("/member/memberLogout\" id=\"sign_out_btn\">로그아웃</a></li>\n");
      out.write("\t\t\t\t<li><a href=\"");
      out.print(request.getContextPath());
      out.write("/admin/memberList\" id=\"admin_btn\">관리자페이지</a></li>\n");
      out.write("\t\t\t\t<li><a href=\"");
      out.print(request.getContextPath() );
      out.write("/customer/notice/notice.do\" id=\"customer_btn\">고객센터</a></li>\n");
      out.write("\t        ");
 }
	        else { 
      out.write("\n");
      out.write("\t            <li>\n");
      out.write("\t                <a href=\"");
      out.print(request.getContextPath() );
      out.write("\" id=\"home\">홈</a>   \n");
      out.write("\t\n");
      out.write("\t            </li>\n");
      out.write("\t            <li>\n");
      out.write("\t                <a href=\"");
      out.print(request.getContextPath() );
      out.write("/member/memberLogout\" id=\"sign_out_btn\">로그아웃</a>   \n");
      out.write("\t\n");
      out.write("\t            </li>\n");
      out.write("\t            <li>\n");
      out.write("\t                <a href=\"");
      out.print(request.getContextPath() );
      out.write("/member/my/mypage.do?memberId=");
      out.print(memberLoggedIn.getMemberId() );
      out.write("\" id=\"mypage_btn\">마이페이지</a>   \n");
      out.write("\t            </li>\n");
      out.write("\t            <li>\n");
      out.write("\t                <a href=\"");
      out.print(request.getContextPath() );
      out.write("/customer/notice/notice.do\" id=\"customer_btn\">고객센터</a>\n");
      out.write("\t            </li>\n");
      out.write("\t    ");
 } 
      out.write("\n");
      out.write("\t        </ul>\n");
      out.write("\t    </nav>\n");
      out.write("\t<!-- 고정헤더 끝 -->\n");
      out.write("        <nav id=\"secUtil\" class=\"sec_util\">\n");
      out.write("            \n");
      out.write("            <span>\n");
      out.write("                <a href=\"");
      out.print(request.getContextPath() );
      out.write("\">PIXAR<br><br>BOX</a>\n");
      out.write("            </span>\n");
      out.write("                \n");
      out.write("            <ul class=\"nav-sec\">\n");
      out.write("                <li>\n");
      out.write("                    <a href=\"");
      out.print(request.getContextPath() );
      out.write("/ticket/ticketingStep1/step1.do\">예매</a>\n");
      out.write("                </li>\n");
      out.write("                <li>\n");
      out.write("                    <a href=\"");
      out.print(request.getContextPath() );
      out.write("/movie/boxOffice/order1.do\">영화</a>\n");
      out.write("                </li>\n");
      out.write("                <li>\n");
      out.write("                    <a href=\"");
      out.print(request.getContextPath());
      out.write("/theater/theaterInfo\">영화관</a>\n");
      out.write("                </li>\n");
      out.write("                <li>\n");
      out.write("                    <a href=\"#\">이벤트</a>\n");
      out.write("                </li>\n");
      out.write("                <li>\n");
      out.write("                    <a href=\"#\">스토어</a>\n");
      out.write("                </li>\n");
      out.write("                <li>\n");
      out.write("                    <a href=\"#\">VOD</a>\n");
      out.write("                </li>\n");
      out.write("            </ul>\n");
      out.write("        </nav>\n");
      out.write("    </div><!--div.sec_haed-->\n");
      out.write("    <!-- 두번째 헤더 끝 -->\n");
      out.write("</header>\n");
      out.write("<!-- 헤더 끝 -->\n");
      out.write("\n");
      out.write("<!-- <section id=\"content\"> -->\n");
      out.write('\r');
      out.write('\n');

	List<Member> list = (List<Member>)request.getAttribute("list");
	String pageBar = (String)request.getAttribute("pageBar");

      out.write("\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath() );
      out.write("/css/admin.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath() );
      out.write("/css/mypage.css\" />\r\n");
      out.write("\r\n");
      out.write("<div class=\"tab_type01\">\r\n");
      out.write("\t<ul>\r\n");
      out.write("\t\t<li><a href=\"");
      out.print(request.getContextPath() );
      out.write("/admin/memberList\" id=\"memberList\">회원관리</a></li>\r\n");
      out.write("\t\t<li><a href=\"");
      out.print(request.getContextPath() );
      out.write("/admin/chartList/chartListView.do\">매출분석</a></li>\r\n");
      out.write("\t\t<li><a href=\"");
      out.print(request.getContextPath() );
      out.write("/admin/addDeleteMovieNow\">현재상영작관리</a></li>\r\n");
      out.write("\t</ul>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("$(()=>{\r\n");
      out.write("\tvar $searchMemberId = $(\"#search-memberId\");\r\n");
      out.write("\tvar $searchMemberName = $(\"#search-memberName\");\r\n");
      out.write("\tvar $searchMemberEmail = $(\"#search-memberEmail\");\r\n");
      out.write("\tvar $searchMemberPhone = $(\"#search-memberPhone\");\r\n");
      out.write("\tvar $searchMemberGender = $(\"#search-memberGender\");\r\n");
      out.write("\tvar $searchMemberAge = $(\"#search-memberAge\");\r\n");
      out.write("\tvar $searchMemberPoint = $(\"#search-memberPoint\");\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t$(\"#searchType\").change(function(){\r\n");
      out.write("\t\t$searchMemberId.hide();\r\n");
      out.write("\t\t$searchMemberName.hide();\r\n");
      out.write("\t\t$searchMemberEmail.hide();\r\n");
      out.write("\t\t$searchMemberPhone.hide();\r\n");
      out.write("\t\t$searchMemberGender.hide();\r\n");
      out.write("\t\t$searchMemberAge.hide();\r\n");
      out.write("\t\t$searchMemberPoint.hide();\r\n");
      out.write("\t\r\n");
      out.write("\t\t//console.log($(this).val());\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t$(\"#search-\"+$(this).val()).css(\"display\", \"inline-block\");\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("});\r\n");
      out.write("\r\n");
      out.write("$(()=> {\r\n");
      out.write("\t$(\"#memberList\").css(\"color\", \"#503396\")\r\n");
      out.write("\t\t\t\t\t.css(\"font-weight\", \"bold\");\r\n");
      out.write("});\r\n");
      out.write("</script>\r\n");
      out.write("<body>\r\n");
      out.write("<section class=\"listAll\" id=\"memberList-container\">\r\n");
      out.write("\t\r\n");
      out.write("\t<!-- <label for=\"searchType\">검색타입 : </label> -->\r\n");
      out.write("\t<br /><br /><br />\r\n");
      out.write("\t<div id=\"search-container\">\r\n");
      out.write("\t\t<br /><br />\r\n");
      out.write("\t\t<select id=\"searchType\">\r\n");
      out.write("\t\t\t<option value=\"memberId\">아이디</option>\r\n");
      out.write("\t\t\t<option value=\"memberName\">이름</option>\r\n");
      out.write("\t\t\t<option value=\"memberEmail\">이메일</option>\r\n");
      out.write("\t\t\t<option value=\"memberPhone\">핸드폰</option>\r\n");
      out.write("\t\t\t<option value=\"memberGender\">성별</option>\r\n");
      out.write("\t\t\t<option value=\"memberAge\">나이대</option>\r\n");
      out.write("\t\t\t<option value=\"memberPoint\">포인트잔액</option>\r\n");
      out.write("\t\t\t<!-- <option value=\"enrolldate\">가입일</option> -->\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t</select>\r\n");
      out.write("\t\t<div id=\"search-memberId\">\r\n");
      out.write("\t\t\t<form action=\"");
      out.print(request.getContextPath());
      out.write("/admin/memberFinder\">\r\n");
      out.write("\t\t\t\t<input type=\"hidden\" name=\"searchType\" value=\"memberId\" />\r\n");
      out.write("\t\t\t\t<input type=\"search\" name=\"searchKeyword\" id=\"srch\" \r\n");
      out.write("\t\t\t\t\t   size=\"25\" \r\n");
      out.write("\t\t\t\t\t   placeholder=\"검색할 아이디를 입력하세요\"/>\r\n");
      out.write("\t\t\t\t<input type=\"submit\" value=\"검색\" />\r\n");
      out.write("\t\t\t</form>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div id=\"search-memberName\">\r\n");
      out.write("\t\t\t<form action=\"");
      out.print(request.getContextPath());
      out.write("/admin/memberFinder\">\r\n");
      out.write("\t\t\t\t<input type=\"hidden\" name=\"searchType\" value=\"memberName\" />\r\n");
      out.write("\t\t\t\t<input type=\"search\" name=\"searchKeyword\" id=\"srch\" \r\n");
      out.write("\t\t\t\t\t   size=\"25\" \r\n");
      out.write("\t\t\t\t\t   placeholder=\"검색할 회원명을 입력하세요\"/>\r\n");
      out.write("\t\t\t\t<input type=\"submit\" value=\"검색\" />\r\n");
      out.write("\t\t\t</form>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<!--이메일로 찾기  -->\r\n");
      out.write("\t\t<div id=\"search-memberEmail\">\r\n");
      out.write("\t\t\t<form action=\"");
      out.print(request.getContextPath());
      out.write("/admin/memberFinder\">\r\n");
      out.write("\t\t\t\t<input type=\"hidden\" name=\"searchType\" value=\"memberEmail\" />\r\n");
      out.write("\t\t\t\t<input type=\"search\" name=\"searchKeyword\" id=\"srch\"\r\n");
      out.write("\t\t\t\t\t   size=\"25\" \r\n");
      out.write("\t\t\t\t\t   placeholder=\"검색할 이메일을 입력하세요\"/>\r\n");
      out.write("\t\t\t\t<input type=\"submit\" value=\"검색\" />\r\n");
      out.write("\t\t\t</form>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<!--핸드폰으로 찾기  -->\r\n");
      out.write("\t\t<div id=\"search-memberPhone\">\r\n");
      out.write("\t\t\t<form action=\"");
      out.print(request.getContextPath());
      out.write("/admin/memberFinder\">\r\n");
      out.write("\t\t\t\t<input type=\"hidden\" name=\"searchType\" value=\"memberPhone\" />\r\n");
      out.write("\t\t\t\t<input type=\"search\" name=\"searchKeyword\"  id=\"srch\"\r\n");
      out.write("\t\t\t\t\t   size=\"25\" \r\n");
      out.write("\t\t\t\t\t   placeholder=\"검색할 핸드폰을 입력하세요\"/>\r\n");
      out.write("\t\t\t\t<input type=\"submit\" value=\"검색\" />\r\n");
      out.write("\t\t\t</form>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<!-- 성별로 찾기 -->\r\n");
      out.write("\t\t<div id=\"search-memberGender\">\r\n");
      out.write("\t\t\t<form action=\"");
      out.print(request.getContextPath());
      out.write("/admin/memberFinder\">\r\n");
      out.write("\t\t\t\t<input type=\"hidden\" name=\"searchType\" value=\"memberGender\" />\r\n");
      out.write("\t\t\t\t<input type=\"radio\" name=\"searchKeyword\" value=\"F\" />여자\r\n");
      out.write("\t\t\t\t<input type=\"radio\" name=\"searchKeyword\" value=\"M\" />남자\r\n");
      out.write("\t\t\t\t<input type=\"submit\" value=\"검색\" />\r\n");
      out.write("\t\t\t</form>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<!-- 나이대로 찾기 -->\r\n");
      out.write("\t\t<div id=\"search-memberAge\">\r\n");
      out.write("\t\t\t<form action=\"");
      out.print(request.getContextPath());
      out.write("/admin/memberFinder\">\r\n");
      out.write("\t\t\t\t<input type=\"hidden\" name=\"searchType\" value=\"memberAge\" />\r\n");
      out.write("\t\t\t\t<input type=\"radio\" name=\"searchKeyword\" value=\"10\" />10대\r\n");
      out.write("\t\t\t\t<input type=\"radio\" name=\"searchKeyword\" value=\"20\" />20대\r\n");
      out.write("\t\t\t\t<input type=\"radio\" name=\"searchKeyword\" value=\"30\" />30대\r\n");
      out.write("\t\t\t\t<input type=\"radio\" name=\"searchKeyword\" value=\"40\" />40대\r\n");
      out.write("\t\t\t\t<input type=\"radio\" name=\"searchKeyword\" value=\"50\" />50대\r\n");
      out.write("\t\t\t\t<input type=\"radio\" name=\"searchKeyword\" value=\"60\" />60대 이상\r\n");
      out.write("\t\t\t\t<input type=\"submit\" value=\"검색\" />\r\n");
      out.write("\t\t\t</form>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<!--포인트으로 찾기  -->\r\n");
      out.write("\t\t<div id=\"search-memberPoint\">\r\n");
      out.write("\t\t\t<form action=\"");
      out.print(request.getContextPath());
      out.write("/admin/memberFinder\">\r\n");
      out.write("\t\t\t\t<p id=\"ptest\">\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t<input type=\"hidden\" name=\"searchType\" value=\"memberPoint\" />\r\n");
      out.write("\t\t\t\t<input type=\"number\" name=\"searchKeyword1\" id=\"searchKeyword1\" value=\"0\" placeholder=\"0\" /> ~\r\n");
      out.write("\t\t\t\t<input type=\"number\" name=\"searchKeyword2\" id=\"searchKeyword2\" placeholder=\"5000\" />\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t<!-- <input type=\"search\" name=\"searchKeyword\" \r\n");
      out.write("\t\t\t\t\t   size=\"25\" \r\n");
      out.write("\t\t\t\t\t   placeholder=\"검색할 포인트를 입력하세요\"/> -->\r\n");
      out.write("\t\t\t\t<input type=\"submit\" value=\"검색\" />\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t</p>\r\n");
      out.write("\t\t\t</form>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t<table id=\"tbl-member\">\r\n");
      out.write("\t\t<thead>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t\t<th>아이디</th>\r\n");
      out.write("\t\t\t\t<th>이름</th>\r\n");
      out.write("\t\t\t\t<th>이메일</th>\r\n");
      out.write("\t\t\t\t<th>핸드폰</th>\r\n");
      out.write("\t\t\t\t<th>성별</th>\r\n");
      out.write("\t\t\t\t<th>나이대</th>\r\n");
      out.write("\t\t\t\t<th>관심장르</th>\r\n");
      out.write("\t\t\t\t<th>포인트잔액</th>\r\n");
      out.write("\t\t\t\t<th>가입일자</th>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t</thead>\r\n");
      out.write("\t\t<tbody id=\"mList-tbl\">\r\n");
      out.write("\t\t");
 if(list==null || list.isEmpty()){ 
      out.write("\r\n");
      out.write("            <tr>\r\n");
      out.write("                <td colspan=\"9\" align=\"center\"> 검색 결과가 없습니다. </td>\r\n");
      out.write("            </tr>\r\n");
      out.write("        ");
 
            } 
            else {
                for(Member m : list){ 
        
      out.write("\r\n");
      out.write("             <tr>\r\n");
      out.write("               ");
      out.write("\r\n");
      out.write("\t                <td>\r\n");
      out.write("\t\t\t            <a href=\"");
      out.print(request.getContextPath());
      out.write("/admin/memberView?memberId=");
      out.print(m.getMemberId());
      out.write("\">\r\n");
      out.write("\t                \t\t");
      out.print(m.getMemberId());
      out.write("\r\n");
      out.write("\t\t\t            </a>\r\n");
      out.write("\t                </td>\r\n");
      out.write("\t                <td>");
      out.print(m.getLastName()+m.getFirstName() );
      out.write("</td>\r\n");
      out.write("\t                <td>");
      out.print(m.getEmail()!=null?m.getEmail():"");
      out.write("</td>\r\n");
      out.write("\t                <td>");
      out.print(m.getPhone());
      out.write("</td>\r\n");
      out.write("\t                <td>");
      out.print(m.getGender() );
      out.write("</td>\r\n");
      out.write("\t                <td>");
      out.print(m.getAge() );
      out.write("대</td>\r\n");
      out.write("\t                <td>");
      out.print(m.getInterests()!=null?m.getInterests():"");
      out.write("</td>\r\n");
      out.write("\t                <td>");
      out.print(m.getPoint());
      out.write("</td> \r\n");
      out.write("\t                <td>");
      out.print(m.getEnrollDate());
      out.write("</td>\r\n");
      out.write("            </tr>\t\t\r\n");
      out.write("        ");
		} 
            }
        
      out.write("\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t</tbody>\r\n");
      out.write("\t</table>\r\n");
      out.write("\t\r\n");
      out.write("\t<div id=\"pageBar\">\r\n");
      out.write("\t\t");
      out.print(pageBar );
      out.write("\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("</section>\r\n");
      out.write("\n");
      out.write("\n");
      out.write("<!-- css -->\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath() );
      out.write("/css/style.css\" />\n");
      out.write("\n");
      out.write("<!-- slick -->\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"http://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css\"/>\n");
      out.write("<!-- icon -->\n");
      out.write("<link rel=\"stylesheet\" href=\"http://cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css\">\n");
      out.write("<!-- bx slider -->\n");
      out.write("<!-- <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.   css\"> -->\n");
      out.write("\n");
      out.write("<!-- jquery -->\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/js/jquery-3.4.1.js\"></script>\n");
      out.write("\n");
      out.write("<!-- script slick, bxslider -->\n");
      out.write("<script type=\"text/javascript\" src=\"http://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"http://kenwheeler.github.io/slick/slick/slick.min.js\"></script>\n");
      out.write("<script src=\"https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js\"></script>\n");
      out.write("\n");
      out.write("</section>\n");
      out.write("<!-- 풋터 -->\n");
      out.write("<footer class=\"foot\">\n");
      out.write("    <div id=bottomFoot class=\"bottom_foot\">\n");
      out.write("        <ul class=\"div-bottomfoot\">\n");
      out.write("            <li>\n");
      out.write("                <a href=\"#\">공지사항\n");
      out.write("            </li>\n");
      out.write("            <li>\n");
      out.write("                <a href=\"#\">고객센터\n");
      out.write("            </li>\n");
      out.write("            <li>\n");
      out.write("                <a href=\"#\">이용약관\n");
      out.write("            </li>\n");
      out.write("            <li>\n");
      out.write("                <a href=\"#\">이벤트\n");
      out.write("            </li>\n");
      out.write("            <li>\n");
      out.write("                <a href=\"#\">스토어바로가기\n");
      out.write("            </li>\n");
      out.write("        </ul>           \n");
      out.write("    </div>\n");
      out.write("        \n");
      out.write("\n");
      out.write("\n");
      out.write("    \n");
      out.write("    <!-- 풋터 안 인포메이션 -->\n");
      out.write("    <div id=\"infoFoot\" class=\"info_foot\">\n");
      out.write("                                \n");
      out.write("        <span>사업자정보확인</span> <span>개인정보관리자</span><span>통신판매 신고번호 제 00000123123호</span> <br><br>\n");
      out.write("        <span>대표이사: NONAME</span> <span>본점: 서울특별시 서초구 서초동 123-456</span> <span>사업장: 서울특별시 마포구 상암로 123 상암동 456</span><br><br>\n");
      out.write("        <span>고객센터: 1234-5678</span> <span>NOTITLE문의: 02-1234-5678</span> <span>NOTITLE문의:  csnotitle@notitle.com</span>\n");
      out.write("        \n");
      out.write("    <h3>PIXARBOX</h3>\n");
      out.write("    </div><!-- div.info_foot div-->\n");
      out.write("\n");
      out.write("    <div class=\"end_foot\">\n");
      out.write("        InsideOut\n");
      out.write("    </div><!--div.end_foot-->\n");
      out.write("    <!-- 풋터 안 인포메이션 끝 -->\n");
      out.write("</footer>\n");
      out.write("    <!-- 풋터 끝 -->   \n");
      out.write("            \n");
      out.write("            \n");
      out.write("   \n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
