package com.ecs.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ecs.domain.Campus;

@Mapper
public interface CampusDao {
	/**
	 * 根据parentid查找校区
	 * @param parentid
	 * @return
	 */
	@Select("select * from campus where parentid=#{parentid}")
	public List<Campus> findAllCampusByParentId(Integer parentid);
	
	@Select("select campusname from campus where parentid=#{parentid}")
	public ArrayList<String> findCampusByParentIdForwx(Integer parentid);
	
	@Select("select * from campus where campusname=#{campusname} and parentid=#{parentid}")
	public Campus findOnlyCampus(String campusname,Integer parentid);
	
	@Insert("insert into campus(campusname,parentid) values(#{campusname},#{parentid})")
	public void addCampus(String campusname,Integer parentid);
	
	@Select("select id from campus where campusname=#{campusname} and parentid=#{parentid}")
	public Integer findCampusId(String campusname,Integer parentid);
}
