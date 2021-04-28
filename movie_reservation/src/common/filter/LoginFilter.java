package common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.simp.member.model.vo.Member;


/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(urlPatterns = {
		"/product/payment",
		"/movie/reservation",
		"/review/write",
		"/product/shoppinglist",
		"/member/mypageMyMovie"
		})
public class LoginFilter implements Filter {
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//로그인 여부 확인하기
		HttpServletRequest httpReq = (HttpServletRequest)request;
		HttpServletResponse httpRes = (HttpServletResponse)response;
		
		HttpSession session = httpReq.getSession();
		Member loginMember = null;
		loginMember = (Member)session.getAttribute("loginMember");
		
		String referer =  httpReq.getHeader("Referer");
		
		if(loginMember == null) {
			session.setAttribute("msg", "로그인후 사용할 수 있습니다.");
			httpRes.sendRedirect(referer);
			return;
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}
}
