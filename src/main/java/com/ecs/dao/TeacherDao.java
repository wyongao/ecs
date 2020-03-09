package com.ecs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ecs.domain.Teacher;
/**
 * teacher的DAO
 * @author xuluyang
 *
 * 2020年3月7日
 */
@Mapper
public interface TeacherDao {
	/**
	 * 查找所有的老师
	 * @return
	 */
	@Select("select * from teacher")
	public List<Teacher> findAllTeacher();
	
	@Insert("insert into teacher(id,tname,tnum,school,college,sex,tel) "
			+ "values(#{id},#{tname},#{tnum},#{school},#{college},#{sex},#{tel})")
	public void addTeacher(Teacher teacher);
	
	@Delete("delete from teacher where tnum=#{tnum}")
	public void deleteTeacher(String tnum);
	
	@Update("update teacher set tname=#{tname},tnum=#{tnum},school=#{school},college=#{college},sex=#{sex},tel=#{tel}")
	public void updateTeacher(Teacher teacher);
	
	
	
}
