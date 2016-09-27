package com.apm.asm;

import java.lang.instrument.Instrumentation;

public class ApmAgent {
	public static void premain(String args, Instrumentation inst) {
		System.out.println("start agent ................... ");
		inst.addTransformer(new ApmClassFileTransformer());
	}
}
