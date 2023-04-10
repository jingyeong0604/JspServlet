package com.shinhan.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReuquestTestServlet
 */
@WebServlet("/requestTest")
public class ReuquestTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReuquestTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("===========요청정보 얻기==========");
		//Header + Body(화면에 보이는것)
		System.out.println("getContextPath:"+request.getContextPath());
		System.out.println("getMethod:"+request.getMethod());
		System.out.println("getRequestURI:"+request.getRequestURI());
		System.out.println("getLocalPort:"+request.getLocalPort());
		System.out.println("getLocalAddr:"+request.getLocalAddr());
		System.out.println("getRemoteAddr:"+request.getRemoteAddr());
		System.out.println("getServletPath:"+request.getServletPath());
		
		//내가 요청한 주소 그 이후에 있는 주소들도 얻을 수 있음.
		System.out.println("getPathInfo:"+request.getPathInfo());
		
		
		
		//요청한 Browser의 정보 : user-agent 
		Enumeration<String> hs = request.getHeaderNames();
		while(hs.hasMoreElements()) { 
			String name=hs.nextElement();
			System.out.println("headerName:"+name);
			System.out.println(request.getHeader(name));
			System.out.println("----------------------------");
		}
		
		response.getWriter().append("jingyeong...Served at ").append(request.getContextPath());
	}



}
