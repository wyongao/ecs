package com.ecs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
/**
 * 每日提交所对应的学生实体类
 * @author xuluyang
 *
 * 2020年3月8日
 */
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.ecs.dao.provider.DayStudentProvider;
import com.ecs.domain.DayStudent;
@Mapper
public interface DayStudentDao {
	
	//动态sql
	@SelectProvider(type = DayStudentProvider.class,method = "selectWithParam")
	public List<DayStudent> findAllDayStudents(@Param("school")String school, @Param("college")String college, @Param("major") String major,@Param("classes") String classes,@Param("snum") String snum,@Param("date")String date);
	
	//保存每日学生信息
	@Insert("insert into day_student(snum,sname,school,college,major,classes,addr,date,symptom,temp)"
			+ " values(#{snum},#{sname},#{school},#{college},#{major},#{classes},#{addr},#{date},#{symptom},#{temp}) ")
	public void addDayStudent(DayStudent dayStudent);

	//根据学号或者姓名进行模糊查询
	@SelectProvider(type = DayStudentProvider.class,method = "fuzzyQueryDaystudents")
	public List<DayStudent> fuzzyQueryDaystudents(String school,String college,String name);
	
	//根据学号查找学生的打卡信息
	@Select("select addr,date,temp,symptom from day_student where snum=#{snum} order by date desc")
	public List<DayStudent> findBySnumForwx(String snum);

	//根据学号查找学生的打卡信息
	@Select("select temp from day_student where DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date and snum=#{snum}")
	public List<DayStudent> findBySnumAndDateForwx(String snum);
	
	//根据时间查找学生的打卡信息
	@Select("select * from day_student where snum=#{snum} and date=#{date}")
	public List<DayStudent> findByDateForwx(String snum, String date);

	//修改打卡记录
	@Update("update day_student set temp=#{temp},symptom=#{symptom},addr=#{addr} where snum=#{snum} and date=#{date}")
	public void updateDayStudentForwx(String snum, String date, String temp, String symptom, String addr);
	
	//查找各个学院的打卡人数
	@SelectProvider(type = DayStudentProvider.class,method = "countDayStudents")
	public Integer countDayStudent(String school,String college,String date);
	
	//查找各个专业的打卡人数
	@Select("select count(*) from day_student where school=#{school} and major=#{major} and date=#{date}")
	public Integer countDayMajorStudent(String school,String major,String date);
	
	//修改信息
	@Update("update day_student set snum=#{snum},sname=#{sname},school=#{school},college=#{college},major=#{major},classes=#{classes} where snum=#{snum1}")
	public void updateDayStudentInfo(String snum,String sname,String school,String college,String major,String classes,String snum1);
}
