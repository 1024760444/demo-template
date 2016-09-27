package com.yhaitao.dubbo.service.impl;

import com.yhaitao.dubbo.service.DemoService;

/**
 * 业务实现。
 * @author yanghaitao
 *
 */
public class DemoServiceImpl implements DemoService {

	@Override
	public String build(String name) throws Exception {
		String test = name + " :  Hello, DemoService !";
		System.out.println(test);
		return test;
	}

}
