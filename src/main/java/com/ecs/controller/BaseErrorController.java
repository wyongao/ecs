package com.ecs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/error")
public class BaseErrorController{

	
	@RequestMapping("/400")
	@ResponseBody
	public String ErrorPage400() {
		return "400!!!!";
	}

	@RequestMapping("/500")
	@ResponseBody
	public String errorPage500() {
		return "5000!!!";
	}
	
	@RequestMapping("/404")
	@ResponseBody
	public String errorPage404() {
		return "404!!!";
	}
	


}
