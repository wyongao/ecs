package com.ecs.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static String getDate() {
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(System.currentTimeMillis());
		System.out.println("时间工具类调用成功-------->>>");
		return formatter.format(date);
	}

}
