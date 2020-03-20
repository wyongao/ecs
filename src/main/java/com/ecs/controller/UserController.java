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

import com.ecs.common.IpUtil;
import com.ecs.constant.Constant;
import com.ecs.domain.Teacher;
import com.ecs.domain.User;
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
	private UserService userService;

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

	// 添加用户
	@RequestMapping("/addUser")
	@ResponseBody
	public String addUser(User user) {
		user.setUsername("abcdc");
		user.setPassword("456789");
		user.setIdentify("2");
		userService.addUser(user);
		System.out.println("添加成功!------------>");
		return "添加成功";
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

	// 修改密码
	@RequestMapping("/changePassword")
	@ResponseBody
	public String changePassword(Integer id, String password) {
		id = 1;
		password = "xuluyang";
		userService.changePassword(id, password);
		System.out.println("修改成功");
		return "修改密码成功";
	}

	// 进入登录页面
	@RequestMapping("/login")
	public String start() {
		System.out.println("进入登陆页面");
		return "login";
	}

	// 进入访问记录页面
	@RequestMapping("/goAccessData")
	public String goAccessData() {

		return "accessData";
	}

	// 进入添加用户页面
	@RequestMapping("/goAddUser")
	public String goAddUser() {

		return "addUser";
	}

	// 进入基础数据页面
	@RequestMapping("/goBaseData")
	public String goBaseData() {

		return "baseData";
	}

	// 进入打卡数据页面
	@RequestMapping("/goDailyData")
	public String goDailyData() {

		return "dailyData";
	}

	// 进入入校申请页面
	@RequestMapping("/goIn")
	public String goIn() {

		return "in";
	}

	// 进入出校申请页面
	@RequestMapping("/goOut")
	public String goOut() {

		return "out";
	}

	// 进入用户数据页面
	@RequestMapping("/goUserData")
	public String goUserData() {

		return "userData";
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
			
			return "login";
		}
		
		model.put("teacher", map.get("teacher"));
		model.put("msg", map.get("msg"));
		return "dailyData";
	}

}
