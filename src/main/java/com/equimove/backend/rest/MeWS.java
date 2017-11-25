package com.equimove.backend.rest;

import com.equimove.backend.component.PrincipalComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Controller
@Path("/")
public class MeWS {

	@Autowired
	private PrincipalComponent principalComponent;

	@GET
	@Path("/me")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get() {
		return Response.status(200).entity(principalComponent.getCurrentUser()).build();
	}
}
