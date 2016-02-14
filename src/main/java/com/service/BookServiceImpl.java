package com.service;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.BookDao;
import com.model.Book;

@Service
@ManagedBean(name = "bookService")
@SessionScoped
public class BookServiceImpl implements BookService {

	private BookDao bookDao;

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	@Override
	@Transactional
	public void addBook(Book book) {
		bookDao.addBook(book);

	}

	@Override
	@Transactional
	public List<Book> listBooks() {
		return bookDao.listBooks();
	}

	@Override
	@Transactional
	public boolean isEmpty() {
		return bookDao.isEmpty();
	}

}
