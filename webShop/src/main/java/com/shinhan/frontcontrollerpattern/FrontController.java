package com.shinhan.frontcontrollerpattern;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.do")//이 부분 덕분에 .do인것은 모두 frontcontroller를 지남
//이게 없었으면 그냥 일반적인 서블릿과 똑같았다!
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	//request는 front에 하나만 존재함
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		CommonControllerInterface controller = null;
		List<String> a = new LinkedList<>();
		Map<String, Object> data= new HashMap<>();//pojo에게 보낼 데이터 포멧
		
		
		data.put("method", request.getMethod());//1차로 채우기
		data.put("request", request);//안해주면 request는 null로 들어감.
		
		switch(path) {//요청하고자하는 서블릿 path가 무엇인지
		
		case "/site-result/managerAjax.do":
			controller = new AdminPicAjaxController();
			break;
		
		
		
		case "/site-result/picAjax.do":
			controller = new AdminPicAjaxController();
			break;
		
		case "/site-result/jobsAjax.do":
			controller = new JobsAjaxController();
			break;
		
		case "/site-result/layout.do":
			controller = new LayoutTestController();
			break;
		
		case "/download.do":
			data.put("response", response);
			controller = new DownloadController();
			break;
			
		case "/upload.do":
			controller = new UploadController();
			break;
			
		case "/jstl.do":
			controller = new JSTLController();
			break;
			
		case "/auth/loginCheck.do":
			controller = (CommonControllerInterface) new LoginController();
			break;
		case "/auth/logout.do":
			controller = (CommonControllerInterface) new LogOutController();
			break;	
		case "/emp/emplist.do":
			controller = new EmpListController();
			break;
			
		case "/emp/empDetail.do":
			controller = new EmpDetailController();
			break;
			
		case "/emp/empinsert.do":
			controller = new EmpInsertController();
			break;
			
		case "/emp/empDelete.do":
			controller = new EmpDeleteController();
			break;
		case "/auth/signup.do":
			controller = new EmpSignUpController();
			break;	
		case "/auth/emailDupCheck.do":
			controller = new emailDupCheckController();
			break;	
		default:
			break;
		}
		String page=null;
		try {
			page = controller.execute(data);
			System.out.println("$$$$$$$$$$$$$"+page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpSession session = request.getSession();
		for(String key: data.keySet()) {
			if(key.equals("loginUser")) {
				session.setAttribute(key, data.get(key));
			}
		}
		
		//두가지 형식으로 들어옴!
		//redirect 유무에 따라 달라짐.
		//auth/login.jsp
		//redirect:auth/loginCheck.do
		
		if(page.indexOf("redirect:")>=0) {
			response.sendRedirect(page.substring(9));// -> auth/loginCheck.do
		}else if(page.indexOf("responseBody:")>=0){
			response.getWriter().append(page.substring(13));
		}else if(page.indexOf("download")>=0){
			response.getWriter().append("download OK");
		}
		else {
			RequestDispatcher rd;
			rd=request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		}
		

		//System.out.println(request.getMethod()+"요청");
		//System.out.println(request.getRequestURI()+" uri");
		//System.out.println(request.getServletPath()+" servletPath");
	}

}
