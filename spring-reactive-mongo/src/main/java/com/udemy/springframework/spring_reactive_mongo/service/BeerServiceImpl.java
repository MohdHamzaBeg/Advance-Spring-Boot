package com.udemy.springframework.spring_reactive_mongo.service;

import org.springframework.stereotype.Service;

import com.udemy.springframework.spring_reactive_mongo.entity.Beer;
import com.udemy.springframework.spring_reactive_mongo.repository.BeerRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

	private final BeerRepository beerRepository;
	
	@Override
	public Mono<Beer> save(Mono<Beer> beer) {
		return beer.flatMap(beerRepository::save);
	}

	@Override
	public Mono<Beer> getbyId(String beerId) {
		return beerRepository.findById(beerId);
	}

	@Override
	public Flux<Beer> findbybeername(String beerName) {
		return beerRepository.findAllByBeerName(beerName);
	}

	@Override
	public Flux<Beer> listofbeers() {
		return beerRepository.findAll();
	}

	@Override
	public Mono<Beer> update(String beerId, Beer beer) {
		return beerRepository.findById(beerId)
				.map(foundBeer->{
					foundBeer.setBeerName(beer.getBeerName());
					foundBeer.setPrice(beer.getPrice());
					foundBeer.setCreatedDate(beer.getCreatedDate());
					foundBeer.setModifiedDate(beer.getModifiedDate());
					return foundBeer;
				}).flatMap(beerRepository::save);
	}

	@Override
	public Mono<Void> delete(String beerId) {
		return beerRepository.deleteById(beerId);
	}

}
