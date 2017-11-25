package com.equimove.backend.service;

import com.equimove.backend.dao.AccessTokenDao;
import com.equimove.backend.entity.AccessTokenEntity;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Service
public class AccessTokenService {

	@Autowired
	private AccessTokenDao accessTokenDao;

	@Autowired
	private UserService userService;

	@Transactional
	public Long getUserPkWithToken(String token) {
		AccessTokenEntity entity = accessTokenDao.getByPk(token);
		return entity.getUser().getPk();
	}

	@Transactional
	public String create(Long userPk) {

		String token = UUID.randomUUID().toString();

		AccessTokenEntity entity = new AccessTokenEntity();
		entity.setPk(token);
		entity.setUser(userService.get(userPk));
		accessTokenDao.save(entity);
		return token;
	}

	@Transactional
	public void delete(String token) {

		AccessTokenEntity entity = accessTokenDao.getByPk(token);
		accessTokenDao.delete(entity);
	}
}
