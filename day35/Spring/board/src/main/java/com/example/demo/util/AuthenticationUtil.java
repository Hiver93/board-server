package com.example.demo.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.demo.error.BoardException;
import com.example.demo.error.ErrorCode;

import jakarta.servlet.http.HttpServletRequest;

public class AuthenticationUtil {

	public static Integer getUserId() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		Integer userId = (Integer)request.getSession().getAttribute("userId");
		if(userId == null) {
			throw new BoardException(ErrorCode.AUTHENTICATION_REQUIRED);
		}
		return userId;
	}
	
	public static void setUserId(Integer userId) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		request.getSession().setAttribute("userId", userId);
	}
}
