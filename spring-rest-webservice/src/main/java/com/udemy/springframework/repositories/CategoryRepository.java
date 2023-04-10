package com.udemy.springframework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udemy.springframework.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {
	
}
