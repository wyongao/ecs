package com.ecs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
/**
 * 
 * @author xuluyang
 *
 * 2020年3月5日
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecs.service.SchoolService;


@Controller
public class SchoolController {
	@Autowired
	private SchoolService schoolService;
		@RequestMapping("/findAllSchool")
		@ResponseBody
		public String findAllSchool() {
			System.out.println("查找学校成功--------------->");
			return schoolService.findAllSchool().toString();
		}
}
