package com.ecs.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecs.common.JsonUtils;
import com.ecs.service.ShowDataService;

@Controller
public class ShowDataController {
	
	@Autowired
	private ShowDataService showDataService;
	
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
	
}
