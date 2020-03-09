package com.ecs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecs.service.CampusService;

@Controller
public class CampusController {
	@Autowired
	private CampusService campusService;
	@RequestMapping("/findAllCampusByParentId")
	@ResponseBody
	public String findAllCampusByParentId(Integer parentid) {
		System.out.println("执行成功-------------------->>>>>>>>");
		parentid=1;
		return campusService.findAllCampusByParentId(parentid).toString();
	}
}
