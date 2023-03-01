package com.udemy.springframework.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.udemy.springframework.entities.Author;
import com.udemy.springframework.entities.book;
import com.udemy.springframework.repositories.AuthorRepository;
import com.udemy.springframework.repositories.BookRepository;

@Component
public class BootstrapData implements CommandLineRunner {
	
	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;
	
	
	public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
	}


	@Override
	public void run(String... args) throws Exception {
		Author shake = new Author();
		shake.setFirstName("Shakespear");
		shake.setLastName("Pathan");
		
		book boo = new book();
		boo.setTitle("Tempest");
		boo.setIsbn("999999");
		
		Author shakesave = authorRepository.save(shake);
		book boosave = bookRepository.save(boo);
		
		Author ruskin = new Author();
		ruskin.setFirstName("Ruskin");
		ruskin.setLastName("Bond");
		
		book loo = new book();
		loo.setTitle("Moustrap");
		loo.setIsbn("888888");
		
		Author ruskinsave = authorRepository.save(ruskin);
		book loosave = bookRepository.save(loo);
		
		shakesave.getBooks().add(boosave);
		ruskinsave.getBooks().add(loosave);
		
		authorRepository.save(shakesave);
		authorRepository.save(ruskinsave);
		
		System.out.println("print ho ja madarchod");
		System.out.println("Author count = "+authorRepository.count());
		System.out.println("Book count = "+bookRepository.count());
	}
	
	

}
