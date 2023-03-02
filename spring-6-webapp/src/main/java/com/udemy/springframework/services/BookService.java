package com.udemy.springframework.services;

import com.udemy.springframework.entities.book;

public interface BookService {
	Iterable<book> findAll();
}
