package com.ecs.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.ecs.domain.TrackTeacher;

@Mapper
public interface TrackTeacherDao {

	@Insert("insert into track_teacher(tnum,tname,school,college,addr,date)"
			+ " values(#{tnum},#{tname},#{school},#{college},#{addr},#{date}) ")
	public void addTrackTeacher (TrackTeacher trackTeacher);
}
