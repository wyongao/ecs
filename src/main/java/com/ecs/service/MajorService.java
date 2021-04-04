package com.ecs.service;

import java.util.List;

import com.ecs.domain.Major;
/**
 * 专业的service
 * @author xuluyang
 *
 * 2020年3月5日
 */
public interface MajorService {
	
	/**
	 * 查找二级学院的接口
	 * @param parentid
	 * @return
	 */
	public List<Major> findAllMajorByParentId(Integer parentid);
	
	public List<Major> findMajorByParentName(String parentname);
	
	public Major findMajarByName(String majorname,Integer parentid);
	
	public void addMajor(String majorname,Integer parentid);

	public Integer findMajorId(String majorname,Integer parentid);
}
