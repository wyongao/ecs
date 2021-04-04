package com.ecs.service.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecs.common.IpUtil;
import com.ecs.dao.TeacherDao;
import com.ecs.domain.Teacher;
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

	@Override
	public String changePassword(String tnum, String password) {
		
		teacherDao.changePassword(tnum, password);
		
		return "success";
	}

	// 登录
	public Map<String, Object> doLogin(Teacher t, HttpServletRequest request) {

		Map<String, Object> model = new HashMap<String, Object>();
		Teacher teacher = teacherDao.findTeacherByTnum(t.getTnum());

		if ((teacher == null)) { //账号错误
			
			model.put("msg", "failure");
			model.put("desc", "tnumError");
			return model;
		} 
		
		if (!t.getPassword().equals(teacher.getPassword())) { //密码错误
		
			model.put("msg", "failure");
			model.put("desc", "passwordError");
			return model;
		}
		
		if (!t.getIdentify().equals(teacher.getIdentify())) { //身份错误
			
			model.put("msg", "failure");
			model.put("desc", "identifyError");
			return model;
		}
		
		String ip = ipUtil.getClientIp(request);
		Date now = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//保存用户访问记录
		accessDataService.addAccessData(teacher.getTname(), teacher.getTnum(),teacher.getSchool(),teacher.getCollege(), ip, f.format(now));
		
//		model.put("teacher", teacher);
		model.put("msg", "success");
		return model;
	}

}
