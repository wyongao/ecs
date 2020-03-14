package com.ecs.service;

import java.util.List;

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
	public void changePassword(Integer id,String passwrod);
	//登录
	public String doLogin(User u);
}
