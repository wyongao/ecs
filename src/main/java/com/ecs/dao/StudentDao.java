package com.ecs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.ecs.dao.provider.StudentProvider;
import com.ecs.domain.Student;
/**
 * 学生的Dao
 * @author xuluyang
 *
 * 2020年3月7日
 */

@Mapper
public interface StudentDao {
	/**
	 * 查找所有的学生
	 * @return
	 */
	@Select("select * from student")
	public List<Student> findAllStudent();
	/**
	 * 添加学生
	 * @param student
	 */
	@Insert("insert into student(sname,snum,school,college,major,classes,sex,tel) "
			+ "values(#{sname},#{snum},#{school},#{college},#{major},#{classes},#{sex},#{tel})")
	public void addStudent(Student student);
	/**
	 * 删除学生
	 * @param snum
	 */
	@Delete("delete from student where snum=#{snum}")
	public void deleteStudent(String snum);
	/**
	 * 更改学生信息
	 */
	@Update("update student set sname=#{sname},snum=#{snum},school=#{school},college=#{college},major=#{major},classes=#{classes},sex=#{sex},tel=#{tel}")
	public void updateStudent(Student student);
	
	@Select("select * from student where college=#{collegename}")
	public List<Student> findStudentByCollegeName(String collegename);

	/**
	 * 动态查询,三个参数
	 * @param college
	 * @param major
	 * @param classes
	 * @return list
	 */
	@SelectProvider(type = StudentProvider.class,method = "selectWithParam")
	public List<Student> dynamicStudents(String college,String major,String classes);

	/**
	 * 根据姓名模糊查询,学号查询
	 * @param name
	 * @param snum
	 * @return
	 */
	
	@SelectProvider(type = StudentProvider.class,method = "fuzzyQueryStudents")
	public List<Student> fuzzyStudent(String name,String snum);

}
