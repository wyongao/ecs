package com.ecs.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecs.dao.ApplicationDao;
import com.ecs.dao.DayStudentDao;
import com.ecs.dao.StudentDao;
import com.ecs.dao.TrackStudentDao;
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
	@Autowired
	private DayStudentDao dayStudentDao;
	@Autowired
	private ApplicationDao applicationDao;
	@Autowired
	private TrackStudentDao trackStudentDao;

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
		Integer id=student.getId();
		//找到数据库中未修改之前的学号
		Student oldStudent=studentDao.findStudentById(id);
		//修改所有的出入申请
		applicationDao.updateApplicationInfo(student.getSnum(), student.getSname(), student.getSchool(), student.getCollege(), student.getMajor(), student.getClasses(),oldStudent.getSnum());
		//修改所有的健康申报
		dayStudentDao.updateDayStudentInfo(student.getSnum(), student.getSname(), student.getSchool(), student.getCollege(), student.getMajor(), student.getClasses(),oldStudent.getSnum());
		//修改轨迹里面的信息
		trackStudentDao.updateTrackStudentInfo(student.getSnum(), student.getSname(), student.getSchool(), student.getCollege(), student.getMajor(), student.getClasses(),oldStudent.getSnum());
		//修改学生的基本信息		
		studentDao.changeStudentInfo(student);
		
		return "success";
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
