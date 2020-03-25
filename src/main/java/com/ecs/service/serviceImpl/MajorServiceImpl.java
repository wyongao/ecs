package com.ecs.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecs.dao.CollegeDao;
import com.ecs.dao.MajorDao;
import com.ecs.domain.Major;
import com.ecs.service.MajorService;
/**
 * 实现类
 * @author xuluyang
 *
 * 2020年3月5日
 */

@Service
public class MajorServiceImpl implements MajorService {
	
	@Autowired
	private MajorDao majorDao;
	
	@Autowired
	private CollegeDao collegeDao;

	@Override
	public List<Major> findAllMajorByParentId(Integer parentid) {
		return majorDao.findAllMajorByParentId(parentid);
	}

	@Override
	public List<Major> findMajorByParentName(String parentname) {
		
		return majorDao.findAllMajorByParentId(collegeDao.findCollegeByName(parentname).getId());
	}

}
