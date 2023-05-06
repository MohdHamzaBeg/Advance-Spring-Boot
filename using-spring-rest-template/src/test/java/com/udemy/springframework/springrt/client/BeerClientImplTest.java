package com.udemy.springframework.springrt.client;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.udemy.springframework.springrt.client.BeerClient;
import com.udemy.springframework.springrt.client.BeerClientImpl;
import com.udemy.springframework.springrt.model.BeerModel;

// This class actually runs the beerClient and can access to the original database

@SpringBootTest
public class BeerClientImplTest {

	@Autowired
	BeerClientImpl beerClient;

	//@Test
	void listBeers() {
		beerClient.getBeerslist("ALE");
	}
	
	//@Test
	void getBeerbyid() {
		Page<BeerModel> beerModels = beerClient.getBeerslist("ALE");
		BeerModel model = beerModels.getContent().get(0);
		BeerModel beerbyid = beerClient.getBeerbyId(model.getId());
		System.out.println(beerbyid.getBeerName());
	}
	
	@Test
	void createBeer() {
		BeerModel beerModel = BeerModel.builder()
				.beerName("New Fucking Beer")
				.price(BigDecimal.valueOf(20.767))
				.createdDate(LocalDateTime.now())
				.build();
		BeerModel savedBeer = beerClient.createBeer(beerModel);
		assertNotNull(savedBeer);
	}
	//@Test
	void updateBeer() {
		Page<BeerModel> beerModels = beerClient.getBeerslist("ALE");
		BeerModel model = beerModels.getContent().get(0);
		BeerModel beerModel = BeerModel.builder()
				.beerName("New Fucking Beer")
				.price(BigDecimal.valueOf(20.767))
				.createdDate(LocalDateTime.now())
				.build();
		
		
		BeerModel updatedBeer = beerClient.updateBeer(beerModel, model.getId());
		assertNotNull(updatedBeer);
		
	}
}
