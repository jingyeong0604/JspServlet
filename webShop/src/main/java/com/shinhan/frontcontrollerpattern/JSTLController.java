package com.shinhan.frontcontrollerpattern;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.shinhan.vo.AdminVO;

public class JSTLController implements CommonControllerInterface {

	//frontcontroller에서 전달받아서 page이름을 알려줌
	
	@Override
	public String execute(Map<String, Object> data) throws Exception {
		
		// /로 시자갛면 절대 /가 없으면 상대경로이다
		// /webShop이 생략되어 있으므로 현재는 모두 가능하다.
		HttpServletRequest request = (HttpServletRequest) data.get("request");
		ServletContext application = request.getServletContext();
		HttpSession session = request.getSession();
		application.setAttribute("myName", "김길동application");
		request.setAttribute("myName", "박길동request");
		session.setAttribute("myName", "홍길동session");
		
		List<AdminVO> adminList = new ArrayList<>();
		adminList.add(new AdminVO("abc1@daum.net","김1","1234"));
		adminList.add(new AdminVO("abc2@daum.net","김2","1234"));
		adminList.add(new AdminVO("abc3@daum.net","김3","1234"));
		//request.setAttribute("count",0); == <c:set var="count" value="0">
		
		
		//adminList는 자바영역에 저장되어 있는 변수이고
		//"admins" request 영역에 저장된 변수
		request.setAttribute("admins", adminList);
		
		
		request.setAttribute("score", 99);
		request.setAttribute("today", new Date());
		
		return "/jsptest/jstlTest3.jsp";
	}

}
