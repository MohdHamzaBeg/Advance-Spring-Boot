package com.udemy.springframework.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.udemy.springframework.models.BeerModel;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//@Service
public class BeerServiceImpl implements BeerService{
	private final Map<UUID, BeerModel> beermap;

	public BeerServiceImpl() {
		this.beermap = new HashMap<UUID, BeerModel>();

		//log.debug("Get BeerModel Id from service");
		final BeerModel beer1 = BeerModel.builder()		// All the beers are built here as write now we are not using any JPA repository
				.id(UUID.randomUUID())			// and hence we are saving 3 bears inside a hashmap
				.beerName("Kingfisher")
				.price(new BigDecimal(456545))
				.mfg(LocalDateTime.now())
				.exp(LocalDateTime.now())
				.build();
		
		final BeerModel beer2 = BeerModel.builder()
				.id(UUID.randomUUID())			// Beers are saved in constructor since at the time of wiring this service, we could
				.beerName("RedBull")			// be able to access all the beers from this service only.
				.price(new BigDecimal(7545))
				.mfg(LocalDateTime.now())
				.exp(LocalDateTime.now())
				.build();
		
		final BeerModel beer3= BeerModel.builder()
				.id(UUID.randomUUID())
				.beerName("Tuborg")
				.price(new BigDecimal(4445))
				.mfg(LocalDateTime.now())
				.exp(LocalDateTime.now())
				.build();
		
		beermap.put(beer1.getId(), beer1);
		beermap.put(beer2.getId(), beer2);
		beermap.put(beer3.getId(), beer3);
	}
	
	@Override
	public List<BeerModel> listofBeers(){
		return new ArrayList<BeerModel>(beermap.values());
	}
	@Override
	public Optional<BeerModel> getBeerbyId(UUID id) {
		return Optional.of(beermap.get(id));
	}

	@Override
	public BeerModel addBeer(BeerModel beer) {
		BeerModel savedBeer = BeerModel.builder()
				.id(UUID.randomUUID())
				.mfg(LocalDateTime.now())
				.exp(LocalDateTime.now())
				.beerName(beer.getBeerName())
				.price(beer.getPrice())
				.build();
		beermap.put(savedBeer.getId(), savedBeer);
		return savedBeer;
	}

	@Override
	public BeerModel updateBeer(BeerModel beer) {
		beer.setMfg(LocalDateTime.now());
		beer.setExp(LocalDateTime.now());
		beermap.put(beer.getId(), beer);
		return beer;
	}
}
