package com.ecs.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecs.dao.BuildingDao;
import com.ecs.domain.Building;
import com.ecs.service.BuildingService;
/**
 * building的实现类
 * @author xuluyang
 *
 * 2020年3月7日
 */

@Service
public class BuildingServiceImpl implements BuildingService {
	@Autowired
	private BuildingDao buildingDao;

	@Override
	public List<Building> findAllBuildingByParentId(Integer parentid) {
		
		return buildingDao.findAllBuildingByParentId(parentid);
	}

	@Override
	public Building findOnlyBuilding(String buildingname, Integer parentid) {
		
		return buildingDao.findOnlyBuilding(buildingname, parentid);
	}

	@Override
	public void addBuilding(String buildingname, Integer parentid) {
		
		buildingDao.addBuilding(buildingname, parentid);
		
	}
	
}
