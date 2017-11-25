package com.equimove.backend.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CustomAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomAuthenticationEntryPoint.class);

	@Value("#{'${access.control.allow.origin}'.split(';')}")
	private List<String> accessControlAllowOriginList;

	@Override
	public void commence(final HttpServletRequest request, final HttpServletResponse response,
						 final AuthenticationException authException) throws IOException, ServletException {

		// TODO : virer sysout
		LOGGER.info("======== CustomAuthenticationEntryPoint ========");

		String requestOrigin = request.getHeader("origin");

		String accessControlAllowOrigin;
		if (requestOrigin != null && accessControlAllowOriginList.contains(requestOrigin)) {
			accessControlAllowOrigin = requestOrigin;
		} else {
			accessControlAllowOrigin = "*";
		}

		response.addHeader("Access-Control-Allow-Origin", accessControlAllowOrigin);

		response.addHeader("Access-Control-Allow-Headers",
				"origin, content-type, accept, authorization, Access-Control-Expose-Headers, "
						+ "Access-Control-Allow-Headers, X-CSRF-HEADER, X-CSRF-PARAM, X-CSRF-TOKEN, Access-Control-Allow-Origin, Set-Cookie, X-Access-Token");
		response.addHeader("Access-Control-Expose-Headers",
				"X-CSRF-HEADER, X-CSRF-PARAM, X-CSRF-TOKEN, X-Access-Token");
		response.addHeader("Access-Control-Allow-Credentials", "true");
		response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, PATCH, DELETE, OPTIONS, HEAD");
	}
}
