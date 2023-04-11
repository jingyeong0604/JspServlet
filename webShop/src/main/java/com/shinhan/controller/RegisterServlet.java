package com.shinhan.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shinhan.model.AdminService;
import com.shinhan.vo.AdminVO;

/**
 * Servlet implementation class RegisterServlet
 */
//@WebServlet("/auth/signup.do")//절대경로!!!!!!!!!!!넣는게 좋음
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String mname= request.getParameter("manager_name");
		//get방식은 parameter가 넘어올때 url에 encoding되어 들어온다.
		//post방식은 parameter가 넘어올때 요청문서의 body에 들어온다. 한글이 깨짐!
		
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		
		AdminVO vo=new AdminVO(email, mname, pass);
		AdminService service = new AdminService();
		
		int result = service.registerAdmin(vo);
	
		//응답문서가 어떤 문서인지 알려줌!
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter(); //응답문서의 body부분에 출력됨.
		
		if(result==1) {
			out.print("<h1>계정등록 성공</h1>");
		}else {
			out.print("<h1>계정등록 실패</h1>");
			response.addHeader("refresh", "1;login.jsp");
			
			//response.sendRedirect("login.jsp");//재요청
		}
	}

}
