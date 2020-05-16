package com.ecs.service;

import java.util.List;

import com.ecs.domain.TrackTeacher;

public interface TrackTeacherService {

	//添加轨迹
	public void addTrackTeacher(TrackTeacher trackTeacher);
	
	//查询
	public String findTrackTeacherForwx(String tnum);
	
	//动态查询
	public List<TrackTeacher> searchTeacherDynamic(String school, String college, String tnum, String name);
}
