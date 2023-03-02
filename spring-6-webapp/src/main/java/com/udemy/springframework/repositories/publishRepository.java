package com.udemy.springframework.repositories;

import org.springframework.data.repository.CrudRepository;

import com.udemy.springframework.entities.Publisher;

public interface publishRepository extends CrudRepository<Publisher, Long> {

}
