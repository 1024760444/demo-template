package com.apm.echart.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 时间戳工具类。
 * @author yanghaitao
 *
 */
public class TimerUtil {
	/**
	 * 根据指定格式获取当前系统时间。
	 * @param pattern 时间戳格式，例如：yyyy-MM-dd HH:mm:ss
	 * @return 时间戳字符串
	 */
	public static String getSystemTime(String pattern) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(calendar.getTime());
	}
	
	/**
	 * 功能描述： 获取系统时间，格式为： yyyy-MM-dd HH:mm:ss 。
	 * @author:yanghaitao
	 * @return String 
	 * 2016年8月18日 下午2:57:41
	 */
	public static String getSystemFormat() {
		return getSystemTime("yyyy-MM-dd HH:mm:ss");
	}
}
