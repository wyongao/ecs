package com.ecs.service;

import java.util.List;

import com.ecs.domain.Student;
import com.ecs.domain.Teacher;

/**
 * 老师的service
 * @author xuluyang
 *
 * 2020年3月7日
 */

public interface TeacherService {
	/**
	 * 查找
	 * @return
	 */
	public List<Teacher> findAllTeacher();
	
	/**
	 * 添加
	 * @param teacher
	 */
	public void addTeacher(Teacher teacher);
	
	/**
	 * 删除
	 * @param tnum
	 */
	public void deleteTeacher(String tnum);
	
	/**
	 * 修改
	 * @param teacher
	 */
	public void updateTeacher(Teacher teacher);
	

	public Teacher findTeacherByTnum(String tnum);

	public List<Teacher> findTeacherByCollege(String college);
	
	/**
	 * 动态查询根据职工号和学院
	 * @param college
	 * @param tnum
	 * @return
	 */
	public List<Teacher> dynamicTeacher(String college,String tnum);

	/**
	 * 模糊查询
	 * @param name
	 * @return
	 */
	public List<Teacher> fuzzyTeacher(String name);
<<<<<<< HEAD


	//根据工号查找
	public Teacher findByTnumForwx(String tnum);
=======
	/**
	 * 查找所有的老师
	 * @param scholl
	 * @param college
	 * @return
	 */
	public Integer countTeachers(String school,String college);
	
>>>>>>> 710caa33b928b41f78a9865894c55d7a8aa3a248

	//根据工号查找
	public Teacher findByTnumForweb(String tnum);
}
