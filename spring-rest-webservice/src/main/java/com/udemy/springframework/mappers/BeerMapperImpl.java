package com.udemy.springframework.mappers;

import org.springframework.stereotype.Service;

import com.udemy.springframework.entities.Beer;
import com.udemy.springframework.models.BeerModel;

@Service
public class BeerMapperImpl implements BeerMapper{

	@Override
	public Beer beerModeltoBeer(BeerModel beerModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BeerModel beertoBeerModel(Beer beer) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
