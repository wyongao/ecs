package com.ecs.WXController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecs.common.EncryptUtil;
import com.ecs.common.HttpClientUtil;
import com.ecs.common.JsonUtils;
import com.ecs.common.MyParams;
import com.ecs.domain.WXSession;

@Controller
public class WXLoginController {

	@Autowired
	private StringRedisTemplate redis1StringRedisTemplate;

	@Autowired
	private MyParams myParams;

	private EncryptUtil encryptUtil;
	
	@GetMapping("redis")
	@ResponseBody
	@RequestMapping(value = "/wxLogin")
	public Map<String, Object> wxLogin(String code,String session_id) {
		
		Map<String, Object> res = new HashMap<String, Object>();
		
		if(redis1StringRedisTemplate.hasKey(session_id)) {
			System.out.println("111");
		}else {
			System.out.println("222");
		}
		
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
//		System.out.println(wxResult);

		WXSession wxSession = JsonUtils.jsonToPoJo(wxResult, WXSession.class);
//		System.out.println(wxSession.getOpenid());
//		System.out.println(wxSession.getSession_key());

		String sessionId = encryptUtil.encodeMD5Hex(wxSession.getOpenid());
		String value = wxSession.getOpenid()+"&"+wxSession.getSession_key();
//		System.out.println(sessionId);
//		System.out.println(value);
		
		
		//参数: key,value,时间,时间单位（默认毫秒）
//		redis1StringRedisTemplate.opsForValue().set(sessionId, value, 1, TimeUnit.DAYS);
//		System.out.println(redis1StringRedisTemplate.opsForValue().get(sessionId));
		
		res.put("msg", "success");
		res.put("sessionId", sessionId);
		
		
		return res;
	}
}
