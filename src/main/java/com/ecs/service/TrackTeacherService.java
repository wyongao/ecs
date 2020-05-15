package com.ecs.service;

import com.ecs.domain.TrackTeacher;

public interface TrackTeacherService {

	//添加轨迹
	public void addTrackTeacher(TrackTeacher trackTeacher);
	
	//查询
	public String findTrackTeacherForwx(String tnum);
}
