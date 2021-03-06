package com.ecs.service;

import java.util.List;

import com.ecs.domain.Class;
/**
 * 班级的服务接口
 * @author xuluyang
 *
 * 2020年3月5日
 */
public interface ClassService {
	
	//根据parentid查询
	public List<Class> findAllClassByParentId(Integer parentid);
	
	public Class findClassByName(String classname,Integer parentid);

	public void addClass(String calssname,Integer parentid);
}
