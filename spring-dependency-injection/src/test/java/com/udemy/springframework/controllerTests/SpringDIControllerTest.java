package com.udemy.springframework.controllerTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.udemy.springframework.controllerss.SpringDIcontroller;

@SpringBootTest
public class SpringDIControllerTest {
	
	@Autowired
	SpringDIcontroller sdic;
	
	@Test
	void sayHello() {
		System.out.println(sdic.sayHello());
	}
}
