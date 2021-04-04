package com.ecs.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ecs.domain.Teacher;
/**
 * 
 * @author xuluyang
 *
 * 2020年3月4日
 */

public interface UserService {

	//修改密码
	public String changePassword(String tnum,String password);
	//登录
	public Map<String, Object> doLogin(Teacher t, HttpServletRequest request);
}
