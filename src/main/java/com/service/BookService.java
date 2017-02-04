package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.model.Book;
import com.repository.BookRepository;

@Service
public class BookService extends AbstractService<Book> {

	@Autowired
	private BookRepository repository;

	@Override
	protected JpaRepository<Book, Long> getRepository() {
		return repository;
	}

}
