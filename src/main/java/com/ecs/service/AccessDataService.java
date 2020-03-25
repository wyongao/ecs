package com.ecs.service;

import java.util.List;

import com.ecs.domain.AccessData;

public interface AccessDataService {

	// 查找所有
	public List<AccessData> findAll();

	// 根据学院查找访问记录
	public List<AccessData> findAccessDataByCollege(String college);

	// 保存
	public void addAccessData(String username, String userid,String college, String ip, String date);

}
