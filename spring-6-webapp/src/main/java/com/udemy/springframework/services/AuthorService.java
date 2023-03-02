package com.udemy.springframework.services;

import com.udemy.springframework.entities.Author;

public interface AuthorService {
	Iterable<Author> findAll();
}
