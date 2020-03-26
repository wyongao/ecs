package com.ecs.service;

import java.util.List;

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
}
