package com.ecs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecs.common.JsonUtils;
import com.ecs.domain.TrackStudent;
import com.ecs.service.TrackStudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class TrackStudentController {

	@Autowired
	private TrackStudentService trackStudentService;
	
	@ResponseBody
	@RequestMapping(value="/searchStudentDynamic", method = RequestMethod.POST)
	public String searchStudentDynamic(String page, String limit, String school, String college, String snum, String name) {
		
		PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
		List<TrackStudent> data = trackStudentService.searchStudentDynamic(school, college, snum, name);
		PageInfo<TrackStudent> pageInfo = new PageInfo<>(data);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "0");
		map.put("msg", "");
		map.put("count", pageInfo.getTotal());
		map.put("data", data);
		
		return JsonUtils.objectToJson(map);
	}
}
