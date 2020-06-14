package com.ecs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ecs.service.CampusService;

@Controller
public class CampusController {
	
	@Autowired
	private CampusService campusService;
	

}
