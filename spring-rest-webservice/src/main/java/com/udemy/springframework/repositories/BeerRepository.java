package com.udemy.springframework.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udemy.springframework.entities.Beer;

public interface BeerRepository extends JpaRepository<Beer, Integer> {
	Page<Beer> findAllByBeerNameIsLikeIgnoreCase(String beerName, Pageable pageable);// JPA provides advantage to write query string as method name
													// so that we can fetch data according to our need
	Page<Beer> findAllByPrice(BigDecimal price, Pageable pageable);
	
	Page<Beer> findAllByBeerNameIsLikeIgnoreCaseAndPrice(String beerName, BigDecimal price, Pageable pageable);
}
