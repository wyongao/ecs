package com.ecs.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecs.dao.CollegeDao;
import com.ecs.dao.SchoolDao;
import com.ecs.domain.College;
import com.ecs.service.CollegeService;
/**
 * 
 * @author xuluyang
 *
 * 2020年3月5日
 */

@Service
public class CollegeServiceImpl implements CollegeService{
	
	@Autowired
	private CollegeDao collegeDao;
	
	@Autowired
	private SchoolDao schoolDao;
	
	@Override
	public List<College> findAllCollegeByParentId(Integer parentid) {
		
		return collegeDao.findAllCollegeByParentId(parentid);
	}
	
	//根据父级学校名字查找二级院校
	@Override
	public List<College> findCollegeByParentName(String schoolname) {
		
		return collegeDao.findAllCollegeByParentId(schoolDao.findSchoolByName(schoolname).getId());
	}

	@Override
	public College findCollegeByName(String collegename) {
		
		return collegeDao.findCollegeByName(collegename);
	}

	@Override
	public void addCollege(String collegename,Integer parentid) {
	
		collegeDao.addCollege(collegename,parentid);
	}

	@Override
	public Integer findCollegeId(String collegename,Integer parentid) {
		
		return collegeDao.findCollegeId(collegename,parentid);
	}

	@Override
	public College findOnlyCollege(String collegename, Integer parentid) {
		
		return collegeDao.findOnlyCollege(collegename, parentid);
	}

}
