package com.shinhan.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ManagerVO {
	//이거는어디서 가져온건지
	 private String email;
	 private String manager_name;
	 private String pass;
	 private String pic;
}
