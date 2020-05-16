package com.ecs.service;

import java.util.List;

import com.ecs.domain.Building;
/**
 * building的service
 * @author xuluyang
 *
 * 2020年3月7日
 */

public interface BuildingService {
	public List<Building> findAllBuildingByParentId(Integer parentid);
	
	public Building findOnlyBuilding(String buildingname,Integer parentid);
	
	public void addBuilding(String buildingname,Integer parentid);
}
