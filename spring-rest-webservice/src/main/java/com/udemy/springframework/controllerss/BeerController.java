package com.udemy.springframework.controllerss;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.springframework.entities.Beer;
import com.udemy.springframework.services.BeerService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/beers")
public class BeerController {
	
	@Autowired
	private final BeerService beerService;
	
	@GetMapping("/list")
	public List<Beer> getBeersList(){
		return beerService.listofBeers();
	}
	
	@GetMapping("/{beerid}")
	public Beer getBeerById(@PathVariable("beerid") UUID id) {
		log.debug("Get beer by id from controller");
		return beerService.getBeerbyId(id);
	}
}
