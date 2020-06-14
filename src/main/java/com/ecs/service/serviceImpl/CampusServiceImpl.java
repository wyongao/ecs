package com.ecs.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecs.dao.CampusDao;
import com.ecs.domain.Campus;
import com.ecs.service.CampusService;

@Service
public class CampusServiceImpl implements CampusService {
	@Autowired
	private CampusDao campusDao;

	@Override
	public Campus findOnlyCampus(String campusname, Integer parentid) {
	
		return campusDao.findOnlyCampus(campusname, parentid);
	}

	@Override
	public void addCampus(String campusname, Integer parentid) {
		
		campusDao.addCampus(campusname, parentid);
	}

	@Override
	public Integer findCampusId(String campusname, Integer parentid) {
		
		return campusDao.findCampusId(campusname, parentid);
	}

}
