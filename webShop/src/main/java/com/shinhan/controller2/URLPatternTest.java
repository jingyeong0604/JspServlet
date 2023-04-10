package com.shinhan.controller2;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class URLPATTERNTEST
 */
//세가진 url pattern 임/
//@WebServlet("/url/test")//이름이 일치하는 URL pattern
//@WebServlet("/url/test/*")
@WebServlet("*.go")
public class URLPatternTest extends HttpServlet { 
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("getContextPath: "+ request.getContextPath());
		System.out.println("getContextURI: "+request.getRequestURI());
		System.out.println("getContextURL: "+request.getRequestURL());
		//http://localhost:9090/webShop/url/test
		System.out.println("getRemoteAddr : "+ request.getRemoteAddr());
		//서버에 접속한 client ip Address
		System.out.println("getServletPath: "+request.getServletPath());
	
		//getContextPath제외한 나머지 url => /url/test
		System.out.println("getPathInfo: "+request.getPathInfo());

		
		System.out.println("getQueryString:"+ request.getQueryString());
	
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
