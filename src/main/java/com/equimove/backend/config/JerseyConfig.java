package com.equimove.backend.config;

import com.equimove.backend.rest.*;
import com.equimove.backend.service.AccessTokenService;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath("/equimove/api")
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {

		register(HorseWS.class);
		register(UserWS.class);
		register(MeWS.class);
		register(LoginWS.class);
		register(AccessTokenWS.class);
	}
}
