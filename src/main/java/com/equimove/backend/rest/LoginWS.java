package com.equimove.backend.rest;

import com.equimove.backend.dto.LoginDto;
import com.equimove.backend.entity.UserEntity;
import com.equimove.backend.service.LoginService;
import com.equimove.backend.utils.CookiesUtils;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Controller
@Path("/")
public class LoginWS {

	@Autowired
	private LoginService loginService;

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(LoginDto dto) {
		Pair<UserEntity, String> pair = loginService.login(dto);
		return Response.status(200).cookie(CookiesUtils.createTokenCookie(pair.getValue1())).entity(pair.getValue0()).build();
	}

	@POST
	@Path("/logout")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response logout() {
		return Response.status(200).cookie(CookiesUtils.deleteTokenCookie()).build();
	}
}
