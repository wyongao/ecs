package com.ecs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
/**
 * class
 * @author xuluyang
 *
 * 2020年3月5日
 */
import org.apache.ibatis.annotations.Select;

import com.ecs.domain.Class;
@Mapper
public interface ClassDao {
	/**
	 * 
	 * @param parentid
	 * @return
	 */
	@Select("select * from class where parentid=#{parentid}")
	public List<Class> findAllClassByParentId(Integer parentid);
	
	@Select("select * from class")
	public List<Class> findAllClass();
	
	@Select("select * from class where classname=#{classname} and parentid=#{parentid}")
	public Class findClassByName(String classname,Integer parentid);
	
	@Insert("insert into class(classname,parentid) values(#{calssname},#{parentid})")
	public void addClass(String calssname,Integer parentid);
}
