package com.ecs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ecs.domain.AccessData;

@Mapper
public interface AccessDataDao {

	// 查找所有
	@Select("select * from accessdata")
	public List<AccessData> findAll();

	// 根据学院查找
	@Select("select * from accessdata where college=#{college}")
	public List<AccessData> findAccessDataByCollege(String college);

	// 保存
	@Insert("insert into accessdata(username, userid, college, ip, date) values(#{username}, #{userid}, #{college}, #{ip}, #{date})")
	public void addAccessData(String username, String userid,String college, String ip, String date);
}
