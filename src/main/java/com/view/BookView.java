package com.view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.model.Book;
import com.service.BookService;

@ManagedBean
@SessionScoped
public class BookView {

	@ManagedProperty(value = "#{bookService}")
	private BookService bookService;

	public void addBook(Book book) {
		bookService.addBook(book);
	}

	public List<Book> listBooks() {
		return bookService.listBooks();
	}

	public boolean isEmpty() {
		return bookService.isEmpty();
	}

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

}
