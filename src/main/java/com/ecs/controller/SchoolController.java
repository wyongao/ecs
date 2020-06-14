package com.ecs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ecs.service.SchoolService;

@Controller
public class SchoolController {

	@Autowired
	private SchoolService schoolService;

}
