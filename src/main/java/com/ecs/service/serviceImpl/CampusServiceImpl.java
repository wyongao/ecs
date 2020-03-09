package com.ecs.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecs.dao.CampusDao;
import com.ecs.domain.Campus;
import com.ecs.service.CampusService;
@Service
public class CampusServiceImpl implements CampusService {
	@Autowired
	private CampusDao campusDao;

	public List<Campus> findAllCampusByParentId(Integer parentid) {

		return campusDao.findAllCampusByParentId(parentid);
	}

}
