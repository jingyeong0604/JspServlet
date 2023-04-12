package com.shinhan.frontcontrollerpattern;

import java.io.UnsupportedEncodingException;
import java.util.Map;

//모든 Controller의 규격서
public interface CommonControllerInterface {

	public String execute(Map<String, Object> data) throws Exception;
	
}
