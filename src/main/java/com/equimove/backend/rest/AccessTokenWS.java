package com.equimove.backend.rest;

import com.equimove.backend.service.AccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Controller
@Path("/")
public class AccessTokenWS {

	@Autowired
	private AccessTokenService accessTokenService;

	@GET
	@Path("/accessTokens")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		return Response.status(200).entity(accessTokenService.getAll()).build();
	}
}
