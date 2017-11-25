package com.equimove.backend.service;

import com.equimove.backend.dao.UserDao;
import com.equimove.backend.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	@Transactional(readOnly = true)
	public List<UserEntity> getAll() {
		return userDao.getAll();
	}

	@Transactional(readOnly = true)
	public UserEntity get(Long pk) {
		return userDao.getByPk(pk);
	}

	@Transactional(readOnly = false)
	public UserEntity create(UserEntity entity) {
		Long pk = userDao.save(entity);
		return userDao.getByPk(pk);
	}
}
