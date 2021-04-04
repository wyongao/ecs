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
	public List<AccessData> dynamicFindAccessData(String school,String college) {
		
		return accessDataDao.dynamicFindAccessData(school,college);
	}

	@Override
	public void addAccessData(String username, String userid,String school,String college, String ip, String date) {
		
		accessDataDao.addAccessData(username, userid, school,college, ip, date);
	}

	@Override
	public List<AccessData> fuzzyAccessData(String school,String college,String username, String userid) {
		
		return accessDataDao.fuzzyAccessData(school,college,username, userid);
	}

	

	
	
}
