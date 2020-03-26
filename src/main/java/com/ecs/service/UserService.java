package com.ecs.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ecs.domain.Teacher;
import com.ecs.domain.User;
/**
 * 
 * @author xuluyang
 *
 * 2020年3月4日
 */

public interface UserService {
	//查找所有的用户
	public List<User> findAll();
	//添加用户
	public void addUser(User user);
	//删除用户
	public void deleteUser(Integer id);
	//修改用户
	public void updateUser(User user);
	//修改密码
	public String changePassword(String tnum,String password);
	//登录
	public Map<String, Object> doLogin(Teacher t, HttpServletRequest request);
}
