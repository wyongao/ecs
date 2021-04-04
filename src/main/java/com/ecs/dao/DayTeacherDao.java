package com.ecs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.ecs.dao.provider.DayTeacherProvider;
import com.ecs.domain.DayTeacher;
/**
 * 老师的每日签到的dao
 * @author xuluyang
 *
 * 2020年3月8日
 */
@Mapper
public interface DayTeacherDao {
			
	//模糊查找
	@SelectProvider(type = DayTeacherProvider.class, method = "fuzzyQueryDayTeachers")
	public List<DayTeacher> fuzzyDayTeachers(String tname,String college,String school);
	
	//动态查询
	@SelectProvider(type = DayTeacherProvider.class,method = "selectWithParam")
	public List<DayTeacher> dynamicDayTeachers(String school,String college,String tnum);

	//保存每日学生信息
	@Insert("insert into day_teacher(tnum,tname,school,college,addr,date,symptom,temp)"
			+ " values(#{tnum},#{tname},#{school},#{college},#{addr},#{date},#{symptom},#{temp}) ")
	public void addDayTeacher(DayTeacher dayTeacher);

	//根据工号查找老师的打卡信息
	@Select("select addr,date,temp,symptom from day_teacher where tnum=#{tnum} order by date desc")
	public List<DayTeacher> findByTnumForwx(String tnum);
	
	//根据学号查找学生的打卡信息
	@Select("select temp from day_teacher where DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date and tnum=#{tnum}")
	public List<DayTeacher> findBySnumAndDateForwx(String tnum);
	
	//根据时间查找老师的打卡信息	
	@Select("select * from day_teacher where tnum=#{tnum} and date=#{date}")
	public List<DayTeacher> findByDateForwx(String tnum, String date);
	
	//修改打卡记录
	@Update("update day_teacher set temp=#{temp},symptom=#{symptom},addr=#{addr} where tnum=#{tnum} and date=#{date}")
	public void updateDayTeacherForwx(String tnum, String date, String temp, String symptom, String addr);

	//查找所有当日打卡的老师
	@SelectProvider(type = DayTeacherProvider.class,method = "countDayTeachers")
	public Integer countDayTeachers(String school,String college,String date);
	
	//
	@SelectProvider(type = DayTeacherProvider.class,method = "findWithParam")
	public List<DayTeacher> findWithParam(String school,String college,String date);
	
	@Update("update day_teacher set tnum=#{tnum},tname=#{tname},school=#{school},college=#{college} where tnum=#{tnum1}")
	public void updateDayTeacherInfo(String tnum,String tname,String school,String college,String tnum1);
}
