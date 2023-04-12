package com.shinhan.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shinhan.vo.AdminVO;

/**
 * Servlet Filter implementation class LoginCheckFilter
 */
@WebFilter("*.do")//css js jsp servlet image
public class LoginCheckFilter extends HttpFilter implements Filter {
       

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse rep = (HttpServletResponse)response;
		
		
		//HttpSession browser = req.getSession();
		//AdminVO user = (AdminVO)browser.getAttribute("loginUser");
		
		System.out.println("req.getServletPath():" +req.getServletPath());
		//로그인됐나 확인
		if(req.getServletPath().equals("/auth/loginCheck.do") || 
				req.getServletPath().equals("/auth/signup.do") ||
				req.getServletPath().equals("/auth/emailDupCheck.do") )  {
			
		}else {
			//로그인이 안됐을경우!
			HttpSession browser = req.getSession();
			AdminVO user = (AdminVO)browser.getAttribute("loginUser");
			if(user==null) {
				rep.sendRedirect(req.getContextPath()+ "/auth/loginCheck.do");
				return;
			} else {
				System.out.println("user:"+user);
			}
		}


		// 요청
		chain.doFilter(request, response);
	}


	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
