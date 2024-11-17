package com.udemy.springframework.spring_reactive_mongo.webfn;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.mongodb.internal.connection.Server;
import com.udemy.springframework.spring_reactive_mongo.entity.Beer;
import com.udemy.springframework.spring_reactive_mongo.service.BeerService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class BeerHandler {
	private final BeerService beerService;
	
	public Mono<ServerResponse> listBeers(ServerRequest request){
		Flux<Beer> flux;
		
		if(request.queryParam("beerName").isPresent())
			flux = beerService.findbybeername(request.queryParam("beerName").get());
		else
			flux = beerService.listofbeers();
		return ServerResponse.ok()
				.body(flux, Beer.class);
	}
	
	public Mono<ServerResponse> getBeerbyId(ServerRequest request){
		return ServerResponse.ok()
				.body(beerService.getbyId(request.pathVariable("id"))
			.switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND))), Beer.class)
;
	}
	public Mono<ServerResponse> saveBeer(ServerRequest request){
		// In easy words, whenever there is a new publisher(Database changer) we use flatMap
		return beerService.save(request.bodyToMono(Beer.class))
				.flatMap(savedBeer->ServerResponse
						.created(UriComponentsBuilder.fromPath(BeerRouterConfig.BEER_PATH_ID)
								.build(savedBeer.getId()))
						.build());
	}
	public Mono<ServerResponse> updateBeer(ServerRequest request){
		return request.bodyToMono(Beer.class)
				.flatMap(beerbody-> 
				beerService.update(request.pathVariable("id"), beerbody))
				.switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
				.flatMap(savedBeer -> ServerResponse.noContent().build());
	}
	public Mono<ServerResponse> deleteBeer(ServerRequest request){
		return beerService.getbyId(request.pathVariable("id"))
				.switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
				.flatMap(beerBody-> beerService.delete(beerBody.getId()))
				.then(ServerResponse.noContent().build());
		// this is used so that if there is any exception occuring during delete
		// then the exception will bubble up and the stream will be going on
	}
}
