package com.equimove.backend.service;

import com.equimove.backend.dao.HorseDao;
import com.equimove.backend.entity.HorseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HorseService {

	@Autowired
	private HorseDao horseDao;

	@Transactional(readOnly = true)
	public List<HorseEntity> getAll() {
		return horseDao.getAll();
	}

	@Transactional(readOnly = false)
	public HorseEntity create(HorseEntity entity) {
		Long pk = horseDao.save(entity);
		return horseDao.getByPk(pk);
	}
}
