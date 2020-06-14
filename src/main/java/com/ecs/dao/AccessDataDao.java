package com.ecs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import com.ecs.dao.provider.AccessdataProvider;
import com.ecs.domain.AccessData;

@Mapper
public interface AccessDataDao {

	//模糊查询
	@SelectProvider(type = AccessdataProvider.class,method = "fuzzyAccessData")
	public List<AccessData> fuzzyAccessData(String school,String college,String username,String userid);

	// 根据学校学院动态查找
	@SelectProvider(type = AccessdataProvider.class,method = "dynamicFindAccessData")
	public List<AccessData> dynamicFindAccessData(String school,String college);

	// 保存
	@Insert("insert into accessdata(username, userid, school, college, ip, date) values(#{username}, #{userid}, #{school}, #{college}, #{ip}, #{date})")
	public void addAccessData(String username, String userid,String school,String college, String ip, String date);

}
