package com.shinhan.frontcontrollerpattern;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.shinhan.vo.AdminVO;

public class LogOutController implements CommonControllerInterface {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request =(HttpServletRequest)data.get("request");
		
		//현재 로그인한 사람을 session에 유지시켜줌
		ServletContext app = request.getServletContext();
		HttpSession session = request.getSession();
		
		Object obj = app.getAttribute("userList");
		AdminVO admin = (AdminVO) session.getAttribute("loginUser");
		List<AdminVO> userList= null;
		if(obj!=null) {
			userList = (List<AdminVO>) obj;
			userList.remove(admin);
			app.setAttribute("userList", userList);
		}
		request.getSession(false).invalidate();
		
		//새션을 지우기, Browser접속을 해제하기
		
		//looutControllerㄱㅏ 갔다오면 front가 받음 OK문자를 header.jsp에 보냄.
		return "responseBody:OK";
	}

}
