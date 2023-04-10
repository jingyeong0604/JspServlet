package com.shinhan.controller2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SetCookie
 */
@WebServlet("/getcookie")
public class GetCookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

  

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//브라우저에 저장된 쿠키가 요청과 함께 온다. 쿠키 읽기해보자!

		
		response.setContentType("text/html;charset=utf-8"); //<meta charset="utf-8">
		PrintWriter out = response.getWriter();
		out.write("<h1>쿠키읽기연습</h1>");
		//out.write("<p>")
		
		Cookie[] cs = request.getCookies();
		for(Cookie c :cs) {
			out.write(c.getName() + "---->" +c.getValue()+"<br>");//out.write 콘솔이 아니라 브라우저에 찍힘
		}
	}



}
