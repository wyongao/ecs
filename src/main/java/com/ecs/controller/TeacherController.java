package com.ecs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecs.domain.Teacher;
import com.ecs.service.TeacherService;
/**
 * 老师的controller
 * @author xuluyang
 *
 * 2020年3月7日
 */
@Controller
public class TeacherController {
	@Autowired
	private TeacherService teacherService;
	
	@RequestMapping("/findAllTeacher")
	@ResponseBody
	public String findAllTeacher() {
		System.out.println("----------->>>查找成功");
		return teacherService.findAllTeacher().toString();
	}
	
	@RequestMapping("/addTeacher")
	@ResponseBody
	public String addTeacher(Teacher teacher) {
		teacher.setCollege("计算机学院");
		teacher.setSchool("河南工程学院");
		teacher.setSex("男");
		teacher.setTname("张老师");
		teacher.setTnum("20168941");
		teacher.setTel("13816383963");
		teacherService.addTeacher(teacher);
		return "添加成功";
	}
	
	@RequestMapping("/deleteTeacherByTnum")
	@ResponseBody
	public String deleteTeacher(String tnum) {
		tnum="20168941";
		teacherService.deleteTeacher(tnum);
		
		return "删除成功";
	}
	
	@RequestMapping("/updateTeacher")
	@ResponseBody
	public String updateTeacher(Teacher teacher) {
		teacher.setCollege("理学院");
		teacher.setSchool("河南工程学院");
		teacher.setSex("男");
		teacher.setTname("张老师");
		teacher.setTnum("20168941");
		teacher.setTel("13816383963");
		teacherService.updateTeacher(teacher);
		return "修改成功";
	}
}
