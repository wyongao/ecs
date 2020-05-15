package com.ecs.WXController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecs.common.EncryptUtil;
import com.ecs.common.HttpClientUtil;
import com.ecs.common.JsonUtils;
import com.ecs.common.MyParams;
import com.ecs.constant.IdentityConstant;
import com.ecs.domain.WXSession;
import com.ecs.service.ApplicationService;
import com.ecs.service.DayStudentService;
import com.ecs.service.DayTeacherService;
import com.ecs.service.StudentService;
import com.ecs.service.TeacherService;
import com.ecs.service.TrackStudentService;
import com.ecs.service.TrackTeacherService;
import com.ecs.service.WXService;

@Controller
public class WXLoginController {

	@Autowired
	private StringRedisTemplate redis1StringRedisTemplate;

	@Autowired
	private MyParams myParams;
	
	private EncryptUtil encryptUtil;

	@Autowired
	private WXService wxService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private DayStudentService dayStudentService;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private DayTeacherService dayTeacherService;
	
	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private TrackStudentService trackStudentService;
	
	@Autowired
	private TrackTeacherService trackTeacherService;
	
	@GetMapping("redis")
	@ResponseBody
	@RequestMapping(value = "/wxLogin", method=RequestMethod.POST)
	public Map<String, Object> wxLogin(String code, String usernum) {
		
		Map<String, Object> res = new HashMap<String, Object>();
		String identity = IdentityConstant.IDENTITY_STUDENT;
		
		if(studentService.findStudentBySnum(usernum) == null) {
			
			 if(teacherService.findTeacherByTnum(usernum) == null) {
				res.put("msg", "failure");
				return res;
			}
		}
		
		if(studentService.findStudentBySnum(usernum) != null) {//学生为1
			
//			identity = IdentityConstant.IDENTITY_STUDENT;	
		}else if(teacherService.findTeacherByTnum(usernum) != null) {//老师为2

			identity = IdentityConstant.IDENTITY_TEACHER;
		}
		
//		https://api.weixin.qq.com/sns/jscode2session?
//		appid=APPID&
//		secret=SECRET&
//		js_code=JSCODE&
//		grant_type=authorization_code

		String url = "https://api.weixin.qq.com/sns/jscode2session";
		Map<String, String> param = new HashMap<>();
		String appid = myParams.getAppid();
		String secret = myParams.getSecret();

		param.put("appid", appid);
		param.put("secret", secret);
		param.put("js_code", code);
		param.put("grant_type", "authorization_code");

		String wxResult = HttpClientUtil.doGet(url, param);
//		System.out.println(wxResult);

		WXSession wxSession = JsonUtils.jsonToPoJo(wxResult, WXSession.class);
//		System.out.println(wxSession.getOpenid());
//		System.out.println(wxSession.getSession_key());
		
		String sessionId = encryptUtil.encodeMD5Hex(wxSession.getOpenid());
		String value = wxSession.getOpenid()+"&"+wxSession.getSession_key();
//		System.out.println(sessionId);
//		System.out.println(value);
		
		//参数: key,value,时间,时间单位（默认毫秒）
		redis1StringRedisTemplate.opsForValue().set(sessionId, value, 1, TimeUnit.DAYS);
//		System.out.println(redis1StringRedisTemplate.opsForValue().get(sessionId));

		wxService.setOpenid(usernum, wxSession.getOpenid(), identity);

		
		res.put("msg", "success");
		res.put("sessionId", sessionId);
		res.put("identity", identity);
		
		return res;
	}
	
	//检查登录状态
	//usernum 用于删除openid
	@GetMapping("redis")
	@ResponseBody
	@RequestMapping(value = "/wxLoginCheck", method=RequestMethod.POST)
	public String wxLoginCheck(String sessionid, String usernum) {
//		System.out.println(usernum);
//		System.out.println(wxService.searchSessionId(sessionid));
		if(redis1StringRedisTemplate.hasKey(sessionid)) {
			return "success";
		}else {
			
			return "failure";
		}		
//		return wxService.searchSessionId(sessionid);
	}
	
	@ResponseBody
	@RequestMapping(value="/getUserInfo", method=RequestMethod.POST)
	public String getStudentInfo(String usernum, String identity) {

		if(identity.equals(IdentityConstant.IDENTITY_STUDENT)) {
			return JsonUtils.objectToJson(studentService.findBySnumForwx(usernum));
		}else {
			return JsonUtils.objectToJson(teacherService.findByTnumForwx(usernum));
		}
	
	}
	
	@ResponseBody
	@RequestMapping(value="/getDailyRecord", method=RequestMethod.POST)
	public String getDailyRecord(String usernum, String identity) {
		
		if(identity.equals(IdentityConstant.IDENTITY_STUDENT)) {
			return JsonUtils.objectToJson(dayStudentService.findBySnumForwx(usernum));
		} else {
			return JsonUtils.objectToJson(dayTeacherService.findByTnumForwx(usernum));
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value="/getOutInRecord", method=RequestMethod.POST)
	public String getOutInRecord(String usernum, String identity) {//可根据identity区别老师和学生

		return JsonUtils.objectToJson(applicationService.findBySnumForwx(usernum));
	}
	
	
	@ResponseBody
	@RequestMapping(value="/daily", method=RequestMethod.POST)
	public String daily (String temp, String symptom, String addr, String usernum, String identity) {
		
		if(identity.equals(IdentityConstant.IDENTITY_STUDENT)) {
			return wxService.addDayStudentFromwx(temp, symptom, addr, usernum);
		} else {
			return wxService.addDayTeacherFromwx(temp, symptom, addr, usernum);
		}
				
	}
	
	@ResponseBody
	@RequestMapping(value="/outIn", method=RequestMethod.POST)
	public String outIn (String inout, String dest, String reason, String exit, String usernum, String identity) {//可根据identity区别老师和学生
		
		return wxService.addApplicationFromwx(inout, dest, reason, exit, usernum);
	}
	
	@ResponseBody
	@RequestMapping(value="/track", method=RequestMethod.POST)
	public String track (String addr, String usernum, String identity) {
		
		if(identity.equals(IdentityConstant.IDENTITY_STUDENT)) {
			return wxService.addTrackStudentFromwx(addr, usernum);
		} else {
			return wxService.addTrackTeacherFromwx(addr, usernum);
		}		
	}
	
	@ResponseBody
	@RequestMapping(value="/getTrackRecord", method=RequestMethod.POST)
	public String getTrackRecord (String usernum, String identity) {
		
		if(identity.equals(IdentityConstant.IDENTITY_STUDENT)) {
			return trackStudentService.findTrackStudentForwx(usernum);
		} else {
			return trackTeacherService.findTrackTeacherForwx(usernum);
		}		
	}
	
	@ResponseBody
	@RequestMapping(value="/getAddr", method=RequestMethod.POST)
	public String getAddr() {
		
//		System.out.println(wxService.findBuildingsForwx());
		return wxService.findBuildingsForwx();
	}
	
	@ResponseBody
	@RequestMapping(value="/getHealthCode", method=RequestMethod.POST)
	public String getHealthCode(String usernum, String identity) {
		
		return wxService.getHealthCodeForwx(usernum, identity);
	}
	
	@ResponseBody
	@RequestMapping(value="/healthCode")
	public String healthCode(String msg) {
		
		if(msg.equals("success")) {
			return "健康状况良好，近七天健康申报体温均低于37.5°C。";
		} else {
			return "近七天健康申报体温存在大于等于37.5°C！";
		}
		
	}
	
}
