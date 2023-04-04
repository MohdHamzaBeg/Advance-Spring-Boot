package com.udemy.springframework.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.udemy.springframework.entities.Beer;
import com.udemy.springframework.models.BeerModel;

@Mapper 
public interface BeerMapper {
	Beer beerModeltoBeer(BeerModel beerModel);
	BeerModel beertoBeerModel(Beer beer);
}
