package com.udemy.springframework.services;

import java.util.List;
import java.util.UUID;

import com.udemy.springframework.entities.Beer;

public interface BeerService {
	List<Beer> listofBeers();
	Beer getBeerbyId(UUID id);
}
