package com.ecs.service;


public interface WXService {

	//	public String searchSessionId(String sessionid);
	
	//建立openid和学生的联系
	public String setOpenid(String snum, String openid);
	
	public String addDayStudentFromwx(String temp, String symptom, String addr, String snum);
	
	public String addApplicationFromwx(String inout, String dest, String reason, String exit, String snum);
}
