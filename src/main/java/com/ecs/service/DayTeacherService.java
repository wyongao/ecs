package com.ecs.service;

import java.util.List;

import com.ecs.domain.DayTeacher;

public interface DayTeacherService {
		
		//老师的模糊查询
		public List<DayTeacher> fuzzyDayTeachers(String tname,String college,String school);
		
		//动态查询
		public List<DayTeacher> dynamicDayTeachers(String school,String college,String tnum);
		
		//保存
		public void addDayTeacher(DayTeacher dayTeacher);
		
		//根据工号查找
		public List<DayTeacher> findByTnumForwx(String tnum);

		//根据工号查找近七天
		public List<DayTeacher> findByTnumAndDateForwx(String tnum);
		
		//根据时间查找
		public List<DayTeacher> findByDateForwx(String tnum, String date);
		
		//修改打卡记录
		public void updateDayTeacherForwx(String tnum,String date, String temp, String symptom, String addr);
		
		//查询当日打卡的老师数量
		public Integer countDayTeachers(String school,String college,String date);
		
		//
		public List<DayTeacher> findWithParam(String school,String college,String date);

}
