package com.ecs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecs.service.ClassService;
/**
 * classcontroller
 * @author xuluyang
 *
 * 2020年3月5日
 */

@Controller
public class ClassController {
	@Autowired
	private ClassService classService;

	@RequestMapping("/findAllClassByParentId")
	@ResponseBody
	public String findAllClassByParentId(Integer parentid) {
		parentid=1;
		System.out.println("成功--------------------->>>>>>>");
		return classService.findAllClassByParentId(parentid).toString() ;
	}
}
