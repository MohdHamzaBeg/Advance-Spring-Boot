package com.udemy.springframework.bootstrap;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.udemy.springframework.entities.Beer;
import com.udemy.springframework.entities.Customer;
import com.udemy.springframework.repositories.BeerRepository;
import com.udemy.springframework.repositories.CustomerRepository;

@Component
public class BootstrapData implements CommandLineRunner{
	
	@Autowired
	BeerRepository beerRepository;
	
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public void run(String... args) throws Exception {
		forBeers();
		forcustomers();
	}

	private void forcustomers() {
		if(customerRepository.count()==0) {
			Customer customer1 = Customer.builder()
					.customerName("Vishal")
					.version(76.4f)
					.createdDate(LocalDateTime.now())
					.lastModifiedDate(LocalDateTime.now())
					.build();
			Customer customer2 = Customer.builder()
					.customerName("Aman")
					.version(21.4f)
					.createdDate(LocalDateTime.now())
					.lastModifiedDate(LocalDateTime.now())
					.build();
			
			Customer customer3 = Customer.builder()
					.customerName("Abhishek")
					.version(45.4f)
					.createdDate(LocalDateTime.now())
					.lastModifiedDate(LocalDateTime.now())
					.build();
			
			customerRepository.save(customer1);
			customerRepository.save(customer2);
			customerRepository.save(customer3);
		}
	}

	private void forBeers() {
		if(beerRepository.count()==0) {
		Beer beer1 = Beer.builder()
				.beerName("Kingfisher")
				.price(new BigDecimal(45.6))
				.mfg(LocalDateTime.now())
				.build();
		
		Beer beer2 = Beer.builder()
				.beerName("Tuborg")
				.price(new BigDecimal(23.67))
				.mfg(LocalDateTime.now())
				.build();
		
		Beer beer3 = Beer.builder()
				.beerName("Mahathandi")
				.price(new BigDecimal(5656.6))
				.mfg(LocalDateTime.now())
				.build();
		beerRepository.save(beer1);
		beerRepository.save(beer2);
		beerRepository.save(beer3);
		}
	}

}
