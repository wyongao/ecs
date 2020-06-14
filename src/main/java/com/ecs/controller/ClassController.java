package com.ecs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecs.common.JsonUtils;
import com.ecs.domain.Class;
import com.ecs.service.ClassService;
/**
 * classcontroller
 * @author xuluyang
 *
 * 2020年3月5日
 */

@Controller
public class ClassController {
	@Autowired
	private ClassService classService;

	@RequestMapping(value = "/findAllClassesByParentId", method = RequestMethod.POST)
	@ResponseBody
	public String findAllClassesByParentId(Integer parentid, Model model) {
		List<Class> list = classService.findAllClassByParentId(parentid);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data1", list);
		System.out.println(JsonUtils.objectToJson(map));
		return JsonUtils.objectToJson(map);
	}
	
}
