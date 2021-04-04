package com.ecs.service;

import java.util.List;

import com.ecs.domain.DayStudent;

import com.ecs.domain.Student;

/**
 * 每日学生信息的服务类
 * 
 * @author xuluyang
 *
 *         2020年3月8日
 */

public interface DayStudentService {

	// 动态sql
	public List<DayStudent> findAllDayStudents(String school, String college, String major, String classes, String snum, String date);

	// 保存每日学生信息
	public void addDayStudent(DayStudent dayStudent);

	// 根据姓名模糊查询
	public List<DayStudent> fuzzyQueryDaystudents(String school, String college, String name);

	// 根据学号查找
	public List<DayStudent> findBySnumForwx(String snum);

	// 根据学号查找近七天
	public List<DayStudent> findBySnumAndDateForwx(String snum);

	// 根据时间查找
	public List<DayStudent> findByDateForwx(String snum, String date);

	
	//修改打卡记录
	public void updateDayStudentForwx(String snum,String date, String temp, String symptom, String addr);
	
	//查找全校所有的当日打卡学生数量
	public Integer countDayStudent(String school,String college,String date);
	
	//查找全专业所有的打卡学生数量
	public Integer countDayMajorStudent(String school,String major,String date);
	
	//修改信息
	public void updateDayStudentInfo(String snum,String sname,String school,String college,String major,String classses,String snum1);
	
	//筛选打卡信息
	public List<DayStudent> screenDayStudents(String temp);
	
	//未打卡的学生
	public List<Student> screenNoSignStudents(String school,String college,String major,String classes,String date);





}
