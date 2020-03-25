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

import com.ecs.common.DateUtil;
import com.ecs.common.JsonUtils;
import com.ecs.constant.Constant;
import com.ecs.domain.DayTeacher;
import com.ecs.service.DayTeacherService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class DayTeacherController {
	@Autowired
	private DayTeacherService dayTeacherService;

		//查找当日的所有老师
		@RequestMapping("/findDayTeachers")
		@ResponseBody
		public String findDayTeachers(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
			PageHelper.startPage(pageNum, Constant.PAGE_SIZE);
			List<DayTeacher> list=dayTeacherService.findDayTeachers(DateUtil.getDate());
			PageInfo<DayTeacher> pageInfo=new PageInfo<DayTeacher>(list);
			System.out.println("总页数"+pageInfo.getPages()+"当前页"+pageInfo.getPageNum()+"总记录数"+pageInfo.getTotal());
			System.out.println("-------------->>>>成功"+list.toString());
			return"成功";
		}
	
		//根据学院查找当天的老师
		@RequestMapping("/findDayTeacherByCollege")
		@ResponseBody
		public  String findDayTeacherByCollege(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,String college){
			college="计算机学院";
			PageHelper.startPage(pageNum, Constant.PAGE_SIZE);
			List<DayTeacher> list=dayTeacherService.findDayTeacherByCollege(college, DateUtil.getDate());
			PageInfo<DayTeacher> pageInfo=new PageInfo<DayTeacher>(list);
			System.out.println("总页数"+pageInfo.getPages()+"当前页"+pageInfo.getPageNum()+"总记录数"+pageInfo.getTotal());
			System.out.println("-------------->>>>成功"+list.toString());
			return"成功";
		}
		
		//根据职工号查找职工的打卡信息
		@RequestMapping("/findDayTeacherByTnum")
		@ResponseBody
		public  String findDayTeacherByTnum(String tnum) {
			tnum="101";
			List<DayTeacher> list=dayTeacherService.findDayTeacherByTnum(tnum);
			System.out.println("-------------->>>>成功"+list.toString());
			return"成功";
		}
			
		//查找老师的轨迹信息(面向小程序端的)
		@RequestMapping("/traceTeacher")
		@ResponseBody
		public String traceTeacher(String tnum){
			tnum="101";
			List<String> list=dayTeacherService.traceTeacher(tnum);
			System.out.println("-------------->>>>成功"+list.toString());
			return "成功";
		}
		
		//模糊分页查询
		@RequestMapping(value = "/fuzzyDayTeachers", method = RequestMethod.POST)
		@ResponseBody
		public String fuzzyDayTeachers(String tname,String limit,String page){
			PageHelper.startPage(Integer.parseInt(page),Integer.parseInt(limit));
			List<DayTeacher> data=dayTeacherService.fuzzyDayTeachers(tname);
			PageInfo<DayTeacher> pageInfo=new PageInfo<DayTeacher>(data);
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("code", "0");
			map.put("msg", "");
			map.put("count", pageInfo.getTotal());
			map.put("data", data);
			return JsonUtils.objectToJson(map);
		}
		
		//动态分页查询
		@RequestMapping(value = "/dynamicDayTeachers",method = RequestMethod.POST)
		@ResponseBody
		public String dynamicDayTeachers(String college,String tnum,String limit,String page) {
			PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
			List<DayTeacher> data=dayTeacherService.dynamicDayTeachers(college, tnum);
			PageInfo<DayTeacher> pageInfo=new PageInfo<DayTeacher>(data);
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("code", "0");
			map.put("msg", "");
			map.put("count", pageInfo.getTotal());
			map.put("data", data);
			return JsonUtils.objectToJson(map);
		}

}
