package com.ecs.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecs.dao.TrackStudentDao;
import com.ecs.domain.TrackStudent;
import com.ecs.service.TrackStudentService;

@Service
public class TrackStudentServiceImpl implements TrackStudentService {

	@Autowired
	private TrackStudentDao trackStudentDao;
	
	@Override
	public void addTrackStudent(TrackStudent trackStudent) {
		
		trackStudentDao.addTrackStudent(trackStudent);
	}

}
