package com.ecs.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecs.common.DateUtil;
import com.ecs.service.DayStudentService;
import com.ecs.service.DayTeacherService;
import com.ecs.service.StudentService;
import com.ecs.service.TeacherService;

@Controller
public class ShowDataController {
	@Autowired
	private StudentService studentService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private DayStudentService dayStudentService;
	@Autowired
	private DayTeacherService dayTeacherService;
	
	@RequestMapping(value = "/initData",method = RequestMethod.GET)
	@ResponseBody
	public String initData(String school,String college) throws JSONException {
		String date=DateUtil.getDate();
		List<String> listName=new ArrayList<String>();
		List<Integer> listSum=new ArrayList<Integer>();
		List<String> listName1=new ArrayList<String>();
		List<Integer> listSum1=new ArrayList<Integer>();
		//总数量
		Integer dayStudentSum=dayStudentService.countDayStudent(college, date);
		Integer studentSum=studentService.coutStudent(school, college);
		//今日打卡的人数
		Integer teacherSum=teacherService.countTeachers(school, college);
		Integer dayTeacherSum=dayTeacherService.countDayTeachers(college, date);
		listSum.add(dayStudentSum);
		listSum.add(studentSum-dayStudentSum);
		listName.add("打卡人数");
		listName.add("未打卡人数");
		listSum1.add(teacherSum);
		//这里数据有一点点问题
		listSum1.add(dayTeacherSum);
		listName1.add("打卡人数");
		listName1.add("未打卡人数");
		JSONObject jsonData=new JSONObject();
		jsonData.put("listSum",listSum);
		jsonData.put("listName",listName);
		jsonData.put("listSum1",listSum1);
		jsonData.put("listName1",listName1);
		System.out.println(jsonData);
		return jsonData.toString();
	}
	
}
