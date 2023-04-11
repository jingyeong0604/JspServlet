package com.shinhan.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shinhan.model.EmpService;
import com.shinhan.vo.AdminVO;
import com.shinhan.vo.EmpVO;

/**
 * Servlet implementation class EmpListServlet
 */
//@WebServlet("/emp/emplist.do")
public class EmpListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		EmpService service = new EmpService();
		List<EmpVO> emplist = service.selectAll();
		
		//request가 유지되는 동안 저장한다.->저장 후 empSelect.jsp로 이동
		//저장되면 무조건 object로 저장함.
		request.setAttribute("empAll", emplist);
		
		//위임(내가 요청을 받고 응답은 다른 페이지가 한다.) jsp에서 보이기 떄문에
		RequestDispatcher rd = request.getRequestDispatcher("empSelect.jsp");
		//내가 받은 요청과 응답을 전달해줌
		rd.forward(request, response);
		
	}



}
