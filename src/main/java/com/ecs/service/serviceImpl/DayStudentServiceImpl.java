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

}
