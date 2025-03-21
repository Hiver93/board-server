package com.example.demo.utill;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.demo.error.BoardException;
import com.example.demo.error.ErrorCode;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class AuthenticationUtil {

	public Integer getUserId(HttpServletRequest request) {
		Integer userId = (Integer)request.getSession().getAttribute("userId");
		if(userId == null) {
			throw new BoardException(ErrorCode.AUTHENTICATION_REQUIRED);
		}
		return userId;
	}
	
	public void setUserId(HttpServletRequest request, Integer userId) {
		request.getSession().setAttribute("userId", userId);
	}
}
