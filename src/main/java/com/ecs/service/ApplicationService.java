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

	// 测试
	public List<Application> findAllApplications(String college, String major, String classes);
//	//测试2.0
//	public List<Application> findAllApplication();

	// 修改申请状态(管理员)
	public String updateStatus(String id, String status, String event);

	// 动态查询
	public List<Application> applicationDynamic(String school,String college, String major, String classes, String inout);

	// 查找出校申请
	public List<Application> findOutData();

	// 查找入校申请
	public List<Application> findInData();
	
	//通过学院名称查找出校申请
	public List<Application> findOutDataByCollege(String school,String college);

	//通过学院名称查找入校申请
	public List<Application> findInDataByCollege(String school,String college);
	
	/**
	 * 这是一个混合查询 可以根据学号进行查询(非模糊),也可以根据姓名进行模糊查询
	 * 
	 * @param snum
	 * @param sname
	 * @return list
	 */
	public List<Application> fuzzyAppliacation(String school,String colelge,String snum, String sname,String inout);
	
	// 通过学号查询
	public List<Application> findBySnumForwx(String snum);
	
	// 通过学号和状态查询
	public List<Application> findBySnumAndStatusForwx(String snum, String status);

}
