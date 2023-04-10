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
@WebServlet("/GetSessionServlet")
public class GetSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
;

		//요청에 의해 sessionid를 가져온다.
		//false이면 가져오지 않음.
		HttpSession session = request.getSession(false);
		System.out.println(session);
		 
		if(session==null) return;
		
		System.out.println(session.getId());
		System.out.println(session.getMaxInactiveInterval());//30분
		//최초요청인 경우 true 아닌경우 false
		System.out.println(session.isNew());
		

		
		String userid = (String)session.getAttribute("userid");
		int score =(Integer)session.getAttribute("score");
		AdminVO admin =(AdminVO)session.getAttribute("admin");
		
		
		
		response.setContentType("text/html;charset=utf-8"); // <meta charset="utf-8">
		PrintWriter out = response.getWriter();
		out.write("<h1>Session저장연습</h1>");
	}


}
