package com.shinhan.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
//DTO, VO, JavaBeans: data를 저장해서 전송하기 위해
public class AdminVO {
	private String email;
	private String manager_name;
	private String pass;
	
}


