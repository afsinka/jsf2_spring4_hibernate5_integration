package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.model.Book;
import com.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	@Transactional
	public void addBook(Book book) {
		bookRepository.save(book);
	}

	@Transactional
	public List<Book> listBooks() {
		return bookRepository.findAll();
	}

	@Transactional
	public boolean isEmpty() {
		return bookRepository.findAll().isEmpty();
	}

}
