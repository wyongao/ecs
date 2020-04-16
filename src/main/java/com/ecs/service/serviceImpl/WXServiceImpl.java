package com.ecs.service.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecs.constant.ApplicationConstant;
import com.ecs.dao.StudentDao;
import com.ecs.domain.Application;
import com.ecs.domain.DayStudent;
import com.ecs.domain.Student;
import com.ecs.service.ApplicationService;
import com.ecs.service.DayStudentService;
import com.ecs.service.WXService;

@Service
public class WXServiceImpl implements WXService {

//	@Autowired
//	private StringRedisTemplate redis1StringRedisTemplate;

	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private DayStudentService dayStudentService;
	
	@Autowired
	private ApplicationService applicationService;
	
	@Override
	public String setOpenid(String snum, String openid) {
		
		studentDao.setOpenidBySnum(snum, openid);
		return "success";
	
	}

	@Override
	public String addDayStudentFromwx(String temp, String symptom, String addr, String snum) {
		
		Student s = studentDao.findBySnum(snum);	
		DayStudent d = new DayStudent();
		
		Date now = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		d.setSnum(snum);
		d.setSname(s.getSname());
		d.setCollege(s.getCollege());
		d.setMajor(s.getMajor());
		d.setClasses(s.getClasses());
		d.setAddr(addr);
		d.setDate(f.format(now));
		d.setSymptom(symptom);
        d.setTemp(temp);	
        
        dayStudentService.addDayStudent(d);
        
		return "success";
	}

	@Override
	public String addApplicationFromwx(String inout, String dest, String reason, String exit, String snum) {
		
		Student s = studentDao.findBySnum(snum);
		Application a = new Application();
		
		Date now = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		a.setSnum(snum);
		a.setSname(s.getSname());
		a.setDate(f.format(now));
		a.setExit(exit);
		a.setDest(dest);
		a.setReason(reason);
		a.setInout(inout);
		a.setStatus(ApplicationConstant.STATUS_CHECK_PENDING);
		a.setSchool(s.getSchool());
		a.setCollege(s.getCollege());
		a.setMajor(s.getMajor());
		a.setClasses(s.getClasses());
		
		applicationService.addApplicationInfo(a);
		
		return "success";
	}
	
//	//判断sessionid是否过期
//	@Override
//	@GetMapping("redis")
//	public String searchSessionId(String sessionid) {
//		System.out.println(sessionid);
//		if(redis1StringRedisTemplate.hasKey(sessionid)) {
//			System.out.println("111");
//			return "success";
//		}else {
//			System.out.println("222");
//			return "failure";
//		}		
//	}

}
