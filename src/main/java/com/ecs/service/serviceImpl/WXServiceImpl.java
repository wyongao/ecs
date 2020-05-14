package com.ecs.service.serviceImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecs.common.JsonUtils;
import com.ecs.constant.ApplicationConstant;
import com.ecs.dao.BuildingDao;
import com.ecs.dao.CampusDao;
import com.ecs.dao.StudentDao;
import com.ecs.dao.TeacherDao;
import com.ecs.domain.Application;
import com.ecs.domain.Building;
import com.ecs.domain.Campus;
import com.ecs.domain.DayStudent;
import com.ecs.domain.DayTeacher;
import com.ecs.domain.Student;
import com.ecs.domain.Teacher;
import com.ecs.domain.TrackStudent;
import com.ecs.domain.TrackTeacher;
import com.ecs.service.ApplicationService;
import com.ecs.service.DayStudentService;
import com.ecs.service.DayTeacherService;
import com.ecs.service.TrackStudentService;
import com.ecs.service.TrackTeacherService;
import com.ecs.service.WXService;

@Service
public class WXServiceImpl implements WXService {

//	@Autowired
//	private StringRedisTemplate redis1StringRedisTemplate;

	@Autowired
	private CampusDao campusDao;
	
	@Autowired
	private BuildingDao buildingDao;
	
	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private TeacherDao teacherDao;
	
	@Autowired
	private DayStudentService dayStudentService;
	
	@Autowired
	private DayTeacherService dayTeacherService;
	
	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private TrackStudentService trackStudentService;
	
	@Autowired
	private TrackTeacherService trackTeacherService;
	
	@Override
	public String setOpenid(String usernum, String openid, String identity) {
		
		if(identity.equals("1")) {
			studentDao.setOpenidBySnum(usernum, openid);
		} else {
			teacherDao.setOpenidByTnum(usernum, openid);
		}
				
		return "success";
	
	}

	@Override
	public String addDayStudentFromwx(String temp, String symptom, String addr, String snum) {
		
		Student s = studentDao.findBySnum(snum);	
		DayStudent d = new DayStudent();
		
		Date now = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		
		if(dayStudentService.findByDateForwx(f.format(now)).isEmpty()) {			
			
			d.setSnum(snum);
			d.setSname(s.getSname());
			d.setSchool(s.getSchool());
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
		
		return "failure";
	}

	@Override
	public String addDayTeacherFromwx(String temp, String symptom, String addr, String tnum) {
		
		Teacher t = teacherDao.findTeacherByTnum(tnum);	
		DayTeacher d = new DayTeacher();
		
		Date now = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		
		if(dayTeacherService.findByDateForwx(f.format(now)).isEmpty()) {			
			
			d.setTnum(tnum);
			d.setTname(t.getTname());
			d.setSchool(t.getSchool());
			d.setCollege(t.getCollege());
			d.setAddr(addr);
			d.setDate(f.format(now));
			d.setSymptom(symptom);
	        d.setTemp(temp);	
	        
	        dayTeacherService.addDayTeacher(d);
	        
			return "success";
			
		}
		
		return "failure";
	}
	
	
	@Override
	public String addApplicationFromwx(String inout, String dest, String reason, String exit, String snum) {
		
		if(applicationService.findBySnumAndStatusForwx(snum, ApplicationConstant.STATUS_CHECK_PENDING).isEmpty()) {
			
			Student s = studentDao.findBySnum(snum);
			Application a = new Application();
			
			Date now = new Date();
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
			
			if(inout.equals("0")) {
				inout = "1";
			}else {
				inout = "2";
			}
			
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
		
		return "failure";
	}

	@Override
	public String findBuildingsForwx() {
		
//		ArrayList<ArrayList<String>> all= new ArrayList<ArrayList<String>>();
		
		Map<String, Object> all = new HashMap<String, Object>(); 
		ArrayList<String> campus = campusDao.findCampusByParentIdForwx(1);
//		all.add(campus);
		all.put("campus", campus);
		
		for(int i=1; i<=campus.size(); i++) {
			
//			all.add(buildingDao.findBuildingByParentIdForwx(i));
			all.put("building"+i, buildingDao.findBuildingByParentIdForwx(i));
		}
				
		return JsonUtils.objectToJson(all);
	}

	@Override
	public String addTrackStudentFromwx(String addr, String snum) {

		Student s = studentDao.findBySnum(snum);	
		TrackStudent ts = new TrackStudent();
		
		Date now = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		
		ts.setSnum(snum);
		ts.setSname(s.getSname());
		ts.setSchool(s.getSchool());
		ts.setCollege(s.getCollege());
		ts.setMajor(s.getMajor());
		ts.setClasses(s.getClasses());
		ts.setAddr(addr);
		ts.setDate(f.format(now));	
        
		trackStudentService.addTrackStudent(ts);
        
		return "success";
	}

	@Override
	public String addTrackTeacherFromwx(String addr, String tnum) {
		
		Teacher t = teacherDao.findTeacherByTnum(tnum);	
		TrackTeacher tt = new TrackTeacher();
		
		Date now = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

		tt.setTnum(tnum);
		tt.setTname(t.getTname());
		tt.setSchool(t.getSchool());
		tt.setCollege(t.getCollege());
		tt.setAddr(addr);
		tt.setDate(f.format(now));	
        
		trackTeacherService.addTrackTeacher(tt);
        
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
