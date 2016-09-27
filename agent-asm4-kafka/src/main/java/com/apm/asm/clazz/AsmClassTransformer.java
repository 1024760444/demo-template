package com.apm.asm.clazz;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import com.apm.asm.clazz.AsmClassVisitor;
import com.apm.asm.util.InterceptUtil;

/**
 * 类加载到内存的过程中，对满足条件的方法进行增强。
 * @author yanghaitao
 *
 */
public class AsmClassTransformer implements ClassFileTransformer {
	public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
			ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
		// 指定需要改造方法的大方向，如果不指定会过多嵌入。
		if (InterceptUtil.isBasic(className)) {
			ClassReader cr = new ClassReader(classfileBuffer);
			ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
			cr.accept(new AsmClassVisitor(cr, cw, className), ClassReader.SKIP_DEBUG);
			byte[] code = cw.toByteArray();
			return code;
		} 
		// 对于没有指定的包的类，不做任何改动。
		else {
			return classfileBuffer;
		}
	}
}
