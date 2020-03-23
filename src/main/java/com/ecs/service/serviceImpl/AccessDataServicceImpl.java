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
	public void addAccessData(String username, String userid, String ip, String date) {
		
		accessDataDao.addAccessData(username, userid, ip, date);
	}

	
	
}
