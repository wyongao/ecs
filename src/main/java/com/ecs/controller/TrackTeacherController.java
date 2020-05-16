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
import com.ecs.domain.TrackTeacher;
import com.ecs.service.TrackTeacherService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class TrackTeacherController {

	
	@Autowired
	private TrackTeacherService trackTeacherService;
	
	@ResponseBody
	@RequestMapping(value="/searchTeacherDynamic", method = RequestMethod.POST)
	public String searchTeacherDynamic(String page, String limit, String school, String college, String tnum, String name) {
		
		PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
		List<TrackTeacher> data = trackTeacherService.searchTeacherDynamic(school, college, tnum, name);
		PageInfo<TrackTeacher> pageInfo = new PageInfo<>(data);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "0");
		map.put("msg", "");
		map.put("count", pageInfo.getTotal());
		map.put("data", data);

		return JsonUtils.objectToJson(map);
	}
}
