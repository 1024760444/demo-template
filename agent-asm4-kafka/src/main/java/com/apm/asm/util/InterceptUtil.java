package com.apm.asm.util;

/**
 * 增强规则定义。
 * @author yanghaitao
 *
 */
public class InterceptUtil {

    /**
     * 包级别的增强筛选。 于AsmClassTransformer类中做初略筛选。
     * @param className 类全名
     * @return 匹配返回true，否则返回false。
     */
    public static boolean isBasic(String className) {
    	
    	// 如果没有设置基础的增强规则，默认所有都不增强
    	if(ToolsUtil.basicIntercept == null 
    			|| ToolsUtil.basicIntercept.equals("")) {
    		 return false;
    	}
    	
    	// 对于以基础包增强包开头的类才能拦截。
    	String[] paks = ToolsUtil.basicIntercept.split("\\&");
    	String replaceAll = className.replaceAll("\\/", "\\.");
    	for(String pak : paks) {
    		if(replaceAll.startsWith(pak.trim())) {
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     * 方法级别的增强筛选。于AsmClassVisitor类中做精细筛选。
     * 1、对于类名字包含"$" 或者 "lock"表示锁有关的类，不能增强。
     * 2、对于方法包含"<"或者">"等java编译器生成的特殊方法，默认不增强。
     * @param className 方法所在类的全名
     * @param methodName 方法名称
     * @return 需要增强返回true；否则false。
     */
    public static boolean isIntercept(String className, String methodName) {
    	if(className.contains("$") || className.contains("lock")) {
    		return false;
    	} 
    	if(methodName.contains("<") || methodName.contains(">")) {
    		return false;
    	}
    	return true;
    }
    
}
