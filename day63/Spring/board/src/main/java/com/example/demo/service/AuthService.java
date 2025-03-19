package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.demo.domain.User;
import com.example.demo.error.BoardException;
import com.example.demo.error.ErrorCode;

import jakarta.servlet.http.HttpSession;

@Service
public class AuthService {

	public void setAuthentication(User user) {
		HttpSession session = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
		
		session.setAttribute("id", user.getId());
	}
	
	public User authenticate() {
		HttpSession session = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
		
		Integer userId = (Integer)session.getAttribute("id");
		if(userId == null) {
			throw new BoardException(ErrorCode.AUTHENTICATION_REQUIRED);
		}
		return User.builder()
				.id(userId)
				.build();
	}
}
