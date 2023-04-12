package com.shinhan.frontcontrollerpattern;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import com.shinhan.model.EmpService;
import com.shinhan.vo.EmpVO;

//pojo로 만듦! 포조니까 컨트롤러
public class EmpListController implements CommonControllerInterface {

	@Override
	public String execute(Map<String, Object> data) throws Exception{
		EmpService service = new EmpService();
		List<EmpVO> emplist = service.selectAll();
		
		//request가 유지되는 동안 저장한다.->저장 후 empSelect.jsp로 이동
		//저장되면 무조건 object로 저장함.
		
		HttpServletRequest request = (HttpServletRequest) data.get("request");//왜 저장
		request.setAttribute("empAll", emplist);//왜?
		
		//위임(내가 요청을 받고 응답은 다른 페이지가 한다.) jsp에서 보이기 떄문에
		return "empSelect.jsp";
		//내가 받은 요청과 응답을 전달해줌
		
	}

}
