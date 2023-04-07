package com.udemy.springframework.springrestwebservice.repositoriestest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.udemy.springframework.bootstrap.BootstrapData;
import com.udemy.springframework.entities.Beer;
import com.udemy.springframework.repositories.BeerRepository;
import com.udemy.springframework.services.BeerCSVserviceImpl;

import jakarta.validation.ConstraintViolationException;

@DataJpaTest // The basic need of writing a test splice is to test JPA repositories in
				// isolation with including the whole project
@Import({BootstrapData.class, BeerCSVserviceImpl.class}) // since this class is a jpa test splice, we need to manually import the
														 // classes which we need to run the test
class BeerRepositoryTest {
	Pageable pageable;

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
	
	@Test
	void testGetBeerByName() {
		Page<Beer> list = beerRepository.findAllByBeerNameIsLikeIgnoreCase("%IPA%",pageable);
		
		assertThat(list.getContent().size()).isEqualTo(336);
	}
	@Test
	void testGetBeersByprice() {
		Page<Beer> list = beerRepository.findAllByPrice(BigDecimal.valueOf(23.67),pageable);
		
		assertThat(list.getContent().size()).isEqualTo(1);
	}
	@Test
	void testGetBeersByNameandPrice() {
		Page<Beer> list = beerRepository.findAllByBeerNameIsLikeIgnoreCaseAndPrice("%Kingfisher%", BigDecimal.valueOf(45.60),pageable);
		
		assertThat(list.getContent().size()).isEqualTo(1);
	}
}
