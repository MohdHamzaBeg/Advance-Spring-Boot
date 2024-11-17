package com.udemy.springframework.spring_reactive;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;

import com.udemy.springframework.spring_reactive.config.DatabaseConfig;
import com.udemy.springframework.spring_reactive.entity.Beer;
import com.udemy.springframework.spring_reactive.repository.BeerRepository;

@DataR2dbcTest
@Import(DatabaseConfig.class)
public class BeerRepsoitoryTest {

	@Autowired
	BeerRepository beerRepository;
	
	@Test
	void saveNewbeerTest() {
		beerRepository.save(testBeer())
			.subscribe(beer -> {
				System.out.println(beer.toString());
			});
	}
	
	Beer testBeer() {
		return Beer.builder()
				.beerName("Tuborg")
				.price(new BigDecimal(334))
				.build();
	}
}
