package com.ecs.service;

import java.util.List;

import com.ecs.domain.AccessData;

public interface AccessDataService {
	
	//查找所有
	public List<AccessData> findAll();
	
	//保存
	public void addAccessData(String username, String userid, String ip, String date);

}
