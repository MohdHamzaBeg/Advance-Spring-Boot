package com.udemy.springframework.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.udemy.springframework.entities.Author;
import com.udemy.springframework.entities.Publisher;
import com.udemy.springframework.entities.book;
import com.udemy.springframework.repositories.AuthorRepository;
import com.udemy.springframework.repositories.BookRepository;
import com.udemy.springframework.repositories.publishRepository;

@Component
public class BootstrapData implements CommandLineRunner {
	
	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;
	private final publishRepository pRepository;
	
	
	public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, publishRepository pRepository ){
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.pRepository = pRepository;
	}


	@Override
	public void run(String... args) throws Exception {
		System.out.println("print ho ja madarchod");
		Author shake = new Author();
		shake.setFirstName("Shakespear");
		shake.setLastName("Pathan");
		
		book boo = new book();
		boo.setTitle("Tempest");
		boo.setIsbn("999999");
		
		Publisher pub = new Publisher();
		pub.setPublisherName("John Thompson");
		pub.setAddress("Bleecker street");
		
		
		Author shakesave = authorRepository.save(shake);
		book boosave = bookRepository.save(boo);
		Publisher pubsave = pRepository.save(pub);
		
		boosave.setPublisher(pubsave);
		
		
		Author ruskin = new Author();
		ruskin.setFirstName("Ruskin");
		ruskin.setLastName("Bond");
		
		book loo = new book();
		loo.setTitle("Moustrap");
		loo.setIsbn("888888");
		
		Author ruskinsave = authorRepository.save(ruskin);
		book loosave = bookRepository.save(loo);
		
		loosave.setPublisher(pubsave);
		
		shakesave.getBooks().add(boosave);
		ruskinsave.getBooks().add(loosave);
		
		authorRepository.save(shakesave);
		authorRepository.save(ruskinsave);
		bookRepository.save(boosave);
		bookRepository.save(loosave);
		
		
		System.out.println("Author count = "+authorRepository.count());
		System.out.println("Book count = "+bookRepository.count());
	}
	
	

}
