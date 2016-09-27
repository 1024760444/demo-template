package com.yhaitao.manager.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间戳处理工具。
 * @author yanghaitao
 *
 */
public class TimerUtil {
	/**
	 * 一般时间规范模型。
	 */
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 时间格式yyyyMMdd模型
	 */
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	
	/**
	 * 获取当前系统时间，时间格式：yyyy-MM-dd HH:mm:ss。
	 * @return 当前系统时间
	 */
	public static String getSystemDate() {
		Calendar calendar = Calendar.getInstance();
		return format.format(calendar.getTime());
	}
	
	/**
	 * 根据指定的时间字符串，解析获取yyyyMMdd类型的时间字符串。
	 * @throws ParseException 
	 */
	public static String getDateFormat(String timeStamp) throws ParseException {
		Date parse = format.parse(timeStamp);
		return dateFormat.format(parse);
	}
}
