package com.shinhan.frontcontrollerpattern;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.shinhan.model.AdminService;
import com.shinhan.vo.AdminVO;

public class LoginController implements CommonControllerInterface {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		String method = (String) data.get("method");
		String page = "";
		
		if(method.equals("GET")) {
			page = "login.jsp";
		}else {
		
		HttpServletRequest request = (HttpServletRequest) data.get(("request"));
		
		String email=request.getParameter("email");
		String pass = request.getParameter("pass");
		AdminService service= new AdminService();
		AdminVO admin = service.loginCheck(email, pass);
		ServletContext app = request.getServletContext();
		
		System.out.println(admin==null?"로그인실패": admin);
		
		Object obj = app.getAttribute("userlist");
		
		List<AdminVO> userList = null;
		
		if(admin!=null) {
			if(obj==null) {
				userList = new ArrayList<>();
			}else {
				userList.add(admin);
				
			}
			userList.add(admin);
			app.setAttribute("userList", userList);
			System.out.println("---------로그인한 사람list----------");
			for(AdminVO vo:userList) {
				System.out.println(vo);
			}
			System.out.println("--------------------------------");
		
			//로그인성공
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", admin);
			String path = request.getContextPath();

			page="redirect:"+path+"/emp/emplist.do";
		}else {
			//로그인실패
			page="redirect:loginCheck.do";
			
		}
	
		}
		
		return page;
	}

}
