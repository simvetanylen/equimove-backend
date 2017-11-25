package com.equimove.backend.service;

import com.equimove.backend.dao.AccessTokenDao;
import com.equimove.backend.dao.UserDao;
import com.equimove.backend.dto.LoginDto;
import com.equimove.backend.entity.AccessTokenEntity;
import com.equimove.backend.entity.UserEntity;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class LoginService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private AccessTokenDao accessTokenDao;

	@Transactional(readOnly = false)
	public Pair<UserEntity, String> login(LoginDto dto) {

		UserEntity userEntity = userDao.getByEmail(dto.getEmail()).get(0);
		String token = UUID.randomUUID().toString();

		AccessTokenEntity accessTokenEntity = new AccessTokenEntity();
		accessTokenEntity.setPk(token);
		accessTokenEntity.setUser(userEntity);

		accessTokenDao.save(accessTokenEntity);

		return new Pair<>(userEntity, token);
	}
}
