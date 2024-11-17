package com.udemy.springframework.spring_reactive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.udemy.springframework.spring_reactive.entity.Beer;
import com.udemy.springframework.spring_reactive.service.BeerService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class BeerController {
	
	public static final String BEER_PATH = "/beer";
	public static final String BEER_PATH_ID = BEER_PATH+"/{beerid}";
	public static final String BEER_PATH_SAVE = BEER_PATH+"/save";
	public static final String BEER_PATH_UPDATE = BEER_PATH_ID+"/update";
	public static final String BEER_PATH_DELETE = BEER_PATH_ID+"/delete";
	
	
	private final BeerService beerService;
	
	@GetMapping(BEER_PATH)
	Flux<Beer> listofBeers(){
		return beerService.listBeers();
	}
	
	@GetMapping(BEER_PATH_ID)
	Mono<Beer> getbeerbyId(@PathVariable("beerid") Integer id){
		return beerService.getbyId(id);
	}
	
	@PostMapping(BEER_PATH_SAVE)
	Mono<ResponseEntity<Void>> saveBeer(@RequestBody @Validated Beer beer){
		return beerService.save(beer)
	               .map(savedBeer -> ResponseEntity.created(UriComponentsBuilder
	                       .fromHttpUrl("http://localhost:8080/" + BEER_PATH
	                               + "/" + savedBeer.getId())
	                       .build().toUri())
	                       .build());
	}
	@PutMapping(BEER_PATH_UPDATE)
	Mono<Beer> updateBeer(@PathVariable("beerid") Integer id, @RequestBody @Validated Beer beer){
		beerService.update(id, beer).subscribe();
		return beerService.getbyId(id)
				.switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
		// Correct way to handle exceptions in spring reactive programming
	}
	@DeleteMapping(BEER_PATH_DELETE)
	ResponseEntity<Void> deleteBeer(@PathVariable("beerid") Integer id){
		beerService.delete(id) .subscribe();
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
