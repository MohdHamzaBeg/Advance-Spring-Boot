package com.udemy.springframework.controllerss;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.springframework.services.BookService;

@Controller
public class BookController {
	
	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	@RequestMapping("/books")
	public String getBooks(Model model) {
		
		model.addAttribute("books",bookService.findAll());
		return "books";
	}
}
