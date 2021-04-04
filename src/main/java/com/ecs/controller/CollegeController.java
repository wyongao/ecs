package com.ecs.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecs.common.JsonUtils;
import com.ecs.domain.College;
import com.ecs.service.CollegeService;
/**
 * @author xuluyang
 *
 * 2020年3月5日
 */
@Controller
public class CollegeController {
	
	@Autowired
	private CollegeService collegeService;
	
	@RequestMapping(value = "/findCollegeId" , method=RequestMethod.POST)
	@ResponseBody
	public String findCollegeId(String collegeName) {
		College college=collegeService.findCollegeByName(collegeName);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("data", college);
		return JsonUtils.objectToJson(map);
	}
}

