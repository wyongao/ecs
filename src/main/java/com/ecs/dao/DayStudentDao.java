package com.ecs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
/**
 * 每日提交所对应的学生实体类
 * @author xuluyang
 *
 * 2020年3月8日
 */
import org.apache.ibatis.annotations.Select;

import com.ecs.domain.DayStudent;
@Mapper
public interface DayStudentDao {
		//查找当日的所有学生
	@Select("select * from day_student where date=#{date}")
	public List<DayStudent> findDayStudents(String date);
		//根据学院查找当天的
	@Select("select * from day_student where college=#{college} and date=#{date}")
	public List<DayStudent> findDayStudentByCollege(String college,String date);
		//根据专业查找当天的
	@Select("select * from day_student where major=#{major} and date=#{date}")
	public List<DayStudent> findDayStudentByMajor(String major,String date);
	  	//根据学院专业班级查找当天的
	@Select("select * from day_student where major=#{major} and classes=#{classes} and date=#{date}")
	public List<DayStudent> findDayStudentByMajorAndClasses(String major,Integer classes,String date);
	//根据学号查找学生的打卡信息
	@Select("select * from day_student where snum=#{snum}")
	public List<DayStudent> findDayStudentBySnum(String snum);
	//查找学生的轨迹信息(面向小程序端的)
	@Select("select addr from day_student where snum=#{sunm}")
	public List<String> traceStudent(String snum);
	//保存每日学生信息
	@Insert("insert into day_student(snum,sname,college,major,classes,addr,date,symptom,temp)"
			+ " values(#{snum},#{sname},#{college},#{major},#{classes},#{addr},#{date},#{symptom},#{temp}) ")
	public void addDayStudent(DayStudent dayStudent);
	
	
}
