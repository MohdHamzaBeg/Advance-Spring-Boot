package com.udemy.springframework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udemy.springframework.entities.Beer;

public interface BeerRepository extends JpaRepository<Beer, Long> {
}
