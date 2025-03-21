package com.example.demo.util;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.demo.domain.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticationUtil {

	private JwtUtil jwtUtil;

	public AuthenticationUtil(JwtUtil jwtUtil) {
		super();
		this.jwtUtil = jwtUtil;
	}
	
	public void setAuthentication(User user) {
		String token = this.jwtUtil.generateToken(user.getId());
		HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
		response.setHeader("Authorization", token);
	}
	
	public Integer authenticate() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String token = request.getHeader("Authorization");
		
		return this.jwtUtil.getId(token);
	}
}
