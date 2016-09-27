package asm4;

import com.apm.asm.util.ToolsUtil;

/**
 * 测试类。
 * @author yanghaitao
 */
public class Test {
	public static void main(String[] args) {
		long uuid = ToolsUtil.startTime();
		System.out.println("Hello World!");
		ToolsUtil.intercept(uuid, "class name + method name");
	}
}
