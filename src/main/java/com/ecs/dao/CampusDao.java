package com.ecs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ecs.domain.Campus;

@Mapper
public interface CampusDao {
	/**
	 * 根据parentid查找校区
	 * @param parentid
	 * @return
	 */
	@Select("select * from campus where parentid=#{parentid}")
	public List<Campus> findAllCampusByParentId(Integer parentid);
}
