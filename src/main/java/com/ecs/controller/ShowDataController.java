package com.ecs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecs.common.JsonUtils;
import com.ecs.domain.College;
import com.ecs.service.CollegeService;
import com.ecs.service.SchoolService;
import com.ecs.service.ShowDataService;

@Controller
public class ShowDataController {
	
	@Autowired
	private ShowDataService showDataService;
	
	@Autowired
	private SchoolService schoolService;
	
	@Autowired
	private CollegeService collegeService;
	
	@RequestMapping(value = "/initStudentData",method = RequestMethod.GET)
	@ResponseBody
	public String initStudentData(String school,String college){
		Map<String, Object> map=showDataService.initStudentData(school, college);
		return JsonUtils.objectToJson(map);
	}
	
	@RequestMapping(value = "/initTeacherData",method = RequestMethod.GET)
	@ResponseBody
	public String initTeacherData(String school,String college){
		Map<String, Object> map=showDataService.initTeacherData(school, college);
		return JsonUtils.objectToJson(map);
	}
	
	@RequestMapping(value = "/dynamicPie",method = RequestMethod.GET)
	@ResponseBody
	public String dynamicPie(String school,String college,Integer collegeId) {
		Map<String, Object> map=showDataService.dynamicPie(school, college,collegeId);
		return JsonUtils.objectToJson(map);
	}
	
	@RequestMapping(value = "/initSelect",method = RequestMethod.POST)
	@ResponseBody
	public String initSelect(String school) {
		Integer schoolId=schoolService.findSchoolId(school);
		List<College> colleges=collegeService.findAllCollegeByParentId(schoolId);
		
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("data", colleges);
		return JsonUtils.objectToJson(map);
	}
}
