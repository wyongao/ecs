package com.ecs.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecs.constant.ApplicationConstant;
import com.ecs.dao.ApplicationDao;
import com.ecs.domain.Application;
import com.ecs.service.ApplicationService;
/**
 * 每日申请的实现类
 * @author xuluyang
 *
 * 2020年3月9日
 */
@Service
public class ApplicationServiceImpl implements ApplicationService{
	@Autowired
	private ApplicationDao applicationDao;
	//申请
	@Override
	public void addApplicationInfo(Application application) {
		applicationDao.addApplicationInfo(application);
		
	}
	//批准或者驳回
	@Override
	public String updateStatus(String id, String status, String event) {
		
		
		if(!status.equals(ApplicationConstant.STATUS_CHECK_PENDING)) {
			return "falure";
		}else {
			if(event.equals("pass")) {
				applicationDao.updateStatus(id, ApplicationConstant.STATUS_PASSED);
			}else if(event.equals("reject")) {
				applicationDao.updateStatus(id, ApplicationConstant.STATUS_REJECTED);
			}
		}	
		return "success";
	}
	
	//查询
	@Override
	public List<Application> findAllByCollegeAndMajor(String college, String major) {
		
		return applicationDao.findAllByCollegeAndMajor(college, major);
	}
	
	@Override
	public List<Application> findAllByCollegeAndMajorAndClasses(String college, String major, String classes) {
		
		return applicationDao.findAllByCollegeAndMajorAndClasses(college, major, classes);
	}
	
	@Override
	public List<Application> findAllByCollege(String college) {
		
		return applicationDao.findAllByCollege(college);
	}
	

	//测试用
	@Override
	public List<Application> findAllApplications(String college, String major, String classes) {
		
		return applicationDao.findAllApplications(college, major, classes);
	}
//	//测试2.0
//	@Override
//	public List<Application> findAllApplication() {
//		
//		return applicationDao.findAllApplication();
//	}
//	

	@Override
	public List<Application> applicationDynamic(String college, String major, String classes, String inout) {
		
		return applicationDao.applicationDynamic(college, major, classes, inout);
	}
	
	
	@Override
	public List<Application> findOutData() {
		
		return applicationDao.findOutData(ApplicationConstant.INOUT_OUT);
	}
	
	@Override
	public List<Application> findInData() {
		
		return applicationDao.findInData(ApplicationConstant.INOUT_IN);
	}
	
	@Override
	public List<Application> findOutDataByCollege(String college) {
		
		return applicationDao.findDataByCollege(ApplicationConstant.INOUT_OUT, college);
	}
	
	@Override
	public List<Application> findInDataByCollege(String college) {
		
		return applicationDao.findDataByCollege(ApplicationConstant.INOUT_IN, college);
	}
	
	@Override
	public List<Application> fuzzyAppliacation(String snum, String sname,String inout) {

		return applicationDao.fuzzyAppliacation(snum, sname,inout);
	}
	
	@Override
	public List<Application> findBySnumForwx(String snum) {
		
		return applicationDao.findBySnumForwx(snum);
	}
	
	
	
	



}
