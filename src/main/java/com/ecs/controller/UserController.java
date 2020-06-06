package com.ecs.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecs.constant.Constant;
import com.ecs.domain.Teacher;
import com.ecs.domain.User;
import com.ecs.service.CollegeService;
import com.ecs.service.MajorService;
import com.ecs.service.TeacherService;
import com.ecs.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author xuluyang
 *
 *         2020年3月5日
 */
@Controller
public class UserController {
	
	@Autowired
	private CollegeService collegeService;
	
	@Autowired
	private MajorService majorService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TeacherService teacherService;

	// 查询所用的用户
	@RequestMapping(value = "/findAllUser")
	@ResponseBody
	public String findAll(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
		// pageNum是当前页,
		// 2是一夜的数量
		PageHelper.startPage(pageNum, Constant.PAGE_SIZE);
		List<User> list = userService.findAll();
		// 这里少个参数目前没有影响
		PageInfo<User> pageInfo = new PageInfo<User>(list);
		System.out.println("总页数" + pageInfo.getPages() + "当前页" + pageInfo.getPageNum() + "总记录数" + pageInfo.getTotal());
		for (User user : list) {
			System.out.println("--------------->" + user);
		}
		return userService.findAll().toString();
	}

	
	// 删除用户
	@RequestMapping("/deleteUser")
	@ResponseBody
	public String deleteUser(Integer id) {
		id = 3;
		userService.deleteUser(id);
		System.out.println("删除成功--------------->>>>");
		return "删除成功";
	}

	/**
	 * 
	 * 参数需要是一个整数数组
	 * 
	 * @param a
	 * @return
	 */

	// 批量删除
	@RequestMapping("/deleteSomeUser")
	@ResponseBody
	public String deleteSomeUser(int[] a) {
		int[] b = new int[] { 4, 5 };
		for (Integer i = 0; i <= b.length - 1; i++) {
			userService.deleteUser(b[i]);
		}
		System.out.println("批量删除成功--------------->>>>");
		return "批量删除成功";
	}

	/**
	 * 修改用户
	 * 
	 * @param user
	 * @return
	 */

	@RequestMapping("/updateUser")
	@ResponseBody
	public String updateUser(User user) {
		user.setId(1);
		user.setUsername("许路洋");
		user.setPassword("xly");
		user.setIdentify("1");
		userService.updateUser(user);
		System.out.println("修改成功");
		return "修改成功";
	}

	
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

	// 进入基础数据页面
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

	// 进入用户数据页面
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
	
	
	// 进入测试页面
		@RequestMapping("/test")
		public String goTest() {

			return "test";
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
