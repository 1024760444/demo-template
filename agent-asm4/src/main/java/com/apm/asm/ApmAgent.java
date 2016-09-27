package com.apm.asm;

import java.lang.instrument.Instrumentation;

/**
 * 代理入口。
 * @author yanghaitao
 *
 */
public class ApmAgent {
	/**
	 * 类加载到内存中需要做的事情。
	 * @param args
	 * @param inst
	 */
	public static void premain(String args, Instrumentation inst) {
		System.out.println("start agent ................... ");
		inst.addTransformer(new ApmClassFileTransformer());
	}
}
