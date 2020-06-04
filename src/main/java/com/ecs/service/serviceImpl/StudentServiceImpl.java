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
	public String deleteStudent(String id) {
		
		studentDao.deleteStudent(id);		
		return "success";
	}

	@Override
	public String changeStudentInfo(Student student) {
		
		studentDao.changeStudentInfo(student);
		
		return "success";
	}

	@Override

	public List<Student> findStudentByCollegeName(String collegename) {
		
		return studentDao.findStudentByCollegeName(collegename);

	}
		
	@Override
	public List<Student> dynamicStudents(String school,String college, String major, String classes) {
	
		return studentDao.dynamicStudents(school, college, major, classes);

	}

	@Override
	public List<Student> fuzzyStudent(String school,String college,String name, String snum) {
		
		return studentDao.fuzzyStudent(school,college,name, snum);
	}

	@Override
	public Student findStudentBySnum(String snum) {
		
		return studentDao.findBySnum(snum);
	}

	@Override
	public Student findBySnumForwx(String snum) {
		
		return studentDao.findBySnumForwx(snum);
	}

	@Override
	public Integer coutStudent(String school, String college) {
		
		return studentDao.coutStudent(school, college);
	}
	
	@Override
	public Integer countMajorStudent(String school, String major) {
		
		return studentDao.countMajorStudent(school, major);
	}

}
