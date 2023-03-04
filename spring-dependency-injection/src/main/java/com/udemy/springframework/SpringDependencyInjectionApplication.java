package com.udemy.springframework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.udemy.springframework.controllerss.MyController;

@SpringBootApplication
public class SpringDependencyInjectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDependencyInjectionApplication.class, args);
		//MyController controller = ctx.getBean(MyController.class);
		//System.out.println("Running spring Application manually");
		//System.out.println(controller.sayHello());
	}
}