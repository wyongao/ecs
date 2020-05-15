package com.ecs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecs.common.JsonUtils;
import com.ecs.domain.Major;
import com.ecs.service.MajorService;

/**
 * 
 * @author xuluyang
 *
 *         2020年3月5日
 */

@Controller
public class MajorController {
	@Autowired
	private MajorService majorService;

	/**
	 * 根据parentid查找所有的二级学院
	 * 
	 * @param parentid
	 * @return
	 */
	@RequestMapping(value = "/findAllMajorByParentId", method = RequestMethod.POST)
	@ResponseBody

	public String findAllMajorByParentId(Integer parentid, Model model) {
		List<Major> list = majorService.findAllMajorByParentId(parentid);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", list);
		return JsonUtils.objectToJson(map);
	}
	
	@RequestMapping(value = "/findAllMajorByParentName", method = RequestMethod.POST)
	@ResponseBody

	public String findAllMajorByParentId(String parentname,Model model) {
		List<Major> list = majorService.findMajorByParentName(parentname);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", list);
		return JsonUtils.objectToJson(map);
	}
	
}
