package com.ecs.service;

import java.util.List;

import com.ecs.domain.AccessData;

public interface AccessDataService {

	// 查找所有
	public List<AccessData> findAll();

	//模糊查询
	public List<AccessData> fuzzyAccessData(String school,String college,String username,String userid);


	// 查找访问记录
	public List<AccessData> dynamicFindAccessData(String school,String college);

	// 保存
	public void addAccessData(String username,String userid,String school,String college, String ip, String date);


}
