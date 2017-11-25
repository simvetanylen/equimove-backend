package com.equimove.backend.dao;

import com.equimove.backend.entity.ClaimEntity;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class ClaimDao extends GenericDao<ClaimEntity, Long> {

	public List<ClaimEntity> getForHorses(List<Long> horsesPk) {
		if (horsesPk.size() > 0) {
			return entityManager.createQuery("SELECT claim FROM Claim claim " +
					"WHERE claim.horse.pk IN :horsesPk ")
					.setParameter("horsesPk", horsesPk)
					.getResultList();
		} else {
			return Collections.emptyList();
		}
	}
}
