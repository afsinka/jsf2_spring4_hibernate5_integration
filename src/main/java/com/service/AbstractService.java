package com.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.IteratorUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.history.Revision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.model.AbstractEntity;

public abstract class AbstractService<T extends AbstractEntity> implements BaseService<T> {

	protected abstract JpaRepository<T, Long> getRepository();

	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<Revision<Integer, T>> findRevisions(final Long id, final int start, final int count) {
		return ((RevisionRepository<T, Long, Integer>) getRepository()).findRevisions(id,
				new PageRequest(start, count));
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public Page<T> findAll(final Pageable pageable, final Map<String, Object> stringObjectMap) {
		throw new RuntimeException("You should implement this!");
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public List<T> findAll(final Map<String, Object> stringObjectMap) {
		throw new RuntimeException("You should implement this!");
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public T find(final Long id) {
		return getRepository().findOne(id);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public List<T> findAll() {
		return IteratorUtils.toList(getRepository().findAll().iterator());
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public Page<T> findAll(Pageable pageable) {
		return getRepository().findAll(pageable);
	}

	@Transactional
	@Override
	public T save(final T entity) {
		return getRepository().save(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public List<T> save(final Collection<T> entityList) {
		return getRepository().save(entityList);
	}

	@Transactional
	@Override
	public void remove(final T entity) {
		save(entity);
	}

	@Transactional
	@Override
	public void remove(final Collection<T> entityList) {
		entityList.forEach(this::remove);
	}

	@Transactional
	public void persistIfNotExist(final T entity) {

		final T found = getRepository().findOne(entity.getId());

		if (found == null) {
			getRepository().save(entity);
		}

	}

	@Transactional
	public void persistIfNotExist(final List<T> entityList) {
		entityList.forEach(this::persistIfNotExist);
	}

//	protected <S extends AbstractSpecification<T>> Specification<T> createSpecifications(
//			final Map<String, Object> objectMap, final Class<S> kClass) {
//
//		if (MapUtils.isNotEmpty(objectMap)) {
//			Specifications<T> specifications = null;
//
//			boolean firstLoop = true;
//			for (final Map.Entry<String, Object> stringObjectEntry : objectMap.entrySet()) {
//				final Specification specification = createSpec(stringObjectEntry, kClass);
//				if (firstLoop) {
//					specifications = Specifications.where(specification);
//					firstLoop = false;
//				} else {
//					specifications = specifications.and(specification);
//				}
//			}
//
//			return specifications;
//		} else {
//			return null;
//		}
//	}

//	private <S extends AbstractSpecification<T>> S createSpec(final Map.Entry<String, Object> stringObjectEntry,
//			final Class<S> specificationClass) {
//		try {
//			final S specification = specificationClass.newInstance();
//			specification
//					.setSearchCriteria(new SearchCriteria(stringObjectEntry.getKey(), stringObjectEntry.getValue()));
//			return specification;
//		} catch (Exception ex) {
//			return null;
//		}
//	}
}