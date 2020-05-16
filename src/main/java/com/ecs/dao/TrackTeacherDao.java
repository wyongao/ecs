package com.ecs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ecs.domain.TrackTeacher;

@Mapper
public interface TrackTeacherDao {

	@Insert("insert into track_teacher(tnum,tname,school,college,addr,date)"
			+ " values(#{tnum},#{tname},#{school},#{college},#{addr},#{date}) ")
	public void addTrackTeacher (TrackTeacher trackTeacher);
	
	@Select("select date,addr from track_teacher where DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date AND tnum=#{tnum} order by date desc")
	public List<TrackTeacher> findTrackTeacherForwx(String tnum);
}
