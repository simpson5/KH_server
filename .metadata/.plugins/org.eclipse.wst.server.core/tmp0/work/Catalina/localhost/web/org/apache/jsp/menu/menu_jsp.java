/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.44
 * Generated at: 2021-03-24 08:59:32 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.menu;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class menu_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
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

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
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
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>오늘의 메뉴</title>\r\n");
      out.write("<style>\r\n");
      out.write("table {border:1px solid black; border-collapse:collapse;}\r\n");
      out.write("th,td {border:1px solid black; padding:10px;}\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<h2>오늘의 메뉴 <sub style=\"color:lightgray\"></sub></h2>\r\n");
      out.write("\t\r\n");
      out.write("\t<table>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<th>메뉴</th>\r\n");
      out.write("\t\t\t<th>품목</th>\r\n");
      out.write("\t\t\t<th>가격</th>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td rowspan=\"3\">메인메뉴</td>\r\n");
      out.write("\t\t\t<td>한우버거</td>\r\n");
      out.write("\t\t\t<td>5000원</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td>밥버거</td>\r\n");
      out.write("\t\t\t<td>4500원</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td>치즈버거</td>\r\n");
      out.write("\t\t\t<td>4000원</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td rowspan=\"2\">사이드메뉴</td>\r\n");
      out.write("\t\t\t<td>감자튀김</td>\r\n");
      out.write("\t\t\t<td>1500원</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td>어니언링</td>\r\n");
      out.write("\t\t\t<td>1700원</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td rowspan=\"4\">음료메뉴</td>\r\n");
      out.write("\t\t\t<td>콜라</td>\r\n");
      out.write("\t\t\t<td>1000원</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td>사이다</td>\r\n");
      out.write("\t\t\t<td>1000원</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td>커피</td>\r\n");
      out.write("\t\t\t<td>1500원</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td>밀크쉐이크</td>\r\n");
      out.write("\t\t\t<td>2500원</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t</table>\r\n");
      out.write("\t<br /><br />\r\n");
      out.write("\t\r\n");
      out.write("\t<form name=\"menuFrm\" action=\"/web/menu.do\" method=\"POST\">\r\n");
      out.write("\t\t메인메뉴 : \r\n");
      out.write("\t\t<select id=\"main_menu\" name=\"main_menu\" required>\r\n");
      out.write("\t\t\t<option value=\"\" disabled selected>햄버거를 선택하세요.</option>\r\n");
      out.write("\t\t\t<option value=\"한우버거\">한우버거</option>\r\n");
      out.write("\t\t\t<option value=\"치즈버거\">치즈버거</option>\r\n");
      out.write("\t\t\t<option value=\"밥버거\">밥버거</option>\r\n");
      out.write("\t\t</select>\r\n");
      out.write("\t\t<br /><br />\r\n");
      out.write("\t\t사이드메뉴 :\r\n");
      out.write("\t\t<select id=\"side_menu\" name=\"side_menu\" required>\r\n");
      out.write("\t\t\t<option value=\"\" disabled selected>사이드메뉴를 선택하세요.</option>\r\n");
      out.write("\t\t\t<option value=\"감자튀김\">감자튀김</option>\r\n");
      out.write("\t\t\t<option value=\"어니언링\">어니언링</option>\r\n");
      out.write("\t\t</select>\r\n");
      out.write("\t\t<br /><br />\r\n");
      out.write("\t\t음료메뉴 : \r\n");
      out.write("\t\t<select id=\"drink_menu\" name=\"drink_menu\" required>\r\n");
      out.write("\t\t\t<option value=\"\" disabled selected>음료를 선택하세요.</option>\r\n");
      out.write("\t\t\t<option value=\"콜라\">콜라</option>\r\n");
      out.write("\t\t\t<option value=\"사이다\">사이다</option>\r\n");
      out.write("\t\t\t<option value=\"커피\">커피</option>\r\n");
      out.write("\t\t\t<option value=\"밀크쉐이크\">밀크쉐이크</option>\r\n");
      out.write("\t\t</select>\r\n");
      out.write("\t\t<br /><br />\r\n");
      out.write("\t\t<input type=\"submit\" value=\"주문\" />&nbsp;\r\n");
      out.write("\t\t<input type=\"reset\" value=\"취소\" />\r\n");
      out.write("\t</form>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
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
