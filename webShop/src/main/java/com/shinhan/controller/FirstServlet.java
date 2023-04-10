package com.shinhan.controller;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @WebServlet : 요청URL에 의해 실행할 Servlet연결 (Servlet Mapping)
 * 주의점 : 요청URL에 반드시 /로 시작한다.
 * /를 생략하면 오류~~ 서블릿 매핑에서 유효하지 않은 <url-patter>
 *
 */
//@WebServlet({ "/FirstServlet", "/first", "/ff" })
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FirstServlet() {
        System.out.println("FirstServlet생성자이다.");
        
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
        System.out.println("FirstServlet init");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
        System.out.println("FirstServlet destroy");
	}

	/**
	 * service는 모든 요청방식을 처리하고자 하는 경우 사용
//	 */
//	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("FirstServlet service");
//	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("FirstServlet doGet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("FirstServlet doPost");
	}

}
