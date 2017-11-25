package com.equimove.backend.utils;

import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.NewCookie;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class CookiesUtils {

	/**
	 * Deux mois.
	 */
	private static int MAX_AGE = 60*60*24*30*2;

	public static NewCookie createTokenCookie(String token) {
		Date expiry = Date.from(LocalDateTime.now().plusMonths(2L).atZone(ZoneId.systemDefault()).toInstant()) ;
		return new NewCookie(new Cookie("accessToken", token, "/", null), "", MAX_AGE, expiry, false, true);
	}

	public static NewCookie deleteTokenCookie() {
		return new NewCookie(new Cookie("accessToken", "null", "/", null), "", 0, new Date(), false, true);
	}
}
