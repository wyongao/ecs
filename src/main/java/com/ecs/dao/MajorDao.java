package com.ecs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
/**
 * 专业的mapper
 * @author xuluyang
 *
 * 2020年3月5日
 */
import org.apache.ibatis.annotations.Select;

import com.ecs.domain.Major;
@Mapper
public interface MajorDao {
	/**
	 * 查找所有的专业
	 * @param parentid
	 * @return
	 */
	@Select("select * from major where parentid=#{parentid}")
	public List<Major> findAllMajorByParentId(Integer parentid);
}
