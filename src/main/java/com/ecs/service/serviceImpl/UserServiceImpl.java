package com.ecs.service.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecs.common.IpUtil;
import com.ecs.dao.TeacherDao;
import com.ecs.dao.UserDao;
import com.ecs.domain.Teacher;
import com.ecs.domain.User;
import com.ecs.service.AccessDataService;
import com.ecs.service.UserService;

/**
 * 
 * @author xuluyang
 *
 *         2020年3月5日
 */
@Service
public class UserServiceImpl implements UserService {
	
	IpUtil ipUtil;
	
	@Autowired
	AccessDataService accessDataService;
	
	@Autowired
	private TeacherDao teacherDao;

	@Autowired
	private UserDao userDao;
	
	// 查找所有的用戶
	@Override
	public List<User> findAll() {

		return userDao.findAll();
	}

	// 添加用户
	@Override
	public void addUser(User user) {
		userDao.addUser(user);
	}

	// 删除用户
	@Override
	public void deleteUser(Integer id) {

		userDao.deleteUser(id);
	}

	// 修改用户
	@Override
	public void updateUser(User user) {

		userDao.updateUser(user);
	}

	@Override
	public String changePassword(String tnum, String password) {
		
		teacherDao.changePassword(tnum, password);


		
		return "success";
	}

	// 登录
	public Map<String, Object> doLogin(Teacher t, HttpServletRequest request) {

		Map<String, Object> model = new HashMap<String, Object>();
		Teacher teacher = teacherDao.findTeacherByTnum(t.getTnum());

		if ((teacher == null)) {
			model.put("msg", "failure");
			return model;
		} 
		if ((!t.getPassword().equals(teacher.getPassword()) || !t.getIdentify().equals(teacher.getIdentify()))) {
			model.put("msg", "failure");
			return model;
		}
		
		String ip = ipUtil.getClientIp(request);
		Date now = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		//保存用户访问记录
		accessDataService.addAccessData(teacher.getTname(), teacher.getTnum(),teacher.getSchool(),teacher.getCollege(), ip, f.format(now));
		
//		model.put("teacher", teacher);
		model.put("msg", "success");
		return model;
	}

}
