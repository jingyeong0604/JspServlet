package com.shinhan.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shinhan.model.AdminService;
import com.shinhan.vo.AdminVO;

/**
 * http://localhost:9090/webShop 에 있는 경로인데
 *  
 */
/*확장명 .do를 붙인다.*/
@WebServlet({"/auth/loginCheck.do"})//URL mapping주소 정의
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd;
		rd=request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//<input name="email"> input tag의 이름이 이메일이다
		//<input name="pass">
		String email=request.getParameter("email");
		String pass = request.getParameter("pass");
		AdminService service= new AdminService();
		AdminVO admin = service.loginCheck(email, pass);
		System.out.println(admin==null?"로그인실패": admin);
		
		//응답문서만들기
		//<meta charset="UTF-8">
		//내가 보낼 문자는 text의 html문서라고 알려줌
		response.setContentType("text/html;charset=utf-8");
		//내가 만드는 응답문서는 PrintWriter라고 표현함 = ResposeBody에 문자열을 출력하기
		//getWriter로 응답문서를 만들기
		PrintWriter out = response.getWriter();
		
		
		if(admin==null) {
			//응답문서에다가 3초잇다가 login.jsp를 뿌려주라고 header한테 알려줌.
			//응답문서에 header부분을 작성해서 내려감.
			response.setHeader("refresh", "3;loginCheck.do");//auth밑에 잇으니까 안줘도됨.
			out.print("<h1>Login Fail</h1>");
			return;
		}
	
		
		
		//로그인한 사람의 정보를 저장하기
	//	request.setAttribute("loginUser", admin);
		
		HttpSession session = request.getSession();
		session.setAttribute("loginUser", admin);
		
		//ServletContext app = request.getServletContext();
		//app.setAttribute("loginUser", admin);
	
		
//		//응답문서에 주소창을 바꾼다. = location.href="";
//		//http://localhost:9090/까지밖에 안된다.
		String path = request.getContextPath();
		String encodeName = URLEncoder.encode(admin.getManager_name(), "utf-8");
		//sendRedirect자체가 get 방식임. get으로 주소를 바꾸려고 함.
		response.sendRedirect(path + "/emp/emplist.do?myname="+encodeName);
		
		//재요청 
		//sendRedirect와 같음
		//js를 이용해서 응답문을 만듦
//		out.print("<script>"
//				+"alert('로그인성공...업무화면으로 이동합니다.');"
//				+ "location.href='"
//				+ path
//				+ "/emp/emplist.do';"
//				+ "</script>");
//		
		
	}
	


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	

}
