package com.ecs.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecs.dao.TeacherDao;
import com.ecs.domain.Teacher;
import com.ecs.service.TeacherService;

/**
 * 老师的实现类
 * 
 * @author xuluyang
 *
 *         2020年3月7日
 */
@Service
public class TeacherServiceImpl implements TeacherService {
	@Autowired
	private TeacherDao teacherDao;

	/**
	 * 查找所有
	 */
	@Override
	public List<Teacher> findAllTeacher() {

		return teacherDao.findAllTeacher();
	}

	/**
	 * 添加
	 */
	@Override
	public void addTeacher(Teacher teacher) {

		teacherDao.addTeacher(teacher);
	}

	/**
	 * 删除
	 */
	@Override
	public void deleteTeacher(String tnum) {

		teacherDao.deleteTeacher(tnum);
	}

	/**
	 * 修改
	 */
	@Override
	public void updateTeacher(Teacher teacher) {

		teacherDao.updateTeacher(teacher);
	}

	@Override
<<<<<<< HEAD
	public Teacher findTeacherByTnum(String tnum) {
		
		return teacherDao.findTeacherByTnum(tnum);
	}
	
=======
	public List<Teacher> dynamicTeacher(String college, String tnum) {
		
		return teacherDao.dynamicTeacher(college, tnum);
	}

>>>>>>> e274da748c0a67f597722bf4a30d69d41a4b7b7c
}
