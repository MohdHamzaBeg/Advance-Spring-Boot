package com.udemy.springframework.springrestwebservice.controllerstest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.udemy.springframework.controllerss.BeerController;
import com.udemy.springframework.entities.Beer;
import com.udemy.springframework.models.BeerModel;
import com.udemy.springframework.repositories.BeerRepository;

@SpringBootTest
public class BeerControllerIntegrationTest {
	@Autowired
	BeerController beerController;
	
	@Autowired
	BeerRepository beerRepository;
	
	@Test
	void testlistofbeers() {
		List<BeerModel> beermodels = beerController.getBeersList();
		
		assertThat(beermodels.size()).isEqualTo(3);
	}
	
	@Test
	void getbeerbyid() {
		//Beer beer = beerRepository.findAll().get(1);
		
		BeerModel beerModel = beerController.getBeerById(1);
		
		assertThat(beerModel).isNotNull();
	}
}
