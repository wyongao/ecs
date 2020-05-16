package com.ecs.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecs.common.JsonUtils;
import com.ecs.dao.TrackTeacherDao;
import com.ecs.domain.TrackTeacher;
import com.ecs.service.TrackTeacherService;

@Service
public class TrackTeacherServiceImpl implements TrackTeacherService {

	@Autowired
	private TrackTeacherDao trackTeacherDao;
	
	@Override
	public void addTrackTeacher(TrackTeacher trackTeacher) {
		
		trackTeacherDao.addTrackTeacher(trackTeacher);
	}

	@Override
	public String findTrackTeacherForwx(String tnum) {
		
		return JsonUtils.objectToJson(trackTeacherDao.findTrackTeacherForwx(tnum));
	}

	@Override
	public List<TrackTeacher> searchTeacherDynamic(String school, String college, String tnum, String name) {
		
		return trackTeacherDao.searchTeacherDynamic(school, college, tnum, name);
	}

}
