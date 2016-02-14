package com.service;

import java.util.List;

import com.model.Book;

public interface BookService {

	public void addBook(Book book);

	public List<Book> listBooks();

	public boolean isEmpty();

}
