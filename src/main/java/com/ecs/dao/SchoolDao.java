package com.ecs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
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
	
	//根据schoolname查找
	@Select("select * from school where schoolname=#{schoolname}")
	public School findSchoolByName(String schoolname);
	
	//根据学校的名字查找id
	@Select("select id from school where schoolname=#{schoolname}")
	public Integer findSchoolId(String schoolname);
	
	//添加学校
	@Insert("insert into school(schoolname) values(#{schoolname})")
	public void addSchool(String schoolname);
}
