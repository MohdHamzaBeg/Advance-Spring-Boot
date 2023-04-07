package com.udemy.springframework.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;

import com.udemy.springframework.models.BeerModel;

public interface BeerService {
	Page<BeerModel> listofBeers(String beerName, BigDecimal price, Integer pageNumber, Integer pageSize);
	Optional<BeerModel> getBeerbyId(int id);
	BeerModel addBeer(BeerModel beer);
	BeerModel updateBeer(BeerModel beer);
}
