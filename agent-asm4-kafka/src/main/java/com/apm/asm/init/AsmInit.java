package com.apm.asm.init;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apm.asm.util.AsmKafkaClient;
import com.apm.asm.util.ToolsUtil;

/**
 * 初始化。
 * @author yanghaitao
 *
 */
public class AsmInit {
	
	/**
	 * 日志对象。
	 */
	private  static  final Logger logger = LoggerFactory.getLogger(AsmInit. class);
	
	/**
	 * 初始化必要参数。
	 */
	public static void init() {
		// 读取配置文件
		readParams();
		
		// 初始化kafka客户端
		ToolsUtil.client = new AsmKafkaClient(ToolsUtil.servers);
	}
	
	public static void readParams() {
		String path = System.getProperty("user.dir") + "/config.properties";
		logger.error("Configuration file full path : " + path);
		File configFile = new File(path);
		InputStream in = null;
		try {
			if(configFile.isFile()) {
				in = new FileInputStream(configFile);
			} else {
				in = ClassLoader.getSystemResourceAsStream("config.properties");
			}
			readConfig(in);
		} catch(Exception e) {
			logger.error("Read config file failed! ", e);
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 读取配置文件数据
	 * @throws IOException 
	 */
	public static void readConfig(InputStream in) throws IOException {
		// 加载配置文件
		Properties dbProps = new Properties();
		dbProps.load(in);

		// 读取Kafka服务器
		try {
			ToolsUtil.servers = dbProps.getProperty("kafka.servers");
		} catch (Exception e) {
			ToolsUtil.servers = "localhost:9092";
		}

		// 读取Kafka主题
		try {
			ToolsUtil.topic = dbProps.getProperty("kafka.topic");
		} catch (Exception e) {
			ToolsUtil.topic = "asm";
		}

		// 拦截阈值
		try {
			ToolsUtil.threshold = Integer.valueOf(dbProps.getProperty("intercept.threshold"));
		} catch (Exception e) {
			ToolsUtil.threshold = 10;
		}

		// 拦截基础包
		try {
			ToolsUtil.basicIntercept = dbProps.getProperty("intercept.basic.pkg");
		} catch (Exception e) {
			ToolsUtil.basicIntercept = null;
		}
	}
}
