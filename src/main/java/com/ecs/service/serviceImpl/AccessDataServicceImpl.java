package com.ecs.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecs.dao.AccessDataDao;
import com.ecs.domain.AccessData;
import com.ecs.service.AccessDataService;

@Service
public class AccessDataServicceImpl implements AccessDataService {

	@Autowired
	AccessDataDao accessDataDao;
	
	@Override
	public List<AccessData> findAll() {
		
		return accessDataDao.findAll();
	}
	
	@Override
	public List<AccessData> findAccessDataByCollege(String college) {
		
		return accessDataDao.findAccessDataByCollege(college);
	}
	

	@Override
	public void addAccessData(String username, String userid,String college, String ip, String date) {
		
		accessDataDao.addAccessData(username, userid, college, ip, date);
	}

	@Override
	public List<AccessData> fuzzyAccessData(String username, String userid) {
		
		return accessDataDao.fuzzyAccessData(username, userid);
	}

	

	
	
}
