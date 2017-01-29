package com.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.model.Book;

@Transactional
@Repository("bookDaoRepo")
public class BookDaoImpl implements BookDao {

	@Autowired
	private SessionFactory sessionFactory;

	private static final Logger logger = LogManager.getLogger(BookDaoImpl.class);

	@Override
	public void addBook(Book book) {
		Session session = sessionFactory.getCurrentSession();
		logger.debug("New Book = " + book);
		session.persist(book);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> listBooks() {
		Session session = sessionFactory.getCurrentSession();
		List<Book> BooksList = session.createQuery("from Book").list();
		for (Book p : BooksList) {
			logger.debug("Books : " + p);
		}
		return BooksList;
	}

	@Override
	public boolean isEmpty() {
		Session session = sessionFactory.getCurrentSession();
		// Criteria criteria = session.createCriteria(Book.class);
		// int size = criteria.list().size();

		Long size = (Long) session.createCriteria(Book.class).setProjection(Projections.rowCount()).uniqueResult();

		logger.debug("Book list size: " + size);

		if (size != null && size > 0) {
			return false;
		} else {
			return true;
		}
	}

}
