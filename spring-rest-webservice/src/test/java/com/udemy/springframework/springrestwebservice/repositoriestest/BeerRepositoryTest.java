package com.udemy.springframework.springrestwebservice.repositoriestest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.udemy.springframework.entities.Beer;
import com.udemy.springframework.repositories.BeerRepository;

@DataJpaTest
class BeerRepositoryTest {
	
	@Autowired
	BeerRepository beerRepository;
	
	@Test
	void testsavebeer() {
		Beer savedBeer = beerRepository.save(Beer.builder().beerName("New Beer").build());
		
		assertThat(savedBeer).isNotNull();
		assertThat(savedBeer.getId()).isNotNull();
	}

}
