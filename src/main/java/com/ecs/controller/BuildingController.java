package com.ecs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecs.service.BuildingService;

@Controller
public class BuildingController {
	@Autowired
	private BuildingService buildingService;
	@RequestMapping("/findAllBuildingByParentId")
	@ResponseBody
	public String findAllBuildingByParentId(Integer parentid) {
		System.out.println("----------->>>执行成功");
		parentid=1;
		return buildingService.findAllBuildingByParentId(parentid).toString();
		
	}
}
