package com.ecs.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecs.domain.Teacher;
import com.ecs.service.TeacherService;
import com.ecs.service.UserService;

/**
 * @author xuluyang
 *
 *         2020年3月5日
 */
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TeacherService teacherService;
	
	// 添加用户
	@ResponseBody
	@RequestMapping("/addUser")
	public String addUser(Teacher t) {
		
		teacherService.addTeacher(t);

		return "success";
	}

	
	// 修改密码
	@ResponseBody
	@RequestMapping("/changePassword")
	public String changePassword(String username, String password) {

		String msg = userService.changePassword(username, password);
		
		return msg;
	}

	// 进入登录页面
	@RequestMapping("/login")
	public String start() {
		System.out.println("进入登陆页面");
		return "login";
	}

	// 进入访问记录页面
	@RequestMapping("/goAccessData")
	public String goAccessData(String tnum, Map<String, Object> model) {
	
		Teacher t = teacherService.findByTnumForweb(tnum);
		
		if(t.getIdentify().equals("1")) {
			
			return "error/403";
		}
		
		model.put("teacher", t);
		return "accessData";
	}

	// 进入添加用户页面
	@RequestMapping("/goAddUser")
	public String goAddUser(String tnum, Map<String, Object> model) {

		Teacher t = teacherService.findByTnumForweb(tnum);
		
		if(t.getIdentify().equals("1")) {
			
			return "error/403";
		}
		
		model.put("teacher", t);
		return "addUser";
	}

	// 进入学生数据页面
	@RequestMapping("/goBaseData")
	public String goBaseData(String tnum, Map<String, Object> model) {
		
		Teacher t = teacherService.findByTnumForweb(tnum);
		
		model.put("teacher", t);
		return "baseData";
	}

	// 进入学生打卡数据页面
	@RequestMapping("/goDailyData")
	public String goDailyData(String tnum, Map<String, Object> model) {
		
		Teacher t = teacherService.findByTnumForweb(tnum);
        
        model.put("teacher", t);
		return "dailyData";
	}
	
	// 进入老师打卡数据页面
	@RequestMapping("/goDailyTeacherData")
	public String goDailyTeacherData(String tnum, Map<String, Object> model) {
		
		Teacher t = teacherService.findByTnumForweb(tnum);
	        
	    model.put("teacher", t);
	    return "dailyTeacherData";
	}

	// 进入数据报表页面
	@RequestMapping("/goEcharts")
	public String goEcharts(String tnum, Map<String, Object> model) {
			
		Teacher t = teacherService.findByTnumForweb(tnum);
		        
		model.put("teacher", t);
		return "echarts";
	}
		
	// 进入数据导入页面
	@RequestMapping("/goDataImport")
	public String goDataImport(String tnum, Map<String, Object> model) {
			
		Teacher t = teacherService.findByTnumForweb(tnum);
		
		model.put("teacher", t);
		return "dataImport";
	}
	
	// 进入入校申请页面
	@RequestMapping("/goIn")
	public String goIn(String tnum, Map<String, Object> model) {
		
		Teacher t = teacherService.findByTnumForweb(tnum);
		
		model.put("teacher", t);
		return "in";
	}

	// 进入出校申请页面
	@RequestMapping("/goOut")
	public String goOut(String tnum, Map<String, Object> model) {

		Teacher t = teacherService.findByTnumForweb(tnum);
		
		model.put("teacher", t);
		return "out";
	}

	// 进入用户（老师）数据页面
	@RequestMapping("/goUserData")
	public String goUserData(String tnum, Map<String, Object> model) {
		
		Teacher t = teacherService.findByTnumForweb(tnum);
		
		if(t.getIdentify().equals("1")) {
			
			return "error/403";
		}

		model.put("teacher", t);
		return "userData";
	}
	
	// 进入轨迹查询页面
	@RequestMapping("/goTrack")
	public String goTrack(String tnum, Map<String, Object> model) {
			
		Teacher t = teacherService.findByTnumForweb(tnum);
	        
	    model.put("teacher", t);
	    return "track";
	}

	// 实现登录,并保存登陆记录
	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public String doLogin(Teacher t, Map<String, Object> model, HttpServletRequest request) {
		
		Map<String, Object> map = userService.doLogin(t, request);
		if (map.get("msg").equals("failure")) {
			
			model.put("msg", map.get("msg"));
			model.put("desc", map.get("desc"));
			return "login";
		}
			
		Teacher teacher = teacherService.findByTnumForweb(t.getTnum());
		
		model.put("msg", map.get("msg"));
		model.put("teacher", teacher);
//		model.put("teacher", map.get("teacher"));
		return "dailyData";
	}

}
