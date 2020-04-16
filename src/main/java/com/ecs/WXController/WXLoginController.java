package com.ecs.WXController;

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
import com.ecs.domain.WXSession;
import com.ecs.service.ApplicationService;
import com.ecs.service.DayStudentService;
import com.ecs.service.StudentService;
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
	private ApplicationService applicationService;
	
	@GetMapping("redis")
	@ResponseBody
	@RequestMapping(value = "/wxLogin", method=RequestMethod.POST)
	public Map<String, Object> wxLogin(String code, String snum) {
		
		Map<String, Object> res = new HashMap<String, Object>();
		
		if(studentService.findStudentBySnum(snum) == null) {
			res.put("msg", "failure");
			return res;
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

		wxService.setOpenid(snum, wxSession.getOpenid());

		res.put("msg", "success");
		res.put("sessionId", sessionId);
		
		
		return res;
	}
	
	//检查登录状态
	@GetMapping("redis")
	@ResponseBody
	@RequestMapping(value = "/wxLoginCheck", method=RequestMethod.POST)
	public String wxLoginCheck(String sessionid) {
//		System.out.println(sessionid);
//		System.out.println(wxService.searchSessionId(sessionid));
		if(redis1StringRedisTemplate.hasKey(sessionid)) {
			return "success";
		}else {
			return "failure";
		}		
//		return wxService.searchSessionId(sessionid);
	}
	
	@ResponseBody
	@RequestMapping(value="/getStudentInfo", method=RequestMethod.POST)
	public String getStudentInfo(String snum) {

		return JsonUtils.objectToJson(studentService.findBySnumForwx(snum));
	}
	
	@ResponseBody
	@RequestMapping(value="/getDailyRecord", method=RequestMethod.POST)
	public String getDailyRecord(String snum) {
		
		return JsonUtils.objectToJson(dayStudentService.findBySnumForwx(snum));
	}
	
	@ResponseBody
	@RequestMapping(value="/getOutInRecord", method=RequestMethod.POST)
	public String getOutInRecord(String snum) {

		return JsonUtils.objectToJson(applicationService.findBySnumForwx(snum));
	}
	
	
	@ResponseBody
	@RequestMapping(value="/daily", method=RequestMethod.POST)
	String daily (String temp, String symptom, String addr, String snum) {
		
		return wxService.addDayStudentFromwx(temp, symptom, addr, snum);
	}
	
	@ResponseBody
	@RequestMapping(value="/outIn", method=RequestMethod.POST)
	String daily (String inout, String dest, String reason, String exit, String snum) {
		
		return wxService.addApplicationFromwx(inout, dest, reason, exit, snum);
	}
	
	
	
	
	
}
