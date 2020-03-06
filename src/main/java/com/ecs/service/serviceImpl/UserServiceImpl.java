package com.ecs.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecs.dao.UserDao;
import com.ecs.domain.User;
import com.ecs.service.UserService;
/**
 * 
 * @author xuluyang
 *
 * 2020年3月5日
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	//查找所有的用戶
	@Override
	public List<User> findAll() {

		return userDao.findAll();
	}
	
	//添加用户
	@Override
	public void addUser(User user) {
		userDao.addUser(user);
	}
	
	//删除用户
	@Override
	public void deleteUser(Integer id) {

		userDao.deleteUser(id);
	}
	
	//修改用户
	@Override
	public void updateUser(User user) {
		
		userDao.updateUser(user);
	}

	@Override
	public void changePassword(Integer id, String passwrod) {
		userDao.changePassword(id, passwrod);
		
	}

}
