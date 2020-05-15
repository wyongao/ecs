package com.ecs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ecs.domain.TrackStudent;

@Mapper
public interface TrackStudentDao {

	@Insert("insert into track_student(snum,sname,school,college,major,classes,addr,date)"
			+ " values(#{snum},#{sname},#{school},#{college},#{major},#{classes},#{addr},#{date}) ")
	public void addTrackStudent(TrackStudent trackStudent);
	
	@Select("select date,addr from track_student where DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date AND snum=#{snum} order by date desc")
	public List<TrackStudent> findTrackStudentForwx(String snum);
}
