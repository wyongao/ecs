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

import com.ecs.common.JsonUtils;
import com.ecs.constant.Constant;
import com.ecs.domain.Teacher;
import com.ecs.service.TeacherService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
	public String findAllTeacher(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum) {
		//实现分页
		PageHelper.startPage(pageNum, Constant.PAGE_SIZE);
		List<Teacher> list=teacherService.findAllTeacher();
		PageInfo<Teacher> pageInfo=new PageInfo<Teacher>(list);
		System.out.println("总页数"+pageInfo.getPages()+"当前页"+pageInfo.getPageNum()+"总记录数"+pageInfo.getTotal());
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
	
	
	@ResponseBody
	@RequestMapping(value = "/userData", method = RequestMethod.POST)
	public String userData(String page, String limit) {
		
		PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
		List<Teacher> data = teacherService.findAllTeacher();
		PageInfo<Teacher> pageInfo = new PageInfo<Teacher>(data);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "0");
		map.put("msg", "");
		map.put("count", pageInfo.getTotal());
		map.put("data", data);
		
		
		return JsonUtils.objectToJson(map);
	}
	
}
