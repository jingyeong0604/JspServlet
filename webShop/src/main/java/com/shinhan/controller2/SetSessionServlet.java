package com.shinhan.controller2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shinhan.vo.AdminVO;

/**
 * Servlet implementation class SetSessionServlet
 */
@WebServlet("/SetSessionServlet")
public class SetSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		String userpass = request.getParameter("userpass");

		//최초접속인 세션은 세션이 없으므로 자동으로 만들어짐.
		HttpSession session = request.getSession();
		System.out.println(session.getId());
		System.out.println(session.getMaxInactiveInterval());//30분
		//최초요청인 경우 true 아닌경우 false
		System.out.println(session.isNew());
		
		session.setAttribute("key1", userid);
		session.setAttribute("key2", 100);
		session.setAttribute("key3", new AdminVO("wlsrud@a.com","진","1234"));
		
		
		
		response.setContentType("text/html;charset=utf-8"); // <meta charset="utf-8">
		PrintWriter out = response.getWriter();
		out.write("<h1>Session저장연습</h1>");
	}


}
