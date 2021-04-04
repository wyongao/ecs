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
	
	@RequestMapping(value = "/deleteTeacher",method = RequestMethod.POST)
	@ResponseBody
	public String deleteTeacher(String id) {
		
		String msg=teacherService.deleteTeacher(id);
		return msg;
	}
	
	@RequestMapping(value = "/changeTeacherInfo",method = RequestMethod.POST)
	@ResponseBody
	public String changeTeacherInfo(Teacher teacher) {
		String msg=teacherService.changeTeacher(teacher);
		return msg;
	}
	/**
	 * 用户分页
	 * @param page
	 * @param limit
	 * @return
	 */
	
	@ResponseBody
	@RequestMapping(value = "/userData", method = RequestMethod.POST)
	public String userData(String page, String limit, String school,String college,String tnum) {
		PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
		List<Teacher> data=teacherService.dynamicTeacher(school,college, tnum);
		PageInfo<Teacher> pageInfo = new PageInfo<Teacher>(data);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "0");
		map.put("msg", "");
		map.put("count", pageInfo.getTotal());
		map.put("data", data);
		
		
		return JsonUtils.objectToJson(map);
	}
	/**
	 * 动态查询
	 * @param college
	 * @param tnum
	 * @return
	 */
	@RequestMapping(value = "/dynamicTeacher",method = RequestMethod.POST)
	@ResponseBody
	public String dynamicTeacher(String school,String college,String tnum,String page, String limit) {
		PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
		List<Teacher> data=teacherService.dynamicTeacher(school,college, tnum);
		PageInfo<Teacher> pageInfo = new PageInfo<Teacher>(data);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "0");
		map.put("msg", "");
		map.put("count", pageInfo.getTotal());
		map.put("data", data);
		
		
		return JsonUtils.objectToJson(map);
	}
	/**
	 * 模糊查询
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/fuzzyTeacher",method = RequestMethod.POST)
	@ResponseBody
	public String fuzzyTeacher(String school,String college,String name,String page, String limit) {
		PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
		List<Teacher> data=teacherService.fuzzyTeacher(school,college,name);
		PageInfo<Teacher> pageInfo = new PageInfo<Teacher>(data);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "0");
		map.put("msg", "");
		map.put("count", pageInfo.getTotal());
		map.put("data", data);
		
		
		return JsonUtils.objectToJson(map);
	}
}
