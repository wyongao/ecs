package com.ecs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.ecs.dao.provider.DayTeacherProvider;
import com.ecs.domain.DayStudent;
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
	
	//模糊查找
	@SelectProvider(type = DayTeacherProvider.class, method = "fuzzyQueryDayTeachers")
	public List<DayTeacher> fuzzyDayTeachers(String tname,String college);
	
	//动态查询
	@SelectProvider(type = DayTeacherProvider.class,method = "selectWithParam")
	public List<DayTeacher> dynamicDayTeachers(String college,String tnum);

	//保存每日学生信息
	@Insert("insert into day_teacher(tnum,tname,school,college,addr,date,symptom,temp)"
			+ " values(#{tnum},#{tname},#{school},#{college},#{addr},#{date},#{symptom},#{temp}) ")
	public void addDayTeacher(DayTeacher dayTeacher);

	//根据工号查找老师的打卡信息
	@Select("select addr,date,temp,symptom from day_teacher where tnum=#{tnum} order by date desc")
	public List<DayTeacher> findByTnumForwx(String tnum);
	
	@Select("select * from day_teacher where date=#{date}")
	public List<DayTeacher> findByDateForwx(String date);

	//查找所有当日打卡的老师
	@SelectProvider(type = DayTeacherProvider.class,method = "countDayTeachers")
	public Integer countDayTeachers(String school,String college,String date);

	
	//查找老师用于下拉框
	@Select("select * from day_teacher where school=#{school} and college=#{college} and date=#{date}")
	public List<DayTeacher> selectDayTeacher(String school, String college, String date);


}
