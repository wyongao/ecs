package com.ecs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecs.domain.User;
import com.ecs.service.UserService;
	/**
	 * @author xuluyang
	 *
	 * 2020年3月5日
	 */
@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	
	//查询所用的用户
	@RequestMapping(value="/findAllUser")
	@ResponseBody
	public String findAll() {
		List<User> list=userService.findAll();
		for (User user : list) {
			System.out.println("--------------->"+user);
		}
		return userService.findAll().toString();
	}
	
	//添加用户
	@RequestMapping("/addUser")
	@ResponseBody
	public String addUser(User user) {
		user.setName("abcdc");
		user.setPassWord("456789");
		user.setIdentify("2");
		userService.addUser(user);
		System.out.println("添加成功!------------>");		
		return "添加成功";
	}
	
	//删除用户
	@RequestMapping("/deleteUser")
	@ResponseBody
	public String deleteUser(Integer id) {
		id=3;
		userService.deleteUser(id);
		System.out.println("删除成功--------------->>>>");
		return "删除成功";
	}
	
	/**
	 * 
	 * 参数需要是一个整数数组
	 * @param a
	 * @return
	 */
	//批量删除
	@RequestMapping("/deleteSomeUser")
	@ResponseBody
	public String deleteSomeUser(int[] a){
		int[] b=new int[] {4,5};
		
		for(Integer i=0;i<=b.length-1;i++) {
			userService.deleteUser(b[i]);
		}
		System.out.println("批量删除成功--------------->>>>");
		return "批量删除成功";
	}
	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	@RequestMapping("/updateUser")
	@ResponseBody
	public String updateUser(User user) {
		user.setId(1);
		user.setName("许路洋");
		user.setPassWord("xly");
		user.setIdentify("1");
		userService.updateUser(user);
		System.out.println("修改成功");
		return "修改成功";
	}
	
	@RequestMapping("/changePassword")
	@ResponseBody
	public String changePassword(Integer id,String password) {
		id=1;
		password="xuluyang";
		userService.changePassword(id, password);
		System.out.println("修改成功");
		
		return "修改密码成功";
	}
}
