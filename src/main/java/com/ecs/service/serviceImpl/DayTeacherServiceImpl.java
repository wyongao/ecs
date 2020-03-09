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

	@Override
	public List<DayTeacher> findDayTeachers(String date) {
		return dayTeacherDao.findDayTeachers(date);
	}

	@Override
	public List<DayTeacher> findDayTeacherByCollege(String college, String date) {
		
		return dayTeacherDao.findDayTeacherByCollege(college, date);
	}

	@Override
	public List<DayTeacher> findDayTeacherByTnum(String tnum) {
		
		return dayTeacherDao.findDayTeacherByTnum(tnum);
	}

	@Override
	public List<String> traceTeacher(String tnum) {
		
		return dayTeacherDao.traceTeacher(tnum);
	}
	

}
