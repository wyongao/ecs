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
import com.ecs.domain.Student;
import com.ecs.service.StudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 学生的Controller
 * 
 * @author xuluyang
 *
 *         2020年3月7日
 */
@Controller
public class StudentController {
	
	@Autowired
	private StudentService studentService;

	/**
	 * 删除学生
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteStudent")
	@ResponseBody
	public String deleteStudent(String id) {

		String msg = studentService.deleteStudent(id);
		
		return msg;
	}

	/**
	 * 修改信息
	 * 
	 * @param student
	 * @return
	 */
	@RequestMapping("/changeStudentInfo")
	@ResponseBody
	public String changeStudentInfo(Student student) {
		
		String msg = studentService.changeStudentInfo(student);
		
		return msg;
	}

	/**
	 * 动态查询
	 * 
	 * @param college
	 * @param major
	 * @param classes
	 * @param page
	 * @param limit
	 * @return
	 */

	@RequestMapping(value = "/studentDynamic")
	@ResponseBody
	public String studentDynamic(String page, String limit, String school,String college, String major, String classes) {

		PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
		List<Student> data = studentService.dynamicStudents(school,college, major, classes);
		PageInfo<Student> pageInfo = new PageInfo<>(data);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "0");
		map.put("msg", "");
		map.put("count", pageInfo.getTotal());
		map.put("data", data);
		return JsonUtils.objectToJson(map);
	}

	@ResponseBody
	@RequestMapping(value = "/baseData", method = RequestMethod.POST)
	public String baseData(String page, String limit, String school,String college,String major,String classes) {

		PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
		//List<Student> data = studentService.findStudentByCollegeName(college);
		List<Student> data=studentService.dynamicStudents(school, college, major, classes);
		PageInfo<Student> pageInfo = new PageInfo<Student>(data);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "0");
		map.put("msg", "");
		map.put("count", pageInfo.getTotal());
		map.put("data", data);

		return JsonUtils.objectToJson(map);
	}

	/**
	 * 模糊查询
	 * 
	 * @param name
	 * @param snum
	 * @param page
	 * @param limit
	 * @return
	 */

	@RequestMapping(value = "/fuzzyStudent", method = RequestMethod.POST)
	@ResponseBody
	public String fuzzyStudent(String school,String college,String name, String snum, String page, String limit) {
		PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
		List<Student> data = studentService.fuzzyStudent(school,college,name, snum);
		PageInfo<Student> pageInfo = new PageInfo<Student>(data);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "0");
		map.put("msg", "");
		map.put("count", pageInfo.getTotal());
		map.put("data", data);

		return JsonUtils.objectToJson(map);
	}

	
	
}
