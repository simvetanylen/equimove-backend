package com.equimove.backend.rest;

import com.equimove.backend.component.PrincipalComponent;
import com.equimove.backend.constant.UserType;
import com.equimove.backend.entity.HorseEntity;
import com.equimove.backend.entity.UserEntity;
import com.equimove.backend.service.HorseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Controller
@Path("/")
public class HorseWS {

	@Autowired
	private HorseService horseService;

	@Autowired
	private PrincipalComponent principalComponent;

	@GET
	@Path("/horses")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		return Response.status(200).entity(horseService.getAll()).build();
	}

	@GET
	@Path("/horses/{pk}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("pk") Long pk) {
		return Response.status(200).entity(horseService.get(pk)).build();
	}

	@POST
	@Path("/horses")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response post(HorseEntity horse) {
		UserEntity currentUser = principalComponent.getCurrentUser();
		if (currentUser.getType().equals(UserType.LOGGED)) {
			horse.setOwner(principalComponent.getCurrentUser());
			return Response.status(201).entity(horseService.create(horse)).build();
		} else {
			throw new NotAuthorizedException("Not authorized");
		}
	}
}
