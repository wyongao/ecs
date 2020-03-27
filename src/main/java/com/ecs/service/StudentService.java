package com.ecs.service;

import java.util.List;

import com.ecs.domain.Student;
/**
 * 学生的Service
 * @author xuluyang
 *
 * 2020年3月7日
 */
public interface StudentService {
	//查找所有的学生
	public List<Student> findAllStudent();

	//添加学生
	public void addStudent(Student student);
	
	//删除学生
	public void deleteStudent(String snum);
	
	//修改学生的信息
	public void updateStudent(Student student);
	

	//根据学院名称查找学生
	public List<Student> findStudentByCollegeName(String collegename);

	//动态查询
	public List<Student> dynamicStudents(String college,String major,String classes);

	
	//模糊查询
	public List<Student> fuzzyStudent(String name,String snum);

}
