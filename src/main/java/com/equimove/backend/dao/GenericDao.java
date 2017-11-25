package com.equimove.backend.dao;

import com.equimove.backend.entity.IGenericEntity;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GenericDao<E extends IGenericEntity<PK>, PK extends Serializable> {

	@PersistenceContext
	protected EntityManager entityManager;

	protected final Class<E> type;

	@SuppressWarnings({"unchecked", "rawtypes"})
	public GenericDao() {
		super();
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class) pt.getActualTypeArguments()[0];
	}

	public List<E> getAll() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(type);
		Root<E> queryRoot = criteriaQuery.from(type);
		criteriaQuery.select(queryRoot);

		List<E> results = entityManager.createQuery(criteriaQuery).getResultList();
		if (results.isEmpty()) {return Collections.EMPTY_LIST;}
		else {
			return results;
		}
	}

	public List<PK> getAllPk() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(type);
		Root<E> queryRoot = criteriaQuery.from(type);
		criteriaQuery.select(queryRoot);

		List<E> results = entityManager.createQuery(criteriaQuery).getResultList();
		if (results.isEmpty()) {return Collections.EMPTY_LIST;}
		else {
			List<PK> pkList = new ArrayList<PK>();
			for (E result : results) {
				pkList.add(result.getPk());
			}
			return pkList;
		}
	}

	public E getByPk(PK pk) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(type);
		Root<E> queryRoot = criteriaQuery.from(type);
		criteriaQuery.select(queryRoot);
		criteriaQuery.where(criteriaBuilder.equal(queryRoot.get("pk"), pk));

		List<E> results = entityManager.createQuery(criteriaQuery).getResultList();
		if (results.isEmpty()) {return null;}
		else if (results.size() == 1) return results.get(0);
		throw new NonUniqueResultException();
	}

	@SuppressWarnings("unchecked")
	public PK save(E entity) {
		entityManager.persist(entity);
		entityManager.flush();
		return entity.getPk();
	}

	public void saveOrUpdate(E entity) {
		E oldEntity = getByPk(entity.getPk());
		if (oldEntity == null) {
			save(entity);
		} else {
			update(entity);
		}
		entityManager.merge(entity);
	}

	public void update(E entity) {
		entityManager.merge(entity);
	}

	public void delete(E entity) {
		entityManager.remove(entity);
	}

	protected CriteriaQuery<E> createCriteriaQuery() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		return criteriaBuilder.createQuery(type);
	}

}
