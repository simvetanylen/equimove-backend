package com.equimove.backend.filter;

import com.equimove.backend.security.VGAuthentication;
import com.equimove.backend.service.AccessTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Provider
@Component
public class AuthenticationProcessingFilter extends AbstractPreAuthenticatedProcessingFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationProcessingFilter.class);

	private static final String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";
	private static final String ROLE_USER = "ROLE_USER";

	@Autowired
	private AccessTokenService accessTokenService;

	@Override
	@Autowired
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		super.setAuthenticationManager(authenticationManager);
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

		try {
			String accessToken = getAccessToken(httpRequest);
			if (accessToken != null) {
				List<GrantedAuthority> authorities = new ArrayList<>();
				authorities.add(new SimpleGrantedAuthority(ROLE_USER));
				try {
					Long userPk = accessTokenService.getUserPkWithToken(accessToken);
					Authentication authentication = new VGAuthentication(userPk, accessToken, authorities);
					SecurityContextHolder.getContext().setAuthentication(authentication);
				} catch (Exception e) {
					LOGGER.info("Wrong access token {}", accessToken);
				}
			} else {
				String login = httpRequest.getHeader("X-Access-Login");
				String password = httpRequest.getHeader("X-Access-Password");
				if (login != null && password != null) {
					// TODO
				}
			}
		} catch (Exception e) {
			// TODO : voir si il y a moyen de faire ça de manière plus propre.
			LOGGER.warn("{}", e);
		}

		if (SecurityContextHolder.getContext().getAuthentication() == null) {
			List<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(ROLE_ANONYMOUS));
			AnonymousAuthenticationToken authentication = new AnonymousAuthenticationToken("unauthenticated",
					"unauthenticated", authorities);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest arg0) {
		return null;
	}

	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest arg0) {
		return null;
	}

	private String getAccessToken(HttpServletRequest httpRequest) {
		Cookie[] cookies = httpRequest.getCookies();

		if (cookies == null) {
			return null;
		}

		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("accessToken")) {
				return cookie.getValue();
			}
		}
		return null;
	}
}
