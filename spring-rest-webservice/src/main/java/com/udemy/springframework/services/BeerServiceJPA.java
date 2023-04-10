package com.udemy.springframework.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.udemy.springframework.entities.Beer;
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
	
	
	private final BeerMapper beerMapper;
	
	private final static int DEFAULT_PAGE_NUMBER=0;
	private final static int DEFAULT_PAGE_SIZE=25;
	@Override
	public Page<BeerModel> listofBeers(String beerName, BigDecimal price, Integer pageNumber, Integer pageSize) {
		
		PageRequest pageRequest = buildpageRequest(pageNumber, pageSize);
		
		Page<Beer> beerpage;
		if(StringUtils.hasText(beerName)&&price==null) {
			beerpage = beerRepository.findAllByBeerNameIsLikeIgnoreCase("%"+beerName+"%",pageRequest);
		}
		else if(StringUtils.hasText(beerName)&&price!=null) {
			beerpage = beerRepository.findAllByBeerNameIsLikeIgnoreCaseAndPrice(beerName, price,pageRequest);
		}
		else if(price!=null &&!StringUtils.hasText(beerName)) {
			beerpage = beerRepository.findAllByPrice(price,pageRequest);
		}
		else {
			beerpage = beerRepository.findAll(pageRequest);
		}

		return beerpage.map(beerMapper::beertoBeerModel);
	}
	
	public PageRequest buildpageRequest(Integer pageNumber, Integer pageSize) {
		int querypageNumber;
		int querypageSize;
		if(pageNumber!=null&&pageNumber>0) {
			querypageNumber = pageNumber-1;
		}
		else {
			querypageNumber = DEFAULT_PAGE_NUMBER;
		}
		if(pageSize!=null&&pageSize<=1000) {
			querypageSize = pageSize;
		}
		else if(pageSize!=null&&pageSize>1000) {
			querypageSize = 1000;
		}
		else {
			querypageSize = DEFAULT_PAGE_SIZE;
		}
		return PageRequest.of(querypageNumber, querypageSize);
	}
	
	@Override
	public Optional<BeerModel> getBeerbyId(int id) {
		return Optional.ofNullable(beerMapper.beertoBeerModel(beerRepository.findById(id).orElse(null)));
	}

	@Override
	public BeerModel addBeer(BeerModel beer) {
		return beerMapper.beertoBeerModel(beerRepository.save(beerMapper.beerModeltoBeer(beer)));
	}

	@Override
	public BeerModel updateBeer(BeerModel beer) {
		return beerMapper.beertoBeerModel(beerRepository.save(beerMapper.beerModeltoBeer(beer)));
	}

}
