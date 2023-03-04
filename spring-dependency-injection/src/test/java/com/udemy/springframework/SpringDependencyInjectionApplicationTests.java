package com.udemy.springframework;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.udemy.springframework.controllerss.MyController;

@SpringBootTest
class SpringDependencyInjectionApplicationTests {
	
	@Autowired
	ApplicationContext ctx;
	
	@Autowired
	MyController controller;
	
	@Test
	void testGAutoWiredinstances() {
		System.out.println(controller.sayHello());
	}
	
	//@Test
	void contextLoads() {
	}

}
