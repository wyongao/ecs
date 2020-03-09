package com.ecs.service;

import com.ecs.domain.Application;
/**
 * 
 * @author xuluyang
 *
 * 2020年3月9日
 */
public interface ApplicationService {
	
	//上报申请
	public void addApplicationInfo(Application application);
	
	
	//修改申请状态(管理员)
	public void updateStatus(String status,String snum,String date);

}
