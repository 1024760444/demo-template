package com.apm.asm.clazz;

import java.util.UUID;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import com.apm.asm.util.ToolsUtil;

/**
 * 自定义类的过滤对象AsmClassVisitor继承于ClassVisitor。
 * @author yanghaitao
 *
 */
public class AsmClassVisitor extends ClassVisitor {
	
	/**
	 * 类全名。
	 */
	private String className;
	
	/**
	 * 原始类的输入流。
	 */
	private ClassReader reader;
	
	/**
	 * 构造类的增强对象。
	 * @param reader 原始类的输入流
	 * @param cv 类写入对象
	 * @param className 类全名
	 */
	public AsmClassVisitor(ClassReader reader, ClassVisitor cv, String className) {
		super(Opcodes.ASM4, cv);
		this.className = className;
		this.reader = reader;
	}
	
	/**
	 * 方法增强。
	 * 类的增强其实就是属性增强或者方法增强。
	 */
	@Override
	public MethodVisitor visitMethod(int access, final String name, final String desc, String signature,
			String[] exceptions) {
		// 1、原始的方法对象。
		MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
		
		// 2、获取方法入参个数。
		final int paramsCount = ToolsUtil.getMethodLocalVariableCount(reader, name, desc);
		
		// 3、对于满足条件的方法进行增强。
		if(ToolsUtil.isIntercept(className, name)) {
			System.err.println("log : " + className + "." + name);
			mv = new MethodVisitor(Opcodes.ASM4, mv) {
				
				/**
				 * 1、定义其实结束标志。
				 */
				private Label start;
				private Label end;
				
				/**
				 * 2、方法执行之前。
				 */
				public void visitCode() {
					super.visitCode();
					start = new Label();
					mv.visitLabel(start);
					super.visitMethodInsn(Opcodes.INVOKESTATIC, Type.getInternalName(ToolsUtil.class), "startTime", "()Ljava/lang/Long;");
					super.visitVarInsn(Opcodes.ASTORE, paramsCount);
				}
				
				/**
				 * 3、方法执行之后，return之前。
				 */
				public void visitInsn(int opcode) {
					switch (opcode) {
					case Opcodes.IRETURN:
					case Opcodes.LRETURN:
					case Opcodes.FRETURN:
					case Opcodes.DRETURN:
					case Opcodes.ARETURN:
					case Opcodes.RETURN: {
						super.visitVarInsn(Opcodes.ALOAD, paramsCount);
						super.visitLdcInsn(className + "." + name);
						super.visitMethodInsn(Opcodes.INVOKESTATIC, Type.getInternalName(ToolsUtil.class),
								"intercept",
								"(Ljava/lang/Long;Ljava/lang/String;)V");
					}
						break;
					default:
					}
					super.visitInsn(opcode);
					
					/**
					 * 4、声明局部变量。
					 */
					end = new Label();
					mv.visitLabel(end);
					mv.visitLocalVariable(UUID.randomUUID().toString(), "Ljava/lang/Long;", null, start, end, paramsCount);
					mv.visitEnd();
				}
				
				public void visitLocalVariable(String name, String desc, String signature, Label start, Label end,
						int index) {
					super.visitLocalVariable(name, desc, signature, start, end, index);
				}
			};
		}
		
		// 4、返回改造或者没有改造的方法。
		return mv;
	}

}
