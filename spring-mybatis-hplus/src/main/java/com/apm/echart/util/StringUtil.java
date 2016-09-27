package com.apm.echart.util;

/**
 * 字符串处理工具。
 * @author yanghaitao
 *
 */
public class StringUtil {
	/**
	 * 将字符串转换为数字，如果转换失败则返回默认数字。
	 * @param str 需要转换的字符串
	 * @param defalut 默认数字
	 * @return
	 */
	public static int strToInt(String str, int defalut) {
		int result = defalut;
		try {
			result = Integer.parseInt(str);
		} catch (Exception e) {
			result = defalut;
		}
		return result;
	}
}
