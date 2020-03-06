package com.ecs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecs.service.MajorService;
/**
 * 
 * @author xuluyang
 *
 * 2020年3月5日
 */

@Controller
public class MajorController {
	@Autowired
	private MajorService majorService;
	/**
	 * 根据parentid查找所有的二级学院
	 * @param parentid
	 * @return
	 */
	@RequestMapping("/findAllMajorByParentId")
	@ResponseBody
	public String findAllMajorByParentId(Integer parentid) {
		System.out.println("------------>>>>成功");
		parentid=1;
		return majorService.findAllMajorByParentId(parentid).toString();
	}
}
