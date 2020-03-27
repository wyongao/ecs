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
import com.ecs.domain.AccessData;
import com.ecs.service.AccessDataService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class AccessDataController {

	@Autowired
	AccessDataService accessDataService;
	
	@ResponseBody
	@RequestMapping(value = "/accessData", method = RequestMethod.POST)
	public String accessData(String page, String limit, String college) {
		
		PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
		List<AccessData> data = accessDataService.findAccessDataByCollege(college);
		PageInfo<AccessData> pageInfo = new PageInfo<AccessData>(data);
		
		Map<String, Object> map = new HashMap<String, Object>();		
		map.put("code", "0");
		map.put("msg", "");
		map.put("count", pageInfo.getTotal());
		map.put("data", data);
			
		return JsonUtils.objectToJson(map);
	}
	
	/**
	 * 模糊擦查询
	 * @param username
	 * @param userid
	 * @param page
	 * @param limit
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/fuzzyAccessData",method = RequestMethod.POST)
	public String fuzzyAccessData(String username ,String userid,String page, String limit) {
		PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
		List<AccessData> data=accessDataService.fuzzyAccessData(username, userid);
		PageInfo<AccessData> pageInfo = new PageInfo<AccessData>(data);
		
		Map<String, Object> map = new HashMap<String, Object>();		
		map.put("code", "0");
		map.put("msg", "");
		map.put("count", pageInfo.getTotal());
		map.put("data", data);
			
		return JsonUtils.objectToJson(map);
	}
	
}
