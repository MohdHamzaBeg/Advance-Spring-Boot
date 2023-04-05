package com.udemy.springframework.springrestwebservice.repositoriestest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.udemy.springframework.entities.Beer;
import com.udemy.springframework.repositories.BeerRepository;

import jakarta.validation.ConstraintViolationException;

@DataJpaTest // The basic need of writing a test splice is to test JPA repositories in
				// isolation with including the whole project
class BeerRepositoryTest {

	@Autowired
	BeerRepository beerRepository;

	@Test
	void testsavebeer() {
		Beer savedBeer = beerRepository.save(Beer.builder().beerName("New Beer").price(new BigDecimal(43434)).build());

		beerRepository.flush();
		assertThat(savedBeer).isNotNull();
		assertThat(savedBeer.getId()).isNotNull();
	}

	@Test
	void testsavebeernameTooLong() {
		assertThrows(ConstraintViolationException.class, () -> {
			Beer savedBeer = beerRepository.save(Beer.builder().beerName(
					"ajfldsafjadsljfdsaljfldsajfldsjfldsajfslafjklasdjlfasdfjadskljfladsjfsdafjklfjsakldfjaskjfksldafjakldsfklasjdflkasfjdkladafkjslaskldfjslfjaslfj")
					.price(new BigDecimal(43434)).build());

			beerRepository.flush();
		});
	}

}
