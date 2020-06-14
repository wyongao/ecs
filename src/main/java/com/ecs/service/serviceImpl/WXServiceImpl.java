package com.ecs.service.serviceImpl;

import java.text.ParseException;
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
import com.ecs.constant.Constant;
import com.ecs.constant.IdentityConstant;
import com.ecs.dao.BuildingDao;
import com.ecs.dao.CampusDao;
import com.ecs.dao.SchoolDao;
import com.ecs.dao.StudentDao;
import com.ecs.dao.TeacherDao;
import com.ecs.domain.Application;
import com.ecs.domain.DayStudent;
import com.ecs.domain.DayTeacher;
import com.ecs.domain.School;
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
	private SchoolDao schoolDao;
	
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
			
			if(studentDao.findBySnum(usernum).getOpenid() == null || studentDao.findBySnum(usernum).getOpenid().equals("")) {
				studentDao.setOpenidBySnum(usernum, openid);
			} else {
				if(studentDao.findBySnum(usernum).getOpenid().equals(openid)) {
					return "success";
				}else {
					return "failure";	//微信账号变了，无法存入openid
				}
			}
			
		} else {
			
			if(teacherDao.findTeacherByTnum(usernum).getOpenid() == null || teacherDao.findTeacherByTnum(usernum).getOpenid().equals("")) {
				teacherDao.setOpenidByTnum(usernum, openid);
			} else {
				if(teacherDao.findTeacherByTnum(usernum).getOpenid().equals(openid)) {
					return "success";
				}else {
					return "failure";	//微信账号变了，无法存入openid
				}
			}
			
		}
				
		return "success";
	
	}

	@Override
	public String addDayStudentFromwx(String temp, String symptom, String addr, String snum) {
			
		Date now = new Date();
		SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat f2 = new SimpleDateFormat("HH:mm:ss");
		
		try {
			Date deadLine = f2.parse("12:00:00");	//截止时间
			
			if(f2.parse(f2.format(now)).getTime() >= deadLine.getTime()) {
				
//				System.out.println(f2.parse(f2.format(now)).getTime());
//				System.out.println(deadLine.getTime());
				return "failure";
			}else {
				
				if(dayStudentService.findByDateForwx(snum, f1.format(now)).isEmpty()) {
					Student s = studentDao.findBySnum(snum);	
				    DayStudent d = new DayStudent();
				
					d.setSnum(snum);
					d.setSname(s.getSname());
					d.setSchool(s.getSchool());
					d.setCollege(s.getCollege());
					d.setMajor(s.getMajor());
					d.setClasses(s.getClasses());
					d.setAddr(addr);
					d.setDate(f1.format(now));
					d.setSymptom(symptom);
			        d.setTemp(temp);	
			        
			        dayStudentService.addDayStudent(d);
				}else {
					
					dayStudentService.updateDayStudentForwx(snum, f1.format(now), temp, symptom, addr);
				}
				
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	
			return "success";	
	}

	@Override
	public String addDayTeacherFromwx(String temp, String symptom, String addr, String tnum) {
		
		Date now = new Date();
		SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat f2 = new SimpleDateFormat("HH:mm:ss");
		
		try {
			Date deadLine = f2.parse("12:00:00");	//截止时间
			
			if(f2.parse(f2.format(now)).getTime() >= deadLine.getTime()) {
				
//				System.out.println(f2.parse(f2.format(now)).getTime());
//				System.out.println(deadLine.getTime());
				return "failure";
			}else {
				
				if(dayTeacherService.findByDateForwx(tnum, f1.format(now)).isEmpty()) {			
					
					Teacher t = teacherDao.findTeacherByTnum(tnum);	
					DayTeacher d = new DayTeacher();
					
					d.setTnum(tnum);
					d.setTname(t.getTname());
					d.setSchool(t.getSchool());
					d.setCollege(t.getCollege());
					d.setAddr(addr);
					d.setDate(f1.format(now));
					d.setSymptom(symptom);
			        d.setTemp(temp);	
			        
			        dayTeacherService.addDayTeacher(d);
					
				}else {
					
					dayTeacherService.updateDayTeacherForwx(tnum, f1.format(now), temp, symptom, addr);
				}
				
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	
			return "success";
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
	public String findBuildingsForwx(String usernum, String identity) {//获取地点信息，及打卡情况
		
		//获取地点信息
		String schoolName;
		
		if(identity.equals(IdentityConstant.IDENTITY_STUDENT)) {
			
			schoolName = studentDao.findSchoolBySnumForwx(usernum);
		} else {
			
			schoolName = teacherDao.findSchoolByTnumForwx(usernum);
		}
		
		School school = schoolDao.findSchoolByName(schoolName);
		
		Map<String, Object> all = new HashMap<String, Object>(); 
		ArrayList<String> campus = campusDao.findCampusByParentIdForwx(school.getId());

		all.put("campus", campus);
		
		for(int i=0; i<campus.size(); i++) {
			
			all.put("building"+i, buildingDao.findBuildingByParentIdForwx(campusDao.findCampusId(campus.get(i), school.getId())));
		}
				
		
		
		//打卡情况
		Date now = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		Object daily;
		
		if(identity.equals(IdentityConstant.IDENTITY_STUDENT)) {
			
			daily = dayStudentService.findByDateForwx(usernum, f.format(now));
			
		} else {
			
			daily = dayTeacherService.findByDateForwx(usernum, f.format(now));
			
		}
		
		
		all.put("daily", daily);
		
		return JsonUtils.objectToJson(all);
	}

	@Override
	public String addTrackStudentFromwx(String addr, String snum) {

		Student s = studentDao.findBySnum(snum);	
		TrackStudent ts = new TrackStudent();
		
		Date now = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
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
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		tt.setTnum(tnum);
		tt.setTname(t.getTname());
		tt.setSchool(t.getSchool());
		tt.setCollege(t.getCollege());
		tt.setAddr(addr);
		tt.setDate(f.format(now));	
        
		trackTeacherService.addTrackTeacher(tt);
        
		return "success";
	}

	@Override
	public String getHealthCodeForwx(String usernum, String identity) {
		
		String msg = "success";//表示近七天健康申报体温均低于37.5°C
		
		if(identity.equals(IdentityConstant.IDENTITY_STUDENT)) {
			
			List<DayStudent> ds = dayStudentService.findBySnumAndDateForwx(usernum);
			
			if(ds.size() == 0) {
				
				msg = "none";//还没有打过卡
			}
			
			for(int i=0; i<ds.size(); i++) {			
				if(Double.parseDouble(ds.get(i).getTemp()) >= Constant.TEMP_USUAL) {
					
					msg = "failure";//表示近七天健康申报体温存在大于等于37.5°C
					break;
				}	
			}
			
			return msg;
		} else {
			List<DayTeacher> dt = dayTeacherService.findByTnumAndDateForwx(usernum);
			
			if(dt.size() == 0) {
				
				msg = "none";//还没有打过卡
			}
			
			for(int i=0; i<dt.size(); i++) {			
				if(Double.parseDouble(dt.get(i).getTemp()) >= Constant.TEMP_USUAL) {
					
					msg = "failure";//表示近七天健康申报体温存在大于等于37.5°C
					break;
				}	
			}
			
			return msg;
		}
		
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
