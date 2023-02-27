package com.udemy.springframework.repositories;

import org.springframework.data.repository.CrudRepository;

import com.udemy.springframework.entities.book;

public interface BookRepository extends CrudRepository<book, Long> {

}
