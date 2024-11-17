package com.udemy.springframework.spring_reactive.bootstrap;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.udemy.springframework.spring_reactive.entity.Beer;
import com.udemy.springframework.spring_reactive.repository.BeerRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Bootstrap implements CommandLineRunner {
	
	 private final BeerRepository beerRepository;
	
	@Override
	public void run(String... args) throws Exception {
		forBeers();
		
		beerRepository.count().subscribe(count->{
			System.out.println(count);
		});
	}
	private void forBeers() {
		beerRepository.count().subscribe(count->{
			if(count==0) {
				Beer beer1 = Beer.builder()
						.beerName("Kingfisher")
						.price(new BigDecimal(45.6))
						.createdDate(LocalDateTime.now())
						.modifiedDate(LocalDateTime.now())
						.build();
				
				Beer beer2 = Beer.builder()
						.beerName("Tuborg")
						.price(new BigDecimal(23.67))
						.createdDate(LocalDateTime.now())
						.modifiedDate(LocalDateTime.now())
						.build();
				
				Beer beer3 = Beer.builder()
						.beerName("Mahathandi")
						.price(new BigDecimal(5656.6))
						.createdDate(LocalDateTime.now())
						.modifiedDate(LocalDateTime.now())
						.build();
				
				beerRepository.save(beer1).subscribe();
				beerRepository.save(beer2).subscribe();
				beerRepository.save(beer3).subscribe();
				}
		});
		
	}

}
