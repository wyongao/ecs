package com.ecs.service;

import java.util.List;

import com.ecs.domain.TrackStudent;

public interface TrackStudentService {
	
	//添加轨迹
	public void addTrackStudent(TrackStudent trackStudent);
	
	//查询
	public String findTrackStudentForwx(String snum);
	
	//动态查询
	public List<TrackStudent> searchStudentDynamic(String school, String college, String snum, String name);
	
}
