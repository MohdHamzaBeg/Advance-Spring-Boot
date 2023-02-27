package com.udemy.springframework.repositories;

import org.springframework.data.repository.CrudRepository;

import com.udemy.springframework.entities.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
