package com.ecs.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecs.dao.CollegeDao;
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
	
	@Override
	public List<College> findAllCollegeByParentId(Integer parentid) {
		
		return collegeDao.findAllCollegeByParentId(parentid);
	}
	
	@Override
	public List<College> findAllCollege() {
		
		return collegeDao.findAllCollege();
	}

	//根据父级学校名字查找二级院校
	@Override
	public College findCollegeByName(String collegename) {
		
		return collegeDao.findCollegeByName(collegename);
	}

}
