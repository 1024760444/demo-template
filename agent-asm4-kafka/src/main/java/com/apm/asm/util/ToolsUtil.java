package com.apm.asm.util;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 工具类。
 * @author yanghaitao
 *
 */
public class ToolsUtil {
	private  static  final Logger logger = LoggerFactory.getLogger(ToolsUtil. class);
	public static AsmKafkaClient client = null;
	public static String servers = "localhost:9092";
	public static String topic = "asm";
	public static long threshold = 10;
	
	public static String basicIntercept = "asm4";
	
	/**
	 * 方法之前执行，获取当前时间戳。
	 * @return 当前时间戳
	 */
	public static Long startTime() {
		return System.currentTimeMillis();
	}
	
	/**
	 * 拦截数据。
	 * @param timer 方法起始时间戳
	 * @param key 方法全名
	 */
    public static void intercept(Long timer, String key) {
    	try {
	    	long expend = System.currentTimeMillis() - timer;
	    	if(expend >= threshold) {
		    	String data = key + "|" + timer + "|" + expend;
		    	client.save(topic, data);
	    	}
    	} catch (Exception e) {
    		logger.error("ASM4 : Failed to collect data ： " + e.getMessage(), e);
    	}
    }
    
    /**
     * 获取方法的入参个数。
     * @param reader 当前类输入流
     * @param methodName 方法名称
     * @param methodDesc 方法描述
     * @return 入参个数
     */
	public static int getMethodLocalVariableCount(ClassReader reader, final String methodName, final String methodDesc) {
		final LocalVariableCount localVariableCount = new LocalVariableCount();
		reader.accept(new ClassVisitor(Opcodes.ASM4) {
			@Override
			public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
				MethodVisitor methodVisitor = super.visitMethod(access, name, desc, signature, exceptions);
				if (name.equals(methodName) && desc.equals(methodDesc))
					return new MethodVisitor(Opcodes.ASM4, methodVisitor) {
						@Override
						public void visitMaxs(int maxStack, int maxLocals) {
							localVariableCount.setCount(maxLocals);
							super.visitMaxs(maxStack, maxLocals);
						}
					};
				else
					return methodVisitor;
			}
		}, ClassReader.SKIP_FRAMES);
		return localVariableCount.getCount();
	}
	
	/**
	 * 参数个数对象定义。
	 * @author yanghaitao
	 *
	 */
	public static class LocalVariableCount {
		private int count = -1;

		public int getCount() {
			return count;
		}

		private void setCount(int count) {
			this.count = count;
		}
	}
}
