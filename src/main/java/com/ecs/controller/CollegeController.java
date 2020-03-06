package com.ecs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ecs.service.CollegeService;
/**
 * @author xuluyang
 *
 * 2020年3月5日
 */
@Controller
public class CollegeController {
	@Autowired
	private CollegeService collegeService;
	
	/**
	 * 查找二级学院
	 * @param parentid
	 * @return
	 */
	@RequestMapping("/findAllCollegeByParentId")
	@ResponseBody
	public String findAllCollegeByParentId(Integer parentid) {
		parentid = 1;
		System.out.println("查找成功---------------->>>>>>");
		return collegeService.findAllCollegeByParentId(parentid).toString();
	}
}
