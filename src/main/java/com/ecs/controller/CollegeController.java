package com.ecs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecs.common.JsonUtils;
import com.ecs.domain.College;
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
	 * 根据学校查找二级学院
	 * @param parentid
	 * @return
	 */
	@RequestMapping("/findAllCollegeByParentId")
	@ResponseBody
	public String findAllCollegeByParentId(@RequestParam(value = "parentid")Integer parentid) {
		//parentid = 1;
		System.out.println("查找成功---------------->>>>>>"+parentid);
		return collegeService.findAllCollegeByParentId(parentid).toString();
	}
	//下拉框的选择
	@RequestMapping(value = "/findAllCollege", method = RequestMethod.POST)
	@ResponseBody
	public String findAllCollege() {
		List<College> list = collegeService.findAllCollege();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data2", list);
		System.out.println(JsonUtils.objectToJson(map));
		return JsonUtils.objectToJson(map);
	}
	
	@RequestMapping(value = "/findCollegeId" , method=RequestMethod.POST)
	@ResponseBody
	public String findCollegeId(String collegeName) {
		College college=collegeService.findCollegeByName(collegeName);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("data", college);
		return JsonUtils.objectToJson(map);
	}
}

