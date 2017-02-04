package com.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.model.Book;
import com.service.BookService;

@ManagedBean
@RequestScoped
public class BookView {

	@ManagedProperty(value = "#{bookService}")
	private BookService bookService;
	
	private Book newBook;
	
	@PostConstruct
	public void init() {
		this.newBook = new Book();
	}

	public void addBook(Book book) {
		bookService.save(book);
	}

	public List<Book> listBooks() {
		return bookService.findAll();
	}

	public boolean isEmpty() {
		return bookService.findAll().isEmpty();
	}

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}
	
	public Book getNewBook() {
		return newBook;
	}

}
