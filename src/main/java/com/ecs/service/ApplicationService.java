package com.ecs.service;

import java.util.List;

import com.ecs.domain.Application;

/**
 * 
 * @author xuluyang
 *
 *         2020年3月9日
 */
public interface ApplicationService {

	// 上报申请
	public void addApplicationInfo(Application application);

	// 查询申请
	public List<Application> findAllByCollegeAndMajor(String college, String major);

	// 查找所有的申请根据学院和专业和班级(管理员)
	public List<Application> findAllByCollegeAndMajorAndClasses(String college, String major, String classes);

	// 查找所有的申请根据学院(管理员)
	public List<Application> findAllByCollege(String college);

	// 修改申请状态(管理员)
	public void updateStatus(String status, String snum, String date);
	
	//查找出校申请
	public List<Application> findOutData();
	
	//查找入校申请
	public List<Application> findInData();

}
