package com.ecs.service;

import java.util.List;

import com.ecs.domain.DayStudent;
/**
 * 每日学生信息的服务类
 * @author xuluyang
 *
 * 2020年3月8日
 */

public interface DayStudentService {
		//查找当日的所有学生
	public List<DayStudent> findDayStudents(String date);
		//根据学院查找当天的
	public List<DayStudent> findDayStudentByCollege(String college,String date);
		//根据专业查找当天的
	public List<DayStudent> findDayStudentByMajor(String major,String date);
	  	//根据学院专业班级查找当天的
	public List<DayStudent> findDayStudentByMajorAndClasses(String major,Integer classes,String date);
		//根据学号查找学生的打卡信息
	public List<DayStudent> findDayStudentBySnum(String snum);
		//查找学生的轨迹信息(面向小程序端的)
	public List<String> traceStudent(String snum);
		//动态sql
	public List<DayStudent> findAllDayStudents(String college,String major,String classes,String snum,String date);
		//保存每日学生信息
	public void addDayStudent(DayStudent dayStudent);
	    //查找所有
	public List<DayStudent> findAll();
    //查找所有
	public List<DayStudent> findByCollege(String college);

}
