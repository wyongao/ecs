package com.ecs.service.serviceImpl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecs.dao.StudentDao;
import com.ecs.domain.Student;
import com.ecs.service.StudentService;
/**
 * 学生的service实现类
 * @author xuluyang
 *
 * 2020年3月7日
 */
@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentDao studentDao;

	public List<Student> findAllStudent() {
		return studentDao.findAllStudent();
	}

	@Override
	public void addStudent(Student student) {
		studentDao.addStudent(student);
		
	}

	@Override
	public void deleteStudent(String snum) {
		studentDao.deleteStudent(snum);
		
	}

	@Override
	public void updateStudent(Student student) {
		studentDao.updateStudent(student);
		
	}

}
