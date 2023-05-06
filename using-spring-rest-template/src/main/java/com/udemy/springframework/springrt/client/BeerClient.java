package com.udemy.springframework.springrt.client;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;

import com.udemy.springframework.springrt.model.BeerModel;

public interface BeerClient {
	Page<BeerModel> getBeerslist(String BeerName);
	
	BeerModel getBeerbyId(Long id);
	
	BeerModel createBeer(BeerModel beerModel);
	
	BeerModel updateBeer(BeerModel beerModel, Long beerid);
}
