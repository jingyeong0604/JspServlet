package com.shinhan.frontcontrollerpattern;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.shinhan.model.AdminService;
import com.shinhan.vo.AdminVO;

public class EmpSignUpController implements CommonControllerInterface {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest) data.get("request");
		
		String mname= request.getParameter("manager_name");	
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		
		AdminVO vo=new AdminVO(email, mname, pass);
		AdminService service = new AdminService();
		
		int result = service.registerAdmin(vo);
	
		
		
		return "redirect:loginCheck.do";
	}

}
