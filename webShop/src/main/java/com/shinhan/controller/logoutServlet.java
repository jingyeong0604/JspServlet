package com.shinhan.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shinhan.vo.AdminVO;

/**
 * Servlet implementation class logoutServlet
 */
@WebServlet("/auth/logout.do")
public class logoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("logout.do....");
		
		request.getSession(false).invalidate();
		
		//새션을 지우기, Browser접속을 해제하기
		
		ServletContext app = getServletContext();
		HttpSession session = request.getSession();
		
		Object obj = app.getAttribute("userList");
		AdminVO admin = (AdminVO) session.getAttribute("loginUser");
		List<AdminVO> userList= null;
		if(obj!=null) {
			userList = (List<AdminVO>) obj;
			userList.remove(admin);
			app.setAttribute("userList", userList);
		}
	}



}
