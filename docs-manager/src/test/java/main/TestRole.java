package main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yhaitao.manager.service.UserService;

public class TestRole {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-mybatis.xml");
		UserService userService = (UserService) context.getBean("userService");
			userService.service();
		context.close();
	}
}
