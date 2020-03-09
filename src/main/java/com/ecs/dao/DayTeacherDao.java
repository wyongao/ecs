package com.ecs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ecs.domain.DayTeacher;
/**
 * 老师的每日签到的dao
 * @author xuluyang
 *
 * 2020年3月8日
 */
@Mapper
public interface DayTeacherDao {
		
	//查找当日的所有老师
	@Select("select * from day_teacher where date=#{date}")
	public List<DayTeacher> findDayTeachers(String date);
	
	//根据学院查找当天的老师
	@Select("select * from day_teacher where college=#{college} and date=#{date}")
	public  List<DayTeacher> findDayTeacherByCollege(String college,String date);
	
	//根据职工号查找职工的打卡信息
	@Select("select * from day_teacher where tnum=#{tnum}")
	public  List<DayTeacher> findDayTeacherByTnum(String tnum);
		
	//查找老师的轨迹信息(面向小程序端的)
	@Select("select addr from day_Teacher where tnum=#{tunm}")
	public List<String> traceTeacher(String tnum);

}
