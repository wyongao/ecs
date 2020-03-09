package com.ecs.WXController;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Daily {
	
	@ResponseBody
	@RequestMapping(value="/daily", method=RequestMethod.POST)
	String daily (@RequestBody Map<String, String> data) {
		System.out.println(data);
		System.out.println(data.values());
		System.out.println(data.get("temp"));
		System.out.println(data.get("desc"));
		return "success";
	}
	
}
