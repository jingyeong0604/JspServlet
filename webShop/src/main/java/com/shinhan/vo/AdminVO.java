package com.shinhan.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


//DTO, VO, JavaBeans: data를 저장해서 전송하기 위해
public class AdminVO {
	private String email;
	private String manager_name;
	private String pass;
	
	public String getEmail() {
		System.out.println("getEmail()........"+email);
		return email;
	}
	public AdminVO() {
		System.out.println("AdminVO-----default 생성자");
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getManager_name() {
		return manager_name;
	}
	public void setManager_name(String manager_name) {
		this.manager_name = manager_name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	@Override
	public String toString() {
		return "AdminVO [email=" + email + ", manager_name=" + manager_name + ", pass=" + pass + "]";
	}
	public AdminVO(String email, String manager_name, String pass) {
		super();
		this.email = email;
		this.manager_name = manager_name;
		this.pass = pass;
	}
	
	
}


