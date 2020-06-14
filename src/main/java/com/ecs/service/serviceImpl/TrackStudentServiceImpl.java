package com.ecs.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecs.common.JsonUtils;
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

	@Override
	public String findTrackStudentForwx(String snum) {
				
		return JsonUtils.objectToJson(trackStudentDao.findTrackStudentForwx(snum));
	}

	@Override
	public List<TrackStudent> searchStudentDynamic(String school, String college, String snum, String name) {
		
		return trackStudentDao.searchStudentDynamic(school, college, snum, name);
	}

}
