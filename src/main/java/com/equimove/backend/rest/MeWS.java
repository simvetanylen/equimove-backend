package com.equimove.backend.rest;

import com.equimove.backend.component.PrincipalComponent;
import com.equimove.backend.entity.UserEntity;
import com.equimove.backend.service.ClaimService;
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

	@Autowired
	private ClaimService claimService;

	@GET
	@Path("/me")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get() {
		return Response.status(200).entity(principalComponent.getCurrentUser()).build();
	}

	@GET
	@Path("/me/claims")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getClaims() {
		return Response.status(200).entity(claimService.getClaimsFortUserPk(principalComponent.getPrincipal())).build();
	}
}
