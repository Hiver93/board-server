package com.example.demo.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthenticationUtil {

	public static Integer getUserId() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String token = request.getHeader("Authorization");
		return JwtUtil.getUserId(token);
	}
	
	public static void setUserId(Integer userId) {
		HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
		response.setHeader("Authorization", JwtUtil.buildToken(userId));
	}
}
