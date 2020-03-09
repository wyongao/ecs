package com.ecs.service;

import java.util.List;

import com.ecs.domain.Campus;
/**
  * 校区的service
 * @author xuluyang
 *
 * 2020年3月7日
 */

public interface CampusService {
	public List<Campus> findAllCampusByParentId(Integer parentid);
}
