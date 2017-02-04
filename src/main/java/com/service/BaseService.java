package com.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseService<T> {

	T find(Long id);

	List<T> findAll();

	Page<T> findAll(Pageable pageable);

	List<T> save(Collection<T> entityList);

	T save(T entity);

	void remove(T entity);

	void remove(Collection<T> entityList);

	Page<T> findAll(Pageable pageable, Map<String, Object> stringObjectMap);

	List<T> findAll(Map<String, Object> stringObjectMap);
}