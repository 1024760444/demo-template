package main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yhaitao.dubbo.service.DemoService;

public class Main {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/bean.xml");
		context.start();
		DemoService demoService = (DemoService) context.getBean("demoService");
		try {
			long start = System.currentTimeMillis();
			for(int i = 0; i < 10000; i++) {
				String build = demoService.build(String.valueOf(i));
				System.out.println(build);
			}
			System.out.println("Cost time : " + (System.currentTimeMillis() - start));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
