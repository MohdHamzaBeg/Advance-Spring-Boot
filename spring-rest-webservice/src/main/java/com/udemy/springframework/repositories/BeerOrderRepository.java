package com.udemy.springframework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udemy.springframework.entities.BeerOrder;

public interface BeerOrderRepository extends JpaRepository<BeerOrder, String> {

}
