package com.ecs.service;

import com.ecs.domain.School;

public interface SchoolService {
	
	public Integer findSchoolId(String schoolname);
	
	public School findSchoolByName(String schoolname);
	
	public void addSchool(String schoolname);
}
