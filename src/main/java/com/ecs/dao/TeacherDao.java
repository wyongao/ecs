package com.ecs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.ecs.dao.provider.TeacherProvider;
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
	
	@Delete("delete from teacher where tnum=#{tnum}")
	public void deleteTeacher(String tnum);
	
	@Update("update teacher set tname=#{tname},tnum=#{tnum},school=#{school},college=#{college},sex=#{sex},tel=#{tel}")
	public void updateTeacher(Teacher teacher);
	
	@Insert("insert into teacher(tname,tnum,password,school,college,sex,tel) "
			+ "values(#{tname},#{tnum},#{password},#{school},#{college},#{sex},#{tel})")
	public void addTeacher(String tname, String tnum, String password, String school, String college, String sex, String tel);
	
	@Select("select * from teacher where tnum=#{tnum}")
	public Teacher findTeacherByTnum(String tnum);
	
	@Select("select * from teacher where college=#{college}")
	public List<Teacher> findTeacherByCollege(String college);
	
	@Update("update teacher set password=#{password} where tnum=#{tnum}")
	public void changePassword(String tnum, String password);
	
	/**
	 * 根据学院和职工号进行查找
	 * @param college
	 * @param tnum
	 * @return
	 */
	@SelectProvider(type = TeacherProvider.class,method = "selectWithParam")
	public List<Teacher> dynamicTeacher(String college,String tnum);
	/**
	 * 根据姓名模糊查询
	 * @param name
	 * @return
	 */
	@SelectProvider(type = TeacherProvider.class,method= "fuzzyQueryTeacher")
	public List<Teacher> fuzzyTeacher(String name);
}
