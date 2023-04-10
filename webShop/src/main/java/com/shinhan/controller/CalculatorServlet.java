package com.shinhan.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalculatorServlet
 */
@WebServlet("/mvc2model/calc")
public class CalculatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// page보여주기...주소창의 주소를 변경한다.(재요청)
		//response.sendRedirect("calc.jsp");
		
		//page보여주기..주소창은 그대로 두고 위임하기 forward! 화면만 바뀜!
		RequestDispatcher rd = request.getRequestDispatcher("calc.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request , HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//계산하기
		int num1 = Integer.parseInt(request.getParameter("su1"));
		int num2 = Integer.parseInt(request.getParameter("su1"));
		String op = request.getParameter("op");
		int result = 0;
		
		switch (op) {
		case "+": result = num1+num2; break;
		case "-": result = num1-num2; break;
		case "*": result = num1*num2; break;
		case "/": result = num1/num2; break;
		
		default:
			break;
		}
		//request에 값을 저장먼저 한다. 값을 아니까!
		request.setAttribute("su1", num1);
		request.setAttribute("op", op);
		request.setAttribute("su2", num2);
		request.setAttribute("result", result);
		
		// page보여주기..주소창은 그대로 두고 위임하기 forward! 화면만 바뀜!
		RequestDispatcher rd = request.getRequestDispatcher("calc.jsp");
		rd.forward(request, response);
		
		/*페이지가 이동됨 client->servlet->client로 바로 전달
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("결과는 "+result);
		*/
	}

}


