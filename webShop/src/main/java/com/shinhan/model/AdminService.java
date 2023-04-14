package com.shinhan.model;

import java.util.List;

import com.shinhan.vo.AdminVO;

public class AdminService {
	
	AdminDAO dao=new AdminDAO();
			
	public AdminVO loginCheck(String email, String pass) {
		return dao.loginCheck(email, pass);
	}

	public int registerAdmin(AdminVO vo) {
		// TODO Auto-generated method stub
		return dao.registerAdmin(vo);
	}
	
	
	public int dupCheck(String email) {
		return dao.dupCheck(email);
	}

}
