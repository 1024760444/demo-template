package com.yhaitao.manager.util;

import java.math.BigDecimal;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 常量定义
 * @author yanghaitao
 *
 */
public class Cons {
	/**
	 * 项目基本地址
	 */
	public static String domain = "http://localhost:8080/docs-manager/";
	
	/**
	 * 文件保存的基础路径。
	 */
	public static String basePath = "F:/works/docs-manager/doc/";
	
	/**
	 * JSON格式化对象。
	 */
	public static Gson gson = new GsonBuilder().create();
	
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
	
	/**
	 * 保存小数两位
	 * @param data
	 * @return
	 */
	public static double decimalTwo(double data) {
		BigDecimal bd = new BigDecimal(data);
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		return bd.doubleValue();
	}
	
	public static void main(String[] args) {
		System.err.println(decimalTwo(3.1455789));
	}
}
