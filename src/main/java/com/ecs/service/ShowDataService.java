package com.ecs.service;

import java.util.Map;

public interface ShowDataService {
	//根据学校找到所有的学院
	//还要查出来各个专业的总人数
	//以及打卡的人数和没有打卡的人数
	//查询不同学院的老师和学生的打卡情况
	public Map<String, Object> dynamicPie(String school,String college,Integer collegeId);
	public Map<String, Object> initStudentData(String school,String college);
	public Map<String, Object> initTeacherData(String school,String college);
}
