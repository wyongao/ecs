package com.ecs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecs.common.DateUtil;
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

	@RequestMapping("/addApplicationInfo")
	@ResponseBody
	// 上报申请
	public String addApplicationInfo(Application application) {
		// 得到日期
		application.setDate(DateUtil.getDate());
		applicationService.addApplicationInfo(application);
		return "成功";
	}
	//管理员根据学院专业查询申请状态
	@RequestMapping("/findAllApplicationByCollegeAndMajor")
	@ResponseBody
	public String findAllApplicationByCollegeAndMajor(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,String college,String major) {
		college="计算机学院";
		major="计算机科学与技术";
		PageHelper.startPage(pageNum, Constant.PAGE_SIZE);
		List<Application> list=applicationService.findAllByCollegeAndMajor(college, major);
		PageInfo<Application> pageInfo=new PageInfo<Application>(list);
		System.out.println("总页数"+pageInfo.getPages()+"当前页"+pageInfo.getPageNum()+"总记录数"+pageInfo.getTotal());
		return "成功";
	}
	//管理员根据学院查询申请的人
		@RequestMapping("/findAllApplicationByCollege")
		@ResponseBody
		public String findAllApplicationByCollege(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,String college) {
			college="计算机学院";
			PageHelper.startPage(pageNum, Constant.PAGE_SIZE);
			List<Application> list=applicationService.findAllByCollege(college);
			PageInfo<Application> pageInfo=new PageInfo<Application>(list);
			System.out.println("总页数"+pageInfo.getPages()+"当前页"+pageInfo.getPageNum()+"总记录数"+pageInfo.getTotal());
			return "成功";
		}
		//管理员根据学院专业班级查询申请的人
				@RequestMapping("/findAllApplicationByCollegeAndMajorAndClasses")
				@ResponseBody
				public String findAllApplicationByCollegeAndMajorAndClasses(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,String college,String major,String classes) {
					college="理学院";
					major="物理";
					classes="1742";
					PageHelper.startPage(pageNum, Constant.PAGE_SIZE);
					List<Application> list=applicationService.findAllByCollegeAndMajorAndClasses(college, major, classes);
					PageInfo<Application> pageInfo=new PageInfo<Application>(list);
					System.out.println("总页数"+pageInfo.getPages()+"当前页"+pageInfo.getPageNum()+"总记录数"+pageInfo.getTotal());
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
