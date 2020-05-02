package com.ecs.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecs.dao.DayStudentDao;
import com.ecs.domain.DayStudent;
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
	public List<DayStudent> findDayStudents(String date) {

		return dayStudentDao.findDayStudents(date);
	}

	@Override
	public List<DayStudent> findDayStudentByCollege(String college, String date) {

		return dayStudentDao.findDayStudentByCollege(college, date);
	}

	@Override
	public List<DayStudent> findDayStudentByMajor(String major, String date) {
		
		return dayStudentDao.findDayStudentByMajor(major, date);
	}

	@Override
	public List<DayStudent> findDayStudentByMajorAndClasses(String major, Integer classes, String date) {
		
		return dayStudentDao.findDayStudentByMajorAndClasses(major, classes, date);
	}

	@Override
	public List<DayStudent> findDayStudentBySnum(String snum) {
		
		return dayStudentDao.findDayStudentBySnum(snum);
	}
	/**
	 * 这个是面向小程序的
	 */
	@Override
	public List<String> traceStudent(String snum) {
		
		return dayStudentDao.traceStudent(snum);
	}

	@Override
	public void addDayStudent(DayStudent dayStudent) {
		
		dayStudentDao.addDayStudent(dayStudent);
		}
	//动态sql
	@Override
	public List<DayStudent> findAllDayStudents(String college, String major, String classes, String snum,String date) {
		
		
		return dayStudentDao.findAllDayStudents(college, major, classes, snum,date);
	}

	@Override
	public List<DayStudent> findAll() {
		
		return dayStudentDao.findAll();
	}
	//模糊查询
	@Override
	public List<DayStudent> fuzzyQueryDaystudents(String name) {
		
		return dayStudentDao.fuzzyQueryDaystudents(name);
	}

	@Override
	public List<DayStudent> findByCollege(String college) {
		
		return dayStudentDao.findByCollege(college);
	}

	@Override
	public List<DayStudent> findBySnumForwx(String snum) {
		
		return dayStudentDao.findBySnumForwx(snum);
	}

	@Override
	public List<DayStudent> findByDateForwx(String date) {
		
		return dayStudentDao.findByDateForwx(date);
	}
	
	public Integer countDayStudent(String school,String college, String date) {
		
		return dayStudentDao.countDayStudent(school,college, date);
	}

	@Override
	public Integer countDayMajorStudent(String school, String major, String date) {
		
		return dayStudentDao.countDayMajorStudent(school, major, date);
	}

}
