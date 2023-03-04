package com.udemy.springframework.controllerTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.udemy.springframework.controllerss.fauxController;

@SpringBootTest
public class fauxControllerTest {
	
	@Autowired
	fauxController fc;
	
	@Test
	void getDataSource() {
		System.out.println(fc.getDatasource());
	}
}
