package com.ecs.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecs.dao.ClassDao;
import com.ecs.domain.Class;
import com.ecs.service.ClassService;
/**
 * 实体类
 * @author xuluyang
 *
 * 2020年3月5日
 */
@Service
public class ClassServiceImpl implements ClassService{
	@Autowired
	private ClassDao classDao;
	/**
	 * 
	 */
	public List<Class> findAllClassByParentId(Integer parentid){
		return classDao.findAllClassByParentId(parentid);
	}
}
