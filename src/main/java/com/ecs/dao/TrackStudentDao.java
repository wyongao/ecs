package com.ecs.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.ecs.domain.TrackStudent;

@Mapper
public interface TrackStudentDao {

	@Insert("insert into track_student(snum,sname,school,college,major,classes,addr,date)"
			+ " values(#{snum},#{sname},#{school},#{college},#{major},#{classes},#{addr},#{date}) ")
	public void addTrackStudent(TrackStudent trackStudent);
}
