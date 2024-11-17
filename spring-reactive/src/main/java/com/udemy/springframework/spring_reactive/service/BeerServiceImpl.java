package com.udemy.springframework.spring_reactive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.udemy.springframework.spring_reactive.entity.Beer;
import com.udemy.springframework.spring_reactive.repository.BeerRepository;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

	private final BeerRepository beerRepository;
	
	@Override
	public Flux<Beer> listBeers() {
		return beerRepository.findAll();
	}

	@Override
	public Mono<Beer> getbyId(Integer id) {
		return beerRepository.findById(id);
	}

	@Override
	public Mono<Beer> save(Beer beer) {
		return beerRepository.save(beer);
	}

	@Override
	public Mono<Beer> update(Integer id, Beer beer) {
		return beerRepository.findById(id)
				.map(foundBeer ->{
					foundBeer.setBeerName(beer.getBeerName());
					foundBeer.setPrice(beer.getPrice());
					return foundBeer;
				}).flatMap(beerRepository::save);
	}

	@Override
	public Mono<Void> delete(Integer id) {
		return beerRepository.deleteById(id);
	}

}
