package com.udemy.springframework.springrestwebservice.repositoriestest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.udemy.springframework.entities.Beer;
import com.udemy.springframework.entities.Category;
import com.udemy.springframework.repositories.BeerRepository;
import com.udemy.springframework.repositories.CategoryRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class CategoryRepositoryTest {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	BeerRepository beerRepository;
	Beer testbeer;
	
	@BeforeEach
	void setUp() {
		testbeer = beerRepository.findAll().get(0);
	}
	
	@Transactional
	@Test
	void testAddCategory() {
		Category savedCategory = categoryRepository.save(Category.builder().id("1")
				.description("Ales")
				.build());
		
		testbeer.addCategory(savedCategory);
		Beer saveBeer = beerRepository.save(testbeer);
		
		System.out.println(saveBeer.getBeerName());
	}
}
