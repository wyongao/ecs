package com.ecs.service.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecs.dao.TeacherDao;
import com.ecs.dao.UserDao;
import com.ecs.domain.Teacher;
import com.ecs.domain.User;
import com.ecs.service.UserService;

/**
 * 
 * @author xuluyang
 *
 *         2020年3月5日
 */
@Service
public class UserServiceImpl implements UserService {

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
	public void changePassword(Integer id, String passwrod) {
		userDao.changePassword(id, passwrod);

	}

	// 登录
	public Map<String, Object> doLogin(User u) {

		Map<String, Object> model = new HashMap<String, Object>();
		User user = new User();

//		if (u.getUsername().equals("") || (u.getUsername() == null)) { // 为空

//			model.put("msg", "failure");
//			return model;
//		}

		if (u.getIdentify().equals("0")) { // 身份为老师

			// 实现老师登录
			Teacher teacher = teacherDao.findTeacherByTnum(u.getUsername());

			if (teacher == null) {

				model.put("msg", "failure");
				return model;
			}

			user.setUsername(teacher.getTnum());
			user.setPassword(teacher.getTnum());
			user.setIdentify("0");

		} else { // 身份为超管

			user = userDao.fineOne(u.getUsername());
			if ((user == null) || !u.getPassword().equals(user.getPassword())) {

				model.put("msg", "failure");
				return model;
			}
			
		}

		model.put("user", user);
		model.put("msg", "success");
		return model;
	}

}
