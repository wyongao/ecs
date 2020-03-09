package com.ecs.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public void updateStatus(String status, String snum, String date) {
		applicationDao.updateStatus(status, snum, date);
		
	}

}
