package com.udemy.springframework.spring_reactive_mongo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.awaitility.Awaitility.await;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.UncategorizedMongoDbException;

import com.udemy.springframework.spring_reactive_mongo.entity.Beer;
import com.udemy.springframework.spring_reactive_mongo.service.BeerService;

import reactor.core.publisher.Mono;

@SpringBootTest
public class BeerServiceImplTest {
	
	@Autowired
	BeerService beerService;
	
	/*@Test
	void saveBeer() throws InterruptedException, UncategorizedMongoDbException {
		AtomicBoolean atomicBoolean = new AtomicBoolean(false);
		
		Mono<Beer> savedBeer = beerService.save(getTestBeer());
		
		savedBeer.subscribe(sB->{
			System.out.println(sB.getId());
			atomicBoolean.set(true);
		});
		await().untilTrue(atomicBoolean);
	}*/
	
	public static Beer getTestBeer() {
		return Beer.builder()
				.beerName("Tuborg")
				.price(new BigDecimal(69))
				.createdDate(LocalDateTime.now())
				.modifiedDate(LocalDateTime.now())
				.build();
	}
}
