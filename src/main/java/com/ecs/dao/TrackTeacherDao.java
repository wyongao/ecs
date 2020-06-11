package com.ecs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.ecs.dao.provider.TrackTeacherProvider;
import com.ecs.domain.TrackTeacher;

@Mapper
public interface TrackTeacherDao {

	@Insert("insert into track_teacher(tnum,tname,school,college,addr,date)"
			+ " values(#{tnum},#{tname},#{school},#{college},#{addr},#{date}) ")
	public void addTrackTeacher (TrackTeacher trackTeacher);
	
	@Select("select date,addr from track_teacher where DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date AND tnum=#{tnum} order by date desc")
	public List<TrackTeacher> findTrackTeacherForwx(String tnum);
	
	@SelectProvider(type = TrackTeacherProvider.class, method = "searchTeacherDynamic" )
	public List<TrackTeacher> searchTeacherDynamic(String school, String college, String tnum, String name);
	
	@Update("update track_teacher set tnum=#{tnum},tname=#{tname},school=#{school},college=#{college} where tnum=#{tnum1}")
	public void updateTrackTeacherInfo(String tnum,String tname,String school,String college,String tnum1);
}
