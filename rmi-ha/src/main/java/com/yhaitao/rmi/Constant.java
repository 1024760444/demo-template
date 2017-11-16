package com.yhaitao.rmi;

/**
 * 静态变量定义。
 * @author admin
 *
 */
public class Constant {
	/**
	 * ZK地址列表
	 */
	public static String ZK_HOSTS = "172.19.10.5:2181";
	
	/**
	 * 连接ZK的超时时间
	 */
	public static int ZK_CONN_TIMEOUT = 5000;
	
	/**
	 * 注册RMI服务的基础路径
	 */
	public static String ZK_RMI_BASIS = "/rmi/register";
	
	/**
	 * RMI路径
	 */
	public static String ZK_RMI_PATH = "/rmi";
}
