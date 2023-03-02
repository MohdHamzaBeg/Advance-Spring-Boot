package com.udemy.springframework.services;

import org.springframework.stereotype.Service;

import com.udemy.springframework.entities.book;
import com.udemy.springframework.repositories.BookRepository;

@Service
public class BookServiceImpl implements BookService {
	
	private final BookRepository bookRepository;
	
	
	public BookServiceImpl(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}


	@Override
	public Iterable<book> findAll() {
		
		return bookRepository.findAll();
	}

}
