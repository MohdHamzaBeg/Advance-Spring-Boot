package com.udemy.springframework.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.udemy.springframework.models.BeerModel;

public interface BeerService {
	List<BeerModel> listofBeers();
	Optional<BeerModel> getBeerbyId(long id);
	BeerModel addBeer(BeerModel beer);
	BeerModel updateBeer(BeerModel beer);
}
