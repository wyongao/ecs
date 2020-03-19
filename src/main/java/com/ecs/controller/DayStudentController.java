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
import com.ecs.domain.DayStudent;
import com.ecs.service.DayStudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class DayStudentController {
	@Autowired
	private DayStudentService dayStudentService;

	// 查找当日的所有学生
	@RequestMapping("/findDayStudents")
	@ResponseBody
	public String findDayStudents(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
		// 分页
		// DateUtil.getDate();
		// 调试的时候注意这里的时间,不改就是空
		System.out.println("时间为---->>>" + DateUtil.getDate());
		PageHelper.startPage(pageNum, Constant.PAGE_SIZE);
		List<DayStudent> list = dayStudentService.findDayStudents(DateUtil.getDate());
		for (DayStudent dayStudent : list) {
			System.out.println(dayStudent);
		}
		PageInfo<DayStudent> pageInfo = new PageInfo<DayStudent>(list);
		System.out.println("总页数" + pageInfo.getPages() + "当前页" + pageInfo.getPageNum() + "总记录数" + pageInfo.getTotal());
		return "成功";
	}

	// 根据学院查找当天的
	@RequestMapping("/findDayStudentByCollege")
	@ResponseBody
	public String findDayStudentByCollege(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			String college) {
		college = "计算机学院";
		PageHelper.startPage(pageNum, Constant.PAGE_SIZE);
		List<DayStudent> list = dayStudentService.findDayStudentByCollege(college, DateUtil.getDate());
		PageInfo<DayStudent> pageInfo = new PageInfo<DayStudent>(list);
		System.out.println("总页数" + pageInfo.getPages() + "当前页" + pageInfo.getPageNum() + "总记录数" + pageInfo.getTotal());
		return "成功";
	}

	// 根据专业查找当天的
	@RequestMapping("/findDayStudentByMajor")
	@ResponseBody
	public String findDayStudentByMajor(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			String major, String date) {
		major = "计算机科学与技术";
		PageHelper.startPage(pageNum, Constant.PAGE_SIZE);
		List<DayStudent> list = dayStudentService.findDayStudentByMajor(major, DateUtil.getDate());
		PageInfo<DayStudent> pageInfo = new PageInfo<DayStudent>(list);
		System.out.println("总页数" + pageInfo.getPages() + "当前页" + pageInfo.getPageNum() + "总记录数" + pageInfo.getTotal());
		System.out.println("结果----------------->>>>>" + list.toString());
		return "成功";
	}

	// 根据学院专业班级查找当天的
	@RequestMapping("/findDayStudentByMajorAndClasses")
	@ResponseBody
	public String findDayStudentByMajorAndClasses(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			String major, Integer classes) {
		major = "计算机科学与技术";
		classes = 1741;
		PageHelper.startPage(pageNum, Constant.PAGE_SIZE);
		List<DayStudent> list = dayStudentService.findDayStudentByMajorAndClasses(major, classes, DateUtil.getDate());
		PageInfo<DayStudent> pageInfo = new PageInfo<DayStudent>(list);
		System.out.println("总页数" + pageInfo.getPages() + "当前页" + pageInfo.getPageNum() + "总记录数" + pageInfo.getTotal());
		System.out.println("结果---------------------->>>>" + list.toString());
		return "成功";
	}
	// 根据学号查找学生的打卡信息

	@RequestMapping("/findDayStudentBySnum")
	@ResponseBody
	public String findDayStudentBySnum(String snum) {
		snum = "201710913106";
		List<DayStudent> list = dayStudentService.findDayStudentBySnum(snum);
		System.out.println("结果--------------->>>>>>" + list.toString());
		return "成功";
	}

	// 动态sql加上时间上面的就可以不要了
	@RequestMapping("/findAllDayStudents")
	@ResponseBody
	public String findAllDayStudents(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,String college, String major, String classes, String snum,String date) {
		PageHelper.startPage(pageNum, Constant.PAGE_SIZE);
		List<DayStudent> list=dayStudentService.findAllDayStudents(college, major, classes, snum,date);
		PageInfo<DayStudent> pageInfo=new PageInfo<DayStudent>(list);
		System.out.println("总页数" + pageInfo.getPages() + "当前页" + pageInfo.getPageNum() + "总记录数" + pageInfo.getTotal());
		return list.toString();
	}

	// 查找学生的轨迹信息(面向小程序端的)
	@RequestMapping("/traceStudent")
	@ResponseBody
	public String traceStudent(String snum) {
		snum = "201710913106";
		List<String> list = dayStudentService.traceStudent(snum);
		System.out.println("----------------->>>>>" + list.toString());
		return "成功";
	}

	// 保存每日学生信息
	@RequestMapping("/addDayStudent")
	@ResponseBody
	public String addDayStudent(DayStudent dayStudent) {
		DayStudent dayStudent2 = new DayStudent("201710913101", "马金凤", "计算机学院", "软件工程", 1842, "河南省新乡市",
				DateUtil.getDate(), 36.7, "否");
		dayStudentService.addDayStudent(dayStudent2);
		System.out.println("---------------->>>>添加成功");
		return "成功";
	}
	
	@ResponseBody
	@RequestMapping(value = "/dailyData", method = RequestMethod.POST)
	public String dailyData (String page, String limit) {
		
	
		PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
		List<DayStudent> data = dayStudentService.findAll();
		PageInfo<DayStudent> pageInfo = new PageInfo<>(data);		
		
		Map<String, Object> map = new HashMap<String, Object>();		
		map.put("code", "0");
		map.put("msg", "");
		map.put("count", pageInfo.getTotal());
		map.put("data", data);
			
		return JsonUtils.objectToJson(map);
	}
	
	
	
	
	
}
