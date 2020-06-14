package com.ecs.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
/**
 * 校内建筑的DAO
 * @author xuluyang
 *
 * 2020年3月7日
 */
import org.apache.ibatis.annotations.Select;

import com.ecs.domain.Building;
@Mapper
public interface BuildingDao {
	
	@Select("select buildingname from building where parentid=#{parentid}")
	public ArrayList<String> findBuildingByParentIdForwx(Integer parentid);
	
	@Select("select * from building where buildingname=#{buildingname} and parentid=#{parentid}")
	public Building findOnlyBuilding(String buildingname,Integer parentid);
	
	@Insert("insert into building(buildingname,parentid) values(#{buildingname},#{parentid})")
	public void addBuilding(String buildingname,Integer parentid);
	
}
