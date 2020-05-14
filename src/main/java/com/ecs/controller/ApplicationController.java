package com.ecs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecs.common.DateUtil;
import com.ecs.common.JsonUtils;
import com.ecs.constant.ApplicationConstant;
import com.ecs.constant.Constant;
import com.ecs.domain.Application;
import com.ecs.service.ApplicationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 申请的controller
 * 
 * @author xuluyang
 *
 *         2020年3月9日
 */
@Controller
public class ApplicationController {
	@Autowired
	private ApplicationService applicationService;
	Log logger = LogFactory.getLog(ApplicationController.class);

	@RequestMapping("/addApplicationInfo")
	@ResponseBody
	// 上报申请
	public String addApplicationInfo(Application application) {
		// 得到日期
		application.setDate(DateUtil.getDate());
		applicationService.addApplicationInfo(application);
		return "成功";
	}

	// 管理员根据学院专业查询申请状态
	@RequestMapping("/findAllApplicationByCollegeAndMajor")
	@ResponseBody
	public String findAllApplicationByCollegeAndMajor(
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, String college, String major) {
		college = "计算机学院";
		major = "计算机科学与技术";
		PageHelper.startPage(pageNum, Constant.PAGE_SIZE);
		List<Application> list = applicationService.findAllByCollegeAndMajor(college, major);
		PageInfo<Application> pageInfo = new PageInfo<Application>(list);
		System.out.println("总页数" + pageInfo.getPages() + "当前页" + pageInfo.getPageNum() + "总记录数" + pageInfo.getTotal());
		logger.trace("-------------------!!!!日志成功1!!>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		logger.info("-------------------!!!!日志成功2!!>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		logger.debug("-------------------!!!!日志成功3!!>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		logger.error("-------------------!!!!日志成功4!!>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		logger.warn("-------------------!!!!日志成功5!!>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		return "成功";
	}

	// 管理员根据学院查询申请的人
	@RequestMapping(value = "/findAllApplicationByCollege")
	@ResponseBody
	public String findAllApplicationByCollege(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			String college) {
		college = "计算机学院";
		PageHelper.startPage(pageNum, Constant.PAGE_SIZE);
		List<Application> list = applicationService.findAllByCollege(college);
		PageInfo<Application> pageInfo = new PageInfo<Application>(list);
		System.out.println("总页数" + pageInfo.getPages() + "当前页" + pageInfo.getPageNum() + "总记录数" + pageInfo.getTotal());
		return "成功";
	}

	// 管理员根据学院专业班级查询申请的人
	@RequestMapping(value = "/findAllApplicationByCollegeAndMajorAndClasses")
	@ResponseBody
	public String findAllApplicationByCollegeAndMajorAndClasses(
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, String college, String major,
			String classes) {
		college = "理学院";
		major = "物理";
		classes = "1742";
		PageHelper.startPage(pageNum, Constant.PAGE_SIZE);
		List<Application> list = applicationService.findAllByCollegeAndMajorAndClasses(college, major, classes);
		PageInfo<Application> pageInfo = new PageInfo<Application>(list);
		System.out.println("总页数" + pageInfo.getPages() + "当前页" + pageInfo.getPageNum() + "总记录数" + pageInfo.getTotal());
		return "成功";
	}

	// 测试动态sql
	// 有空字符串的bug
	@RequestMapping("/findAllApplications")
	@ResponseBody
	public String findAllApplications(String college, String major, String classes,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
		PageHelper.startPage(pageNum, Constant.PAGE_SIZE);
		List<Application> list = applicationService.findAllApplications(college, major, classes);
		PageInfo<Application> pageInfo = new PageInfo<Application>(list);
		System.out.println("总页数" + pageInfo.getPages() + "当前页" + pageInfo.getPageNum() + "总记录数" + pageInfo.getTotal());

		return "成功";
	}



	@RequestMapping(value = "/updateStatus")
	@ResponseBody
	// 修改申请状态(管理员)
	public String updateStatus(String id, String status, String event) {
		
		String msg = applicationService.updateStatus(id, status, event);
		
		return msg;
	}

	//出校申请动态查询
	@RequestMapping(value = "/outDynamic")
	@ResponseBody
	public String outDynamic(String page, String limit, String school,String college, String major, String classes) {
		
		PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
		List<Application> data=applicationService.applicationDynamic(school,college, major, classes, ApplicationConstant.INOUT_OUT);
		PageInfo<Application> pageInfo = new PageInfo<>(data);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "0");
		map.put("msg", "");
		map.put("count", pageInfo.getTotal());
		map.put("data", data);
		return JsonUtils.objectToJson(map);
	}
	
	//入校申请动态查询
	@RequestMapping(value = "/inDynamic")
	@ResponseBody
	public String inDynamic(String page, String limit, String school,String college, String major, String classes) {
		
		
		PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
		List<Application> data=applicationService.applicationDynamic(school,college, major, classes, ApplicationConstant.INOUT_IN);
		PageInfo<Application> pageInfo = new PageInfo<>(data);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "0");
		map.put("msg", "");
		map.put("count", pageInfo.getTotal());
		map.put("data", data);
		return JsonUtils.objectToJson(map);
	}
	
	// 查找出校申请
	@ResponseBody
	@RequestMapping(value = "/outData", method = RequestMethod.POST)
	public String outData(String page, String limit, String major,String classes,String school,String college) {

		PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
		//List<Application> data = applicationService.findOutDataByCollege(school,college);
		List<Application> data=applicationService.applicationDynamic(school, college, major, classes, ApplicationConstant.INOUT_OUT);
		PageInfo<Application> pageInfo = new PageInfo<Application>(data);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "0");
		map.put("msg", "");
		map.put("count", pageInfo.getTotal());
		map.put("data", data);

		return JsonUtils.objectToJson(map);
	}

	// 查找入校申请
	@ResponseBody
	@RequestMapping(value = "/inData", method = RequestMethod.POST)
	public String inData(String page, String limit,String school,String college,String major,String classes) {
		//修改初始化------>>>
		PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
		//List<Application> data = applicationService.findInDataByCollege(school,college);
		List<Application> data=applicationService.applicationDynamic(school, college, major, classes, ApplicationConstant.INOUT_IN);
		PageInfo<Application> pageInfo = new PageInfo<Application>(data);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "0");
		map.put("msg", "");
		map.put("count", pageInfo.getTotal());
		map.put("data", data);

		return JsonUtils.objectToJson(map);
	}

	// 根据学号和姓名模糊查询进行分页
	@RequestMapping(value = "/fuzzyApplication",method = RequestMethod.POST)
	@ResponseBody
	public String fuzzyApllication(String school,String college,String snum, String sname,String inout, String limit, String page) {
		PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
		System.out.println("--------"+sname+"++++++");
		List<Application> data = applicationService.fuzzyAppliacation(school,college,snum,sname,inout);
		PageInfo<Application> pageInfo = new PageInfo<Application>(data);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "0");
		map.put("msg", "");
		map.put("count", pageInfo.getTotal());
		map.put("data", data);

		return JsonUtils.objectToJson(map);
	}

}
