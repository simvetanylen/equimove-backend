package com.equimove.backend.security;

import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
public class VGAuthentication extends UsernamePasswordAuthenticationToken {

	public VGAuthentication(Long principal, String credentials, Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
	}
}
