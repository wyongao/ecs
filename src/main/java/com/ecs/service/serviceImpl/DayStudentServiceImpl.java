package com.ecs.service.serviceImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecs.dao.DayStudentDao;
import com.ecs.domain.DayStudent;
import com.ecs.domain.Student;
import com.ecs.service.DayStudentService;

/**
 * 每日信息的实现类
 * 
 * @author xuluyang
 *
 *         2020年3月8日
 */
@Service
public class DayStudentServiceImpl implements DayStudentService {
	@Autowired
	private DayStudentDao dayStudentDao;

	@Override
	public void addDayStudent(DayStudent dayStudent) {
		
		dayStudentDao.addDayStudent(dayStudent);
		}
	//动态sql
	@Override
	public List<DayStudent> findAllDayStudents(String school,String college, String major, String classes, String snum,String date) {
		
		
		return dayStudentDao.findAllDayStudents(school,college, major, classes, snum,date);
	}

	//模糊查询
	@Override
	public List<DayStudent> fuzzyQueryDaystudents(String school,String college,String name) {
		
		return dayStudentDao.fuzzyQueryDaystudents(school,college,name);
	}

	@Override
	public List<DayStudent> findBySnumForwx(String snum) {
		
		return dayStudentDao.findBySnumForwx(snum);
	}

	@Override
	public List<DayStudent> findBySnumAndDateForwx(String snum) {
		
		return dayStudentDao.findBySnumAndDateForwx(snum);
	}
	
	@Override
	public List<DayStudent> findByDateForwx(String snum, String date) {
		
		return dayStudentDao.findByDateForwx(snum, date);
	}
	
	@Override
	public void updateDayStudentForwx(String snum, String date, String temp, String symptom, String addr) {
		
		dayStudentDao.updateDayStudentForwx(snum, date, temp, symptom, addr);
	}
	
	public Integer countDayStudent(String school,String college, String date) {
		
		return dayStudentDao.countDayStudent(school,college, date);
	}

	@Override
	public Integer countDayMajorStudent(String school, String major, String date) {
		
		return dayStudentDao.countDayMajorStudent(school, major, date);
	}


	@Override
	public void updateDayStudentInfo(String snum, String sname, String school, String college, String major,
			String classses, String snum1) {
		dayStudentDao.updateDayStudentInfo(snum, sname, school, college, major, classses, snum1);
		
	}

	@Override
	public List<DayStudent> screenDayStudents(String temp) {
		
		return dayStudentDao.screenDayStudents(temp);
	}

	@Override
	public List<Student> screenNoSignStudents(String school, String college, String major, String classes,
			String date) {
		
		return dayStudentDao.screenNoSignStudents(school, college, major, classes, date);
	}







}
