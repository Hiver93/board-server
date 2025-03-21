package com.example.demo.util;

import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {

	private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(
			Decoders.BASE64.decode("abcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdeabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdeabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdeabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdef"));
	
	public static String buildToken(Integer userId) {
		Date cur = new Date();
		Date exp = new Date(cur.getTime() + 1000 * 60 * 30);
		String token = Jwts.builder()
				.claim("userId", userId)
				.issuedAt(cur)
				.expiration(exp)
				.signWith(SECRET_KEY)
				.compact();
		return token;
	}
	
	public static Integer getUserId(String token) {
		Claims claims = (Claims)Jwts.parser().verifyWith(SECRET_KEY).build().parse(token).getPayload();
		return (Integer)claims.get("userId");
	}
}
