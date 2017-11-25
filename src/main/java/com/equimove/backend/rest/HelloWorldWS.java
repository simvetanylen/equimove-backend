package com.equimove.backend.rest;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Controller
@Path("/")
public class HelloWorldWS {

	@GET
	@Path("/hello")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get() {
		return Response.status(200).entity("hello").build();
	}
}
