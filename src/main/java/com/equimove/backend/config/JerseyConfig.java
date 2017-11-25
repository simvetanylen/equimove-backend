package com.equimove.backend.config;

import com.equimove.backend.rest.HelloWorldWS;
import com.equimove.backend.rest.HorseWS;
import com.equimove.backend.rest.MeWS;
import com.equimove.backend.rest.UserWS;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath("/equimove/api")
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {

		register(HelloWorldWS.class);
		register(HorseWS.class);
		register(UserWS.class);
		register(MeWS.class);
	}
}
