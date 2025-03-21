package com.example.demo.util;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.example.demo.domain.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private final String SECRET_STR = "abdfjwaeabdfjwaeabdfjwaeabdfjwaeabdfjwaeabdfjwaeabdfjwaeabdfjwaeabdfjwaeabdfjwaeabdfjwaeabdfjwaeabdfjwaeabdfjwaeabdfjwaeabdfjwaeabdfjwaeabdfjwaeabdfjwaeabdfjwaeabdfjwaeabdfjwaeabdfjwaeabdfjwaeabdfjwaeabdfjwaeabdfjwaeabdfjwaeabdfjwaeabdfjwae";
	private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET_STR.getBytes());
	
	public String generateToken(User user, long exp) {
		Date issued = new Date();
		Date expired = new Date(issued.getTime() + exp);
		return Jwts.builder()
				.signWith(SECRET_KEY)
				.claim("id", user.getId())
				.issuedAt(issued)
				.expiration(expired)
				.compact();
	}
	
	public User getUser(String token) {
		return User.builder()
				.id((Integer)getClaims(token).get("id"))
				.build();
	}
	
	private Claims getClaims(String token) {
		return (Claims)Jwts.parser().verifyWith(SECRET_KEY).build().parse(token).getPayload();
	}
}
