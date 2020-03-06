package com.ecs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ecs.domain.College;

@Mapper
public interface CollegeDao {
	@Select("select * from college where parentid=#{parentid}")
	public List<College> findAllCollegeByParentId(Integer parentid);
}
