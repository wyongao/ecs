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
	
}
