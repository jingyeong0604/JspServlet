package com.shinhan.frontcontrollerpattern;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.shinhan.model.CompanyService;
import com.shinhan.vo.AdminVO;

public class AdminPicAjaxController implements CommonControllerInterface {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		// TODO Auto-generated method stub
		// 모든 job조회하기
		CompanyService service = new CompanyService();
		HttpServletRequest request = (HttpServletRequest) data.get("request");
		List<AdminVO> managerlist = service.adminSelect();
		request.setAttribute("managerList", managerlist);

		// 1)JavaScript로 작성된 ajax부분에 전달하는 방법1 -- JSP만들어서 보낸다.(HTML디자인)
		// 포워드시킴
		// return "jobView.jsp";

		// 2)JavaScript로 작성된 ajax부분에 전달하는 방법2--JSON만들어서 보낸다.(JS로 디자인)

		
		//사진을 보여주는것이기 때문에 pic만 가져옴.
		//사진 여러개일수 있기 때문에 JSONArray사용함.
		JSONArray arr = new JSONArray();
		for (AdminVO manager : managerlist) {
			JSONObject obj = new JSONObject();
			obj.put("pic", manager.getPic());
			//obj.put("", emp.);
			arr.add(obj);
		}
		//JSON에 직접 접근하기 위해서 한 번 더 obj선언함.
		JSONObject jobsObj = new JSONObject();
		jobsObj.put("ManagerList", arr);
		// obj.put("job_id", job.getJob_id());
		
		//보낼때는 json을 문자열로 만들어줘야함 toJSONString으로 고치기!
		return "responseBody:" + jobsObj.toJSONString();
		

	}

}
