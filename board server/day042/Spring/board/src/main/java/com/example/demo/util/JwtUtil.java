package com.example.demo.util;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.example.demo.domain.User;
import com.example.demo.error.BoardException;
import com.example.demo.error.ErrorCode;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private final String SECRET_STR = "abcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefgh";
	private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET_STR.getBytes());
	
	public String genereteToken(User user, long exp) {
		Date issued = new Date();
		Date expired = new Date(issued.getTime() + exp);
		
		return Jwts.builder()
				.claim("userId", user.getId())
				.signWith(SECRET_KEY)
				.issuedAt(issued)
				.expiration(expired)
				.compact();
	}
	
	public User getUser(String token) {
		Claims claims = getClaims(token);
		return User.builder()
				.id((Integer)claims.get("userId"))
				.build();
	}
	
	private Claims getClaims(String token) {
		try {
			return (Claims)Jwts.parser().verifyWith(SECRET_KEY).build().parse(token).getPayload();
		}catch(ExpiredJwtException e) {
			throw new BoardException(ErrorCode.EXPIRED_JWT);
		}catch(Exception e) {
			throw new BoardException(ErrorCode.INVALID_JWT);
		}
	}
}
