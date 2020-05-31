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

	@Override
	public List<Application> applicationDynamic(String school,String college, String major, String classes, String inout) {
		
		return applicationDao.applicationDynamic(school,college, major, classes, inout, ApplicationConstant.STATUS_CHECK_PENDING);
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
	public List<Application> findOutDataByCollege(String college,String school) {
		
		return applicationDao.findDataByCollege(ApplicationConstant.INOUT_OUT, college,school);
	}
	
	@Override
	public List<Application> findInDataByCollege(String school,String college) {
		
		return applicationDao.findDataByCollege(ApplicationConstant.INOUT_IN, college,school);
	}
	
	@Override
	public List<Application> fuzzyAppliacation(String school,String college,String snum, String sname,String inout) {

		return applicationDao.fuzzyAppliacation(school,college,snum, sname,inout);
	}
	
	@Override
	public List<Application> findBySnumForwx(String snum) {
		
		return applicationDao.findBySnumForwx(snum);
	}
	@Override
	public List<Application> findBySnumAndStatusForwx(String snum, String status) {
		
		return applicationDao.findBySnumAndStatusForwx(snum, status);
	}
	
	
	
	



}
