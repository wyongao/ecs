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
