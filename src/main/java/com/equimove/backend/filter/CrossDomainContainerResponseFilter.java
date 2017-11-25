package com.equimove.backend.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;

@Provider
@Component
public class CrossDomainContainerResponseFilter implements ContainerResponseFilter {

	@Value("#{'${access.control.allow.origin}'.split(';')}")
	private List<String> accessControlAllowOriginList;

	public void filter(ContainerRequestContext containerRequestContext,
					   ContainerResponseContext containerResponseContext) throws IOException {

		String requestOrigin;
		if (containerRequestContext.getHeaders().get("origin") != null
				&& containerRequestContext.getHeaders().get("origin").size() > 0) {
			requestOrigin = containerRequestContext.getHeaders().get("origin").get(0);
		} else {
			requestOrigin = "*";
		}

		String accessControlAllowOrigin;
		if (accessControlAllowOriginList.contains(requestOrigin)) {
			accessControlAllowOrigin = requestOrigin;
		} else {
			accessControlAllowOrigin = "*";
		}

		containerResponseContext.getHeaders().add("Access-Control-Allow-Origin", accessControlAllowOrigin);
		containerResponseContext.getHeaders().add("Access-Control-Allow-Headers",
				"origin, content-type, accept, authorization, Access-Control-Expose-Headers, Access-Control-Allow-Headers, X-CSRF-HEADER, X-CSRF-PARAM, X-CSRF-TOKEN, X-Access-Token");
		containerResponseContext.getHeaders().add("Access-Control-Expose-Headers",
				"X-CSRF-HEADER, X-CSRF-PARAM, X-CSRF-TOKEN, X-Access-Token");
		containerResponseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
		containerResponseContext.getHeaders().add("Access-Control-Allow-Methods",
				"GET, POST, PUT, PATCH, DELETE, OPTIONS, HEAD");

	}

}
