package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.demo.domain.User;
import com.example.demo.error.BoardException;
import com.example.demo.error.ErrorCode;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class AuthService {

	public void setAuthentication(User user) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		
		session.setAttribute("id", user.getId());
		session.setMaxInactiveInterval(60 * 60);
	}
	
	public void authenticate() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		
		if(session.getAttribute("id") == null) {
			throw new BoardException(ErrorCode.AUTHENTICATION_REQUIRED);
		}
	}
	
	public User getAuthentiction() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		
		if(session.getAttribute("id") == null) {
			return null;
		}
		return User.builder()
				.id((Integer)session.getAttribute("id"))
				.build();
	}
}
