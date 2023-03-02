package com.udemy.springframework.services;

import org.springframework.stereotype.Service;

import com.udemy.springframework.entities.Author;
import com.udemy.springframework.repositories.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService {
	
	private AuthorRepository authorRepository;
	

	public AuthorServiceImpl(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}


	@Override
	public Iterable<Author> findAll() {
		return authorRepository.findAll();
	}

}
