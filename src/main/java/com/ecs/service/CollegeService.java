package com.ecs.service;

import java.util.List;

import com.ecs.domain.College;
/**
 * 
 * @author xuluyang
 *
 * 2020年3月5日
 */
public interface CollegeService {
	/**
	 * 根据parentid查找所有的二级学院
	 * @param parentid
	 * @return
	 */
	public List<College> findAllCollegeByParentId(Integer parentid);
	/**
	 * 下拉框用的
	 * @return
	 */
	public List<College> findAllCollege();
	
	public College findCollegeByName(String collegename);
}
