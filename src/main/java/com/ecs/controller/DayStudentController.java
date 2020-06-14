package com.ecs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecs.common.DateUtil;
import com.ecs.common.JsonUtils;
import com.ecs.domain.DayStudent;
import com.ecs.service.DayStudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class DayStudentController {
	
	@Autowired
	private DayStudentService dayStudentService;

	/**
	 * 默认加载的数据
	 * @param page
	 * @param limit
	 * @return
	 */

	@ResponseBody
	@RequestMapping(value = "/dailyData", method = RequestMethod.POST)
	public String dailyData(String page, String limit, String school,String college,String major,String classes,String snum) {
		
		PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
		List<DayStudent> data = dayStudentService.findAllDayStudents(school, college, major, classes, snum, DateUtil.getDate());
		PageInfo<DayStudent> pageInfo = new PageInfo<>(data);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "0");
		map.put("msg", "");
		map.put("count", pageInfo.getTotal());
		map.put("data", data);

		return JsonUtils.objectToJson(map);
	}
	
	/**
	 * 动态查找
	 */
	@ResponseBody
	@RequestMapping(value = "/dayStudentDynamic", method = RequestMethod.POST)
	public String DynamicData(String page, String limit,String school,String college,String classes,String major,String snum,String date) {
		System.out.println("---***-****-*-*-*-*-*-"+school+college+major+classes+snum);
		PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
		List<DayStudent> data = dayStudentService.findAllDayStudents(school,college, major, classes, snum, DateUtil.getDate());
		PageInfo<DayStudent> pageInfo = new PageInfo<>(data);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "0");
		map.put("msg", "");
		map.put("count", pageInfo.getTotal());
		map.put("data", data);
		return JsonUtils.objectToJson(map);
	}
	/**
	 * 根据姓名进行模糊分页查询(可用的)
	 */
	@ResponseBody
	@RequestMapping(value = "/fuzzyDayStudents", method = RequestMethod.POST)
	public String fuzzyDaystudents(String school,String college,String name,String limit,String page) {
		PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
		List<DayStudent> data=dayStudentService.fuzzyQueryDaystudents(school,college,name);
		PageInfo<DayStudent> pageInfo =new PageInfo<DayStudent>(data);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("code", "0");
		map.put("msg", "");
		map.put("count", pageInfo.getTotal());
		map.put("data", data);
		return JsonUtils.objectToJson(map);		
	}

	
}
