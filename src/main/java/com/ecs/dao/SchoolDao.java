package com.ecs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ecs.domain.School;
/**
 * 
 * @author xuluyang
 *
 * 2020年3月5日
 */

@Mapper
public interface SchoolDao {
	//查找所有的学校
	@Select("select * from school")
	public List<School> findAllSchool();
	
	//根据schoolname查找
	@Select("select * from school where schoolname=#{schoolname}")
	public School findSchoolByName(String schoolname);
}
