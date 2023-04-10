package com.shinhan.model;

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
import javax.servlet.http.HttpSession;

import org.apache.catalina.filters.SetCharacterEncodingFilter;

import com.shinhan.vo.AdminVO;

/**
 * Servlet Filter implementation class EncodingFilter
 */
//전부다 수행하고싶음@
@WebFilter("/*")
public class EncodingFilter extends HttpFilter implements Filter {
       
	
	
	
	
    public EncodingFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		request.setCharacterEncoding("utf-8");
		
		//강제형변환
		//자식 = (자식)부모
		HttpServletRequest req = (HttpServletRequest)request;
		System.out.println("EncodingFilter에서 getContextPath:"+req.getContextPath());
		System.out.println("EncodingFilter에서 getRequestURI:"+req.getRequestURI());
		System.out.println("EncodingFilter에서 getRequestURL:"+req.getRequestURL());
		
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
