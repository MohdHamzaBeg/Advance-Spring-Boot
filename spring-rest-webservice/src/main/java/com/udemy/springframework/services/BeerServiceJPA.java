package com.udemy.springframework.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.udemy.springframework.mappers.BeerMapper;
import com.udemy.springframework.models.BeerModel;
import com.udemy.springframework.repositories.BeerRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@Primary
@RequiredArgsConstructor
public class BeerServiceJPA implements BeerService{

	private final BeerRepository beerRepository;
	
	@Autowired
	private final BeerMapper beerMapper;

	@Override
	public List<BeerModel> listofBeers() {
		return beerRepository.findAll()
				.stream() // Converting the entities returned by findAll method to beerModels, which is the required return type of this method
				.map(beerMapper::beertoBeerModel)
				.collect(Collectors.toList());
	}
	@Override
	public Optional<BeerModel> getBeerbyId(long id) {
		return Optional.ofNullable(beerMapper.beertoBeerModel(beerRepository.findById(id).orElse(null)));
	}

	@Override
	public BeerModel addBeer(BeerModel beer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BeerModel updateBeer(BeerModel beer) {
		// TODO Auto-generated method stub
		return null;
	}

}
