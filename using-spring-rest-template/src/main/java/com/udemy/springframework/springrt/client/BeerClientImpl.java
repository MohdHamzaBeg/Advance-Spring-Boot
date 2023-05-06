 package com.udemy.springframework.springrt.client;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.udemy.springframework.springrt.model.BeerModel;
import com.udemy.springframework.springrt.model.RestPageImpl;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BeerClientImpl implements BeerClient{
	
	private final RestTemplateBuilder restTemplateBuilder;
	
	
	
	@Override
	public Page<BeerModel> getBeerslist(String BeerName) {
		RestTemplate restTemplate = restTemplateBuilder.build();
		
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath("/beers/list");
		// This allows us to build the path to include query parameters
		
		if(BeerName!=null) {
			uriComponentsBuilder.queryParam("beerName", BeerName);
		}
		ResponseEntity<RestPageImpl> responseEntity = restTemplate.getForEntity(uriComponentsBuilder.toUriString(), RestPageImpl.class);
		
		return responseEntity.getBody();
	}

	@Override
	public BeerModel getBeerbyId(Long id) {
		RestTemplate restTemplate = restTemplateBuilder.build();
		return restTemplate.getForObject("/beers/{id}", BeerModel.class, id);
	}
	
	@Override
	public BeerModel createBeer(BeerModel beerModel) {
		RestTemplate restTemplate = restTemplateBuilder.build();
		
		URI uri = restTemplate.postForLocation("/beers/saveBeer", beerModel, BeerModel.class);
		
		return restTemplate.getForObject(uri.getPath(), BeerModel.class);
	}
	
	@Override
	public BeerModel updateBeer(BeerModel beerModel, Long beerid) {
		RestTemplate restTemplate = restTemplateBuilder.build();
		restTemplate.put("/beers/update/{beerid}", beerModel, beerid);
		
		return getBeerbyId(beerid);
	}

}
