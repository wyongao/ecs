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
	
	public List<College> findCollegeByParentName(String schoolname);
	
	public College findCollegeByName(String collegename);
	
	public void addCollege(String collegename,Integer parentid);
	
	public Integer findCollegeId(String collegename,Integer parentid);
	
	public College findOnlyCollege(String collegename,Integer parentid);
}
