package com.ecs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ecs.common.DateUtil;
import com.ecs.common.JsonUtils;
import com.ecs.domain.Application;
import com.ecs.domain.DayStudent;
import com.ecs.domain.Student;
import com.ecs.domain.Teacher;
import com.ecs.service.ApplicationService;
import com.ecs.service.DayStudentService;
import com.ecs.service.StudentService;
import com.ecs.service.TeacherService;
import com.ecs.service.UploadService;

/**
 * 上传文件的controller
 * 
 * @author xuluyang
 *
 *         2020年4月13日
 */
@Controller
public class UploadController {
	@Autowired
	private UploadService uploadService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private DayStudentService dayStudentService;
	@Autowired
	private ApplicationService applicationService; 

	@RequestMapping(value = "/studentUpload", method = RequestMethod.POST)
	@ResponseBody
	public String studentUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (file.isEmpty()) {
			map.put("res", "1");
			// System.out.println(JsonUtils.objectToJson(map));
			return JsonUtils.objectToJson(map);
		} else {
			List<Student> studentslist = uploadService.studentUpload(file);
			// System.out.println("集合的长度"+studentslist.size());
			for (Student student : studentslist) {
				studentService.addStudent(student);
			}
			map.put("res", "0");
			// System.out.println(JsonUtils.objectToJson(map));
			return JsonUtils.objectToJson(map);
		}
	}
	/**
	 * 上传老师的基本信息	
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/teacherUpload", method = RequestMethod.POST)
	@ResponseBody
	public String teacherUpload(@RequestParam("file") MultipartFile file,HttpServletRequest request) {
		Map<String, Object> map=new HashMap<String, Object>();
		if (file.isEmpty()) {
			map.put("res", "1");
			System.out.println(JsonUtils.objectToJson(map));
			return JsonUtils.objectToJson(map);
		}else {
			List<Teacher> teacherslist =uploadService.teacherUpload(file) ;
			 System.out.println("集合的长度"+teacherslist.size());
			for(Teacher teacher :teacherslist ) {
				System.out.println("---->>>>>>>"+teacher);
				teacherService.addTeacher(teacher);
			}
			map.put("res","0");
			System.out.println(JsonUtils.objectToJson(map));
			return JsonUtils.objectToJson(map);
		}
	}
	
	
	/**
	 * 学生每日信息导出
	 * @param request
	 * @param college
	 * @param major
	 * @param snum
	 * @param classes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/outputStudent", method = RequestMethod.GET)
	public ResponseEntity<byte []> studentDailyDataExport(HttpServletRequest request,String college,String major,String snum
			,String classes) throws Exception 
	{		
			List<DayStudent> list=dayStudentService.findAllDayStudents(college, major, classes, snum, DateUtil.getDate());
			if(list.isEmpty()) {
				return null;
			}else {
				System.out.println(uploadService.studentDailyDataExport(request, list));
			return uploadService.studentDailyDataExport(request, list);
			}   
	}
	
	@RequestMapping(value = "outputApplication",method = RequestMethod.GET )
	public ResponseEntity<byte []> applicationDataExport(HttpServletRequest request,String college,String major,String classes,String inout) throws Exception
	{
		List<Application> list=applicationService.applicationDynamic(college, major, classes, inout);
		if (list.isEmpty()) {
			return null;
		}else{
			
			return uploadService.applicationDataExport(request, list);
		}
		
	}

}
