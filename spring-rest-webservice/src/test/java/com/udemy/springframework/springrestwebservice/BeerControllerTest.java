package com.udemy.springframework.springrestwebservice;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.udemy.springframework.controllerss.BeerController;

@SpringBootTest
public class BeerControllerTest {
	@Autowired
	BeerController beerController;
	
	@Test
	void getbeerbyid() {
		System.out.println(beerController.getBeerById(UUID.randomUUID()));
	}
}
