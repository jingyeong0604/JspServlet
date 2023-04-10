package com.shinhan.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class BindingServletTest1
 */
@WebServlet("/bind2")
public class BindingServletTest2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//application영역(ServletContext)에 저장 (was가 살아있는 동안 유효함)
		//session영역(Browser가 살아있는동안 유효)
		//request영역(request가 살아있는 동안 유효)
		ServletContext application = request.getServletContext();
		HttpSession browser =request.getSession();
		
	
		
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().append("Served at:")
		.append(request.getContextPath())
		.append((String)application.getAttribute("mydata1"+"<br>"))
		.append((String)browser.getAttribute("mydata2")+"<br>")
		.append((String)request.getAttribute("mydata3")+"<br>")
		.append((String)request.getAttribute("myname")+"<br>");
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
