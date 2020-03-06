package com.ecs.WXController;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecs.common.HttpClientUtil;
import com.ecs.common.JsonUtils;
import com.ecs.common.MyParams;
import com.ecs.domain.WXSession;

@Controller
public class WXLoginController {
	
	@Autowired
	MyParams myParams;
	
	@ResponseBody
	@RequestMapping(value="/wxLogin", method=RequestMethod.POST)
	public String wxLogin (String code) {
		
//		https://api.weixin.qq.com/sns/jscode2session?
//		appid=APPID&
//		secret=SECRET&
//		js_code=JSCODE&
//		grant_type=authorization_code
		
		String url = "https://api.weixin.qq.com/sns/jscode2session";
		Map<String, String> param = new HashMap<>();
		String appid = myParams.getAppid();
		String secret = myParams.getSecret();

		param.put("appid", appid);
		param.put("secret", secret);
		param.put("js_code", code);
		param.put("grant_type", "authorization_code");
		
		String wxResult = HttpClientUtil.doGet(url, param);
		System.out.println(wxResult);
		
		WXSession wxSession = JsonUtils.jsonToPoJo(wxResult, WXSession.class);		
		System.out.println(wxSession.getOpenid());
		System.out.println(wxSession.getSession_key());
		
		return "wya";
	}
}
