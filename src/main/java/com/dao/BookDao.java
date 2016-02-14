package com.dao;

import java.util.List;

import com.model.Book;

public interface BookDao {

	public void addBook(Book book);

	public List<Book> listBooks();

	public boolean isEmpty();

}
