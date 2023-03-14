package com.udemy.springframework.controllerss;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.springframework.models.BeerModel;
import com.udemy.springframework.services.BeerService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/beers")
public class BeerController {
	
	@Autowired
	private final BeerService beerService;
	
	
	@GetMapping("/list")
	public List<BeerModel> getBeersList(){
		return beerService.listofBeers();
	}
	
	@GetMapping("/{beerid}")
	public BeerModel getBeerById(@PathVariable("beerid") long id) {
		log.debug("Get beer by id from controller");
		return beerService.getBeerbyId(id).orElseThrow(NotFoundException::new);
	}
	
	@PostMapping("/saveBeer")
	public ResponseEntity saveBeer(@RequestBody BeerModel beer) {
		
		BeerModel savedBeer = beerService.addBeer(beer);
		
		HttpHeaders headers = new HttpHeaders(); // Headers can be added to request or to response to know details about the type
												 // of requested to responded value.
		headers.add("Location", "/beers/saveBeer "+savedBeer.getId().toString());
		
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/update/{beerid}")
	// Here in this update method, We are saving different beer for the same ID. I can replace less than all attributes according
	// to my choice from Postman
	public ResponseEntity updateBeer(@PathVariable("beerid") long id, @RequestBody BeerModel beer) {
		
		BeerModel existingbeer = beerService.getBeerbyId(id).orElseThrow(NotFoundException::new);
		beer.setId(existingbeer.getId());
		BeerModel updatedbeer = beerService.updateBeer(beer);
		
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
}
