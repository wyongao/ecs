package com.ecs.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecs.dao.DayTeacherDao;
import com.ecs.dao.TeacherDao;
import com.ecs.dao.TrackTeacherDao;
import com.ecs.domain.Teacher;
import com.ecs.service.TeacherService;

/**
 * 老师的实现类
 * 
 * @author xuluyang
 *
 *         2020年3月7日
 */
@Service
public class TeacherServiceImpl implements TeacherService {
	@Autowired
	private TeacherDao teacherDao;
	@Autowired
	private TrackTeacherDao trackTeacherDao;
	@Autowired
	private DayTeacherDao dayTeacherDao;


	/**
	 * 添加
	 */
	@Override
	public void addTeacher(Teacher teacher) {

		teacherDao.addTeacher(teacher.getTname(), teacher.getTnum(), teacher.getPassword(), teacher.getSchool(), teacher.getCollege(), teacher.getSex(), teacher.getTel(),teacher.getIdentify());
	}

	/**
	 * 删除
	 */
	@Override
	public String deleteTeacher(String id) {

		teacherDao.deleteTeacher(id);
		return "success";
	}

	/**
	 * 修改
	 */
	@Override
	public String  changeTeacher(Teacher teacher) {
		Integer id=teacher.getId();
		//找到修改之前的老师
		Teacher oldTeacher=teacherDao.findTeacherById(id);
		//修改老师的轨迹
		trackTeacherDao.updateTrackTeacherInfo(teacher.getTnum(),teacher.getTname(),teacher.getSchool(),teacher.getCollege(),oldTeacher.getTnum());
		//修改老师的每日申报
		dayTeacherDao.updateDayTeacherInfo(teacher.getTnum(),teacher.getTname(),teacher.getSchool(),teacher.getCollege(),oldTeacher.getTnum());
		teacherDao.changeTeacher(teacher);
		return "success";
	}

	@Override
	public Teacher findTeacherByTnum(String tnum) {
		
		return teacherDao.findTeacherByTnum(tnum);
	}
	
	@Override
	public List<Teacher> dynamicTeacher(String school,String college,String tnum) {
		
		return teacherDao.dynamicTeacher(school,college,tnum);
	}


	@Override
	public List<Teacher> fuzzyTeacher(String school,String college,String name) {
		
		return teacherDao.fuzzyTeacher(school,college,name);
	}

	@Override
	public Teacher findByTnumForwx(String tnum) {
		
		return teacherDao.findByTnumForwx(tnum);
	}

	@Override
	public Teacher findByTnumForweb(String tnum) {
		
		return teacherDao.findByTnumForweb(tnum);
	}
	
	public Integer countTeachers(String school, String college) {
		
		return teacherDao.countTeachers(school, college);
	}

	


}
