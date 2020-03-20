package com.ecs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ecs.domain.AccessData;

@Mapper
public interface AccessDataDao {

	
	//查找所有
	@Select("select * from accessdata")
	public List<AccessData> findAll();
	
	//保存
	@Insert("insert into accessdata(username, userid, ip, date) values(#{username}, #{userid}, #{ip}, #{date})")
	public void addAccessData(String username, String userid, String ip, String date);
}
