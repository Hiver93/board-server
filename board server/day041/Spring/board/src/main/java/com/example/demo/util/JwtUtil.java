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
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private final String KEY_STR = "abcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefgh";
	private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(KEY_STR));
	
	public String generateToken(User user, long expMs) {
		Date issued = new Date();
		Date expired = new Date(issued.getTime() + expMs);
		
		return Jwts.builder()
				.claim("userId", user.getId())
				.signWith(SECRET_KEY)
				.issuedAt(issued)
				.expiration(expired)
				.compact();
	}
	
	public User getUser(String token) {
		try {
			Claims claims = getClaims(token);
			return User.builder().id((Integer)claims.get("userId")).build();
		}catch(ExpiredJwtException e) {
			throw new BoardException(ErrorCode.EXPIRED_JWT);
		}catch(Exception e) {
			throw new BoardException(ErrorCode.INVALID_JWT);
		}
	}
	
	private Claims getClaims(String token) {
		return (Claims)Jwts.parser().verifyWith(SECRET_KEY).build().parse(token).getPayload();
	}
	
}
