package com.equimove.backend.component;

import com.equimove.backend.constant.UserType;
import com.equimove.backend.entity.UserEntity;
import com.equimove.backend.security.VGAuthentication;
import com.equimove.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class PrincipalComponent {

	@Autowired
	private UserService userService;

	public Long getPrincipal() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal != null && principal instanceof Long) {
			return (Long) principal;
		} else {
			return null;
		}
	}

	private UserEntity unauthenticatedUser() {
		UserEntity model = new UserEntity();
		model.setType(UserType.UNAUTHENTICATED);
		return model;
	}

	public UserEntity getCurrentUser() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth instanceof VGAuthentication) {
			VGAuthentication vgAuth = (VGAuthentication) auth;
			UserEntity user = userService.get(getPrincipal());
			user.setType(UserType.LOGGED);
			return user;

		} else {
			return unauthenticatedUser();
		}
	}
}
