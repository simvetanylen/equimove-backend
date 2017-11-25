package com.equimove.backend.service;

import com.equimove.backend.dao.ClaimDao;
import com.equimove.backend.dao.HorseDao;
import com.equimove.backend.entity.ClaimEntity;
import com.equimove.backend.entity.HorseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClaimService {

	@Autowired
	private ClaimDao claimDao;

	@Autowired
	private HorseDao horseDao;

	@Transactional(readOnly = false)
	public ClaimEntity create(ClaimEntity entity) {
		Long pk = claimDao.save(entity);
		return claimDao.getByPk(pk);
	}

	@Transactional(readOnly = true)
	public List<ClaimEntity> getClaimsFortUserPk(Long userPk) {
		List<HorseEntity> horses = horseDao.getHorseForUserPk(userPk);

		List<Long> horsesPk = new ArrayList<>();
		for (HorseEntity horse : horses) {
			horsesPk.add(horse.getPk());
		}
		return claimDao.getForHorses(horsesPk);
	}
}
