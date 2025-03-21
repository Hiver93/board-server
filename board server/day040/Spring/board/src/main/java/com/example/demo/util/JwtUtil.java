package com.example.demo.util;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.example.demo.error.BoardException;
import com.example.demo.error.ErrorCode;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private final String keyStr = "abcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefg";
	private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(keyStr));
	
	public String generateToken(Integer userId) {
		Date cur = new Date();
		Date exp = new Date(cur.getTime() + 1000 * 10);
		return Jwts.builder()
				.claim("userId", userId)
				.issuedAt(cur)
				.expiration(exp)
				.signWith(SECRET_KEY)
				.compact();
	}
	
	public Integer getId(String token) {
		Claims claims = getClaims(token);
		return (Integer)claims.get("userId");
	}

	private Claims getClaims(String token) {
		try {
			return (Claims)Jwts.parser().verifyWith(SECRET_KEY).build().parse(token).getPayload();
		}catch(ExpiredJwtException expired) {
			throw new BoardException(ErrorCode.EXPIRED_JWT);
		}catch(Exception exception) {
			throw new BoardException(ErrorCode.INVALID_JWT);
		}
	}
}
