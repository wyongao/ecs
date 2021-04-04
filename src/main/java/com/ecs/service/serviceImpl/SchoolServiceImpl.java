package com.ecs.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecs.dao.SchoolDao;
import com.ecs.domain.School;
import com.ecs.service.SchoolService;
@Service
public class SchoolServiceImpl implements SchoolService{
	
	@Autowired
	SchoolDao schoolDao;

	@Override
	public Integer findSchoolId(String schoolname) {
		
		return schoolDao.findSchoolId(schoolname);
	}
	@Override
	public School findSchoolByName(String schoolname) {
	
		return schoolDao.findSchoolByName(schoolname);
	}
	@Override
	public void addSchool(String schoolname) {
		schoolDao.addSchool(schoolname);
		
	}

}
