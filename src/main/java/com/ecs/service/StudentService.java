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

	//添加学生
	public void addStudent(Student student);
	
	//删除学生
	public String deleteStudent(String id);
	
	//修改学生的信息
	public String changeStudentInfo(Student student);

	//动态查询
	public List<Student> dynamicStudents(String school,String college,String major,String classes);
 
	//模糊查询
	public List<Student> fuzzyStudent(String school,String college,String name,String snum);
	
	//根据学号查找学生
	public Student findStudentBySnum(String snum);
	
	//根据学号查找
	public Student findBySnumForwx(String snum);
	
	//查找学生数量
	public Integer coutStudent(String school,String college);
	
	//查找每个专业的学生总数
	public Integer countMajorStudent(String school,String major);
}
