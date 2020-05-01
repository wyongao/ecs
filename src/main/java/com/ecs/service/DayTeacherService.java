package com.ecs.service;

import java.util.List;

import com.ecs.domain.DayTeacher;

public interface DayTeacherService {
		
		//查找当日的所有老师
		public List<DayTeacher> findDayTeachers(String date);
		
		//根据学院查找当天的老师
		public  List<DayTeacher> findDayTeacherByCollege(String college,String date);
		
		//根据职工号查找职工的打卡信息
		public  List<DayTeacher> findDayTeacherByTnum(String tnum);
			
		//查找老师的轨迹信息(面向小程序端的)
		public List<String> traceTeacher(String tnum);
		
		//老师的模糊查询
		public List<DayTeacher> fuzzyDayTeachers(String tname);
		
		//动态查询
		public List<DayTeacher> dynamicDayTeachers(String college,String tnum);
		
		//查询当日打卡的老师数量
		public Integer countDayTeachers(String school,String college,String date);
}
