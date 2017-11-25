package com.equimove.backend.dao;

import com.equimove.backend.entity.HorseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HorseDao extends GenericDao<HorseEntity, Long> {

	public List<HorseEntity> getHorseForUserPk(Long userPk) {
		return entityManager.createQuery("SELECT horse FROM Horse horse " +
				"WHERE horse.owner.pk = :userPk ")
				.setParameter("userPk", userPk)
				.getResultList();
	}
}
