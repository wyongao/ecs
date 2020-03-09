package com.ecs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecs.common.DateUtil;
import com.ecs.domain.DayStudent;
import com.ecs.service.DayStudentService;

@Controller
public class DayStudentController {
	@Autowired
	private DayStudentService dayStudentService;
	
	//查找当日的所有学生
	@RequestMapping("/findDayStudents")
	@ResponseBody
	public String findDayStudents() {
	
		//DateUtil.getDate();
		System.out.println("时间为---->>>"+DateUtil.getDate());
		
		dayStudentService.findDayStudents(DateUtil.getDate());
		return "成功";
	}
		//根据学院查找当天的
	@RequestMapping("/findDayStudentByCollege")
	@ResponseBody
	public String findDayStudentByCollege(String college) {
		college="计算机学院";
		dayStudentService.findDayStudentByCollege(college, DateUtil.getDate());
		return "成功";
	}
		//根据专业查找当天的
	@RequestMapping("/findDayStudentByMajor")
	@ResponseBody
	public String findDayStudentByMajor(String major,String date) {
		major="计算机科学与技术";
		List<DayStudent> list=dayStudentService.findDayStudentByMajor(major, DateUtil.getDate());
		System.out.println("结果----------------->>>>>"+list.toString());
		return "成功";
	}
	  	//根据学院专业班级查找当天的
	@RequestMapping("/findDayStudentByMajorAndClasses")
	@ResponseBody
	public String findDayStudentByMajorAndClasses(String major,Integer classes) {
		major="计算机科学与技术";
		classes=1741;
		List<DayStudent> list=dayStudentService.findDayStudentByMajorAndClasses(major, classes, DateUtil.getDate());
		System.out.println("结果---------------------->>>>"+list.toString());
		return "成功";
	}
		//根据学号查找学生的打卡信息
		
	@RequestMapping("/findDayStudentBySnum")
	@ResponseBody
	public String findDayStudentBySnum(String snum) {
		snum="201710913106";
		List<DayStudent> list=dayStudentService.findDayStudentBySnum(snum);
		System.out.println("结果--------------->>>>>>"+list.toString());
		return "成功";
	}
		//查找学生的轨迹信息(面向小程序端的)
	@RequestMapping("/traceStudent")
	@ResponseBody
	public String traceStudent(String snum) {
		snum="201710913106";
		List<String> list=dayStudentService.traceStudent(snum);
		System.out.println("----------------->>>>>"+list.toString());
		return "成功";
	}
		//保存每日学生信息
	@RequestMapping("/addDayStudent")
	@ResponseBody
	public String addDayStudent(DayStudent dayStudent) {
		DayStudent dayStudent2=new DayStudent("201710913101", "马金凤","计算机学院", "软件工程", 1842, "河南省新乡市", DateUtil.getDate(), 36.7, "否");
		dayStudentService.addDayStudent(dayStudent2);
		System.out.println("---------------->>>>添加成功");
		return "成功";
	}
}
