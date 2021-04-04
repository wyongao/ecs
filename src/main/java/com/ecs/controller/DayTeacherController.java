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
import com.ecs.domain.DayTeacher;
import com.ecs.service.DayTeacherService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class DayTeacherController {
	
	@Autowired
	private DayTeacherService dayTeacherService;
		
		//初始化表格
		@RequestMapping(value = "/dailyTeacherData", method=RequestMethod.POST)
		@ResponseBody
		public String dailyTeacherData(String page,String limit,String school,String college) {
			String date=DateUtil.getDate();
			PageHelper.startPage(Integer.parseInt(page),Integer.parseInt(limit));
			List<DayTeacher> data=dayTeacherService.findWithParam(school, college, date);
			PageInfo<DayTeacher> pageInfo=new PageInfo<DayTeacher>(data);
			
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("code","0");
			map.put("msg", "");
			map.put("count", pageInfo.getTotal());
			map.put("data", data);
			return JsonUtils.objectToJson(map);
		}
		
		//根据姓名的模糊查询
		@ResponseBody
		@RequestMapping(value = "/fuzzyDayTeacher",method = RequestMethod.POST)
		public String fuzzyDayTeacher(String tname,String college,String school,String limit,String page) {
			PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
			System.out.println("---------->>>"+school);
			List<DayTeacher> data=dayTeacherService.fuzzyDayTeachers(tname,college,school);
			System.out.println(data);
			PageInfo<DayTeacher> pageInfo=new PageInfo<DayTeacher>(data);
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("code", "0");
			map.put("msg", "");
			map.put("count", pageInfo.getTotal());
			map.put("data", data);
			return JsonUtils.objectToJson(map);
		}
		
		//工号的动态查询
		@ResponseBody
		@RequestMapping(value = "/dynamicDayTeacher",method = RequestMethod.POST)
		public String dynamicTeacher(String school,String college,String tnum,String limit,String page) {
			PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
			List<DayTeacher> data=dayTeacherService.dynamicDayTeachers(school,college, tnum);
			System.out.println(data.toString());
			PageInfo<DayTeacher> pageInfo=new PageInfo<DayTeacher>(data);
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("code", "0");
			map.put("msg", "");
			map.put("count", pageInfo.getTotal());
			map.put("data", data);
			return JsonUtils.objectToJson(map);
		}
		//下拉框的选择
		@RequestMapping(value = "/selectDayTeacher",method = RequestMethod.POST)
		@ResponseBody
		public String selectDayTeacher(String page,String limit,String college,String school) {
		
			PageHelper.startPage(Integer.parseInt(page),Integer.parseInt(limit));
			List<DayTeacher> data=dayTeacherService.findWithParam(school, college, DateUtil.getDate());
			PageInfo<DayTeacher> pageInfo=new PageInfo<DayTeacher>(data);
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("code","0");
			map.put("msg", "");
			map.put("count", pageInfo.getTotal());
			map.put("data", data);
			return JsonUtils.objectToJson(map);
		}
		

}
