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
	
	@Delete("delete from teacher where id=#{id}")
	public void deleteTeacher(String id);
	
	@Update("update teacher set tname=#{tname},tnum=#{tnum},school=#{school},college=#{college},sex=#{sex},tel=#{tel},identify=#{identify}  where id=#{id} ")
	public void changeTeacher(Teacher teacher);
	
	@Insert("insert into teacher(tname,tnum,password,school,college,sex,tel,identify) "
			+ "values(#{tname},#{tnum},#{password},#{school},#{college},#{sex},#{tel},#{identify})")
	public void addTeacher(String tname, String tnum, String password, String school, String college, String sex, String tel,String identify);
	
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
	public List<Teacher> dynamicTeacher(String school,String college,String tnum);
	/**
	 * 根据姓名模糊查询
	 * @param name
	 * @return
	 */
	@SelectProvider(type = TeacherProvider.class,method= "fuzzyQueryTeacher")
	public List<Teacher> fuzzyTeacher(String school,String college,String name);
	
	//根据工号查询
	@Select("select tname,tnum,school,college from teacher where tnum=#{tnum}")
	public Teacher findByTnumForwx(String tnum);
	
	//根据工号查询
	@Select("select school from teacher where tnum=#{tnum}")
	public String findSchoolByTnumForwx(String tnum);
	
	//根据工号查询
	@Select("select tname,tnum,school,college,sex,tel,identify from teacher where tnum=#{tnum}")
	public Teacher findByTnumForweb(String tnum);
	
	//根据工号插入openid
	@Update("update teacher set openid=#{openid} where tnum=#{tnum}")
	public void setOpenidByTnum(String tnum, String openid);

	@SelectProvider(type = TeacherProvider.class,method = "countTeachers")
	public Integer countTeachers(String school,String college);

}
