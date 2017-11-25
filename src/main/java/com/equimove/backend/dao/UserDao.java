package com.equimove.backend.dao;

import com.equimove.backend.entity.UserEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserDao extends GenericDao<UserEntity, Long> {

	public List<UserEntity> getByEmail(String email) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<UserEntity> criteriaQuery = criteriaBuilder.createQuery(type);
		Root<UserEntity> queryRoot = criteriaQuery.from(type);
		criteriaQuery.select(queryRoot);
		criteriaQuery.where(criteriaBuilder.equal(queryRoot.get("email"), email));

		return entityManager.createQuery(criteriaQuery).getResultList();
	}
}
