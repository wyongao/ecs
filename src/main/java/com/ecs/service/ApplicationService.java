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
	//测试
	public List<Application> findAllApplications(String college, String major, String classes);
//	//测试2.0
//	public List<Application> findAllApplication();

	// 修改申请状态(管理员)
	public void updateStatus(String status, String snum, String date);

}
