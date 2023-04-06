package com.udemy.springframework.bootstrap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.udemy.springframework.entities.Beer;
import com.udemy.springframework.entities.Customer;
import com.udemy.springframework.models.BeerCSV;
import com.udemy.springframework.repositories.BeerRepository;
import com.udemy.springframework.repositories.CustomerRepository;
import com.udemy.springframework.services.BeerCSVservice;

import jakarta.transaction.Transactional;

@Transactional
@Component
public class BootstrapData implements CommandLineRunner{
	
	@Autowired
	BeerRepository beerRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	BeerCSVservice beerCSVservice;
	@Override
	public void run(String... args) throws Exception {
		forBeers();
		forBeerCSV();
		forcustomers();
	}

	private void forBeerCSV() throws FileNotFoundException {
		if(beerRepository.count()<10) {
			File file = ResourceUtils.getFile("classpath:csvdata/beers.csv");
			List<BeerCSV> recs = beerCSVservice.convertCSV(file);
			
			recs.forEach(beerCSV->{
				beerRepository.save(Beer.builder()
						.beerName(StringUtils.abbreviate(beerCSV.getBeer(), 50))
						.price(BigDecimal.TEN)// Not using a beerCSV because there is not such field with the data type as same as price
						.mfg(LocalDateTime.now())// Same reason for not using beercsv
						.build());
			});
		}
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
