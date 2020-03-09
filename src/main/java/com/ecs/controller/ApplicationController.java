package com.ecs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecs.common.DateUtil;
import com.ecs.domain.Application;
import com.ecs.service.ApplicationService;

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

	@RequestMapping("/addApplicationInfo")
	@ResponseBody
	// 上报申请
	public String addApplicationInfo(Application application) {
		// 得到日期
		application.setDate(DateUtil.getDate());
		applicationService.addApplicationInfo(application);
		return "成功";
	}

	@RequestMapping("/updateStatus")
	@ResponseBody
	// 修改申请状态(管理员)
	public String updateStatus(String status, String snum) {
		status = "2";
		snum = "201710913106";
		applicationService.updateStatus(status, snum, DateUtil.getDate());
		return "成功";
	}
}
