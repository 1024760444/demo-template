package com.apm.asm;

import java.lang.instrument.Instrumentation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apm.asm.clazz.AsmClassTransformer;
import com.apm.asm.init.AsmInit;

/**
 * 代理入口。
 * @author yanghaitao
 *
 */
public class Agent {
	/**
	 * 日志对象。
	 */
	private  static  final Logger logger = LoggerFactory.getLogger(Agent. class);
	
	/**
	 * 类加载到内存中需要做的事情。
	 * @param args
	 * @param inst
	 */
	public static void premain(String args, Instrumentation inst) {
		// 初始化参数
		AsmInit.init();
		
		// 增强需要监控的方法
		logger.info("start agent ................... ");
		inst.addTransformer(new AsmClassTransformer());
	}
}
