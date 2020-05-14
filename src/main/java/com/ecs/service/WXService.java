package com.ecs.service;

public interface WXService {

	//	public String searchSessionId(String sessionid);
	
	//建立openid和学生的联系
	public String setOpenid(String usernum, String openid, String identity);
	
	public String addDayStudentFromwx(String temp, String symptom, String addr, String snum);
	
	public String addDayTeacherFromwx(String temp, String symptom, String addr, String tnum);
	
	public String addApplicationFromwx(String inout, String dest, String reason, String exit, String snum);
	
	public String findBuildingsForwx();
	
	public String addTrackStudentFromwx(String addr, String snum);
	
	public String addTrackTeacherFromwx(String addr, String tnum);
}
