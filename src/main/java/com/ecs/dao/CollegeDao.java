package com.ecs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ecs.domain.College;

@Mapper
public interface CollegeDao {
	
	@Select("select * from college where parentid=#{parentid}")
	public List<College> findAllCollegeByParentId(Integer parentid);
	
	@Select("select * from college")
	public List<College> findAllCollege();

	@Select("select * from college where collegename=#{collegename}")
	public College findCollegeByName(String collegename);
	
	@Select("select * from college where collegename=#{collegename} and parentid=#{parentid}")
	public College findOnlyCollege(String collegename,Integer parentid);
	
	@Insert("insert into college(collegename,parentid) values(#{collegename},#{parentid})")
	public void addCollege(String collegename,Integer parentid);
	
	//根据查找唯一id
	@Select("select id from college where collegename=#{collegename} and parentid=#{parentid}")
	public Integer findCollegeId(String collegename,Integer parentid);
}
