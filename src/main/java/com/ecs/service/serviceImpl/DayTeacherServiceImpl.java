package com.ecs.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecs.dao.DayTeacherDao;
import com.ecs.domain.DayTeacher;
import com.ecs.service.DayTeacherService;
/**
 * 老师每日疫情上报的实现类
 * @author xuluyang
 *
 * 2020年3月8日
 */
@Service
public class DayTeacherServiceImpl implements DayTeacherService {
	
	@Autowired
	private DayTeacherDao dayTeacherDao;

	//模糊查询
	@Override
	public List<DayTeacher> fuzzyDayTeachers(String tname,String college,String school) {
		
		return dayTeacherDao.fuzzyDayTeachers(tname,college,school);
	}
	//动态查询
	@Override
	public List<DayTeacher> dynamicDayTeachers(String school,String college, String tnum) {
		
		return dayTeacherDao.dynamicDayTeachers(school,college, tnum);
	}

	@Override
	public void addDayTeacher(DayTeacher dayTeacher) {
		
		dayTeacherDao.addDayTeacher(dayTeacher);
	}

	@Override
	public List<DayTeacher> findByTnumForwx(String tnum) {
		
		return dayTeacherDao.findByTnumForwx(tnum);
	}

	@Override
	public List<DayTeacher> findByTnumAndDateForwx(String tnum) {

		return dayTeacherDao.findBySnumAndDateForwx(tnum);
	}
	
	@Override
	public List<DayTeacher> findByDateForwx(String tnum, String date) {
		
		return dayTeacherDao.findByDateForwx(tnum, date);
	}
	
	@Override
	public void updateDayTeacherForwx(String tnum, String date, String temp, String symptom, String addr) {
		
		dayTeacherDao.updateDayTeacherForwx(tnum, date, temp, symptom, addr);
	}
	
	public Integer countDayTeachers(String school,String college, String date) {

		return dayTeacherDao.countDayTeachers(school,college, date);
	}

	@Override
	public List<DayTeacher> findWithParam(String school, String college, String date) {
	
		return dayTeacherDao.findWithParam(school, college, date);
	}
	

}
