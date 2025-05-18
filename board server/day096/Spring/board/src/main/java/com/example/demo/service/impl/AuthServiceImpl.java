package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.demo.domain.User;
import com.example.demo.error.BoardException;
import com.example.demo.error.ErrorCode;
import com.example.demo.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class AuthServiceImpl implements AuthService{

	@Override
	public void setAuthentication(User user) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		
		session.setAttribute("id", user.getId());
		session.setMaxInactiveInterval(60 * 60);
	}

	@Override
	public boolean isAuthenticated() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		
		if(session.getAttribute("id") == null) {
			return false;
		}
		
		return true;
	}

	@Override
	public User getAuthentication() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		
		if(session.getAttribute("id") == null) {
			throw new BoardException(ErrorCode.AUTHENTICATION_REQUIRED);
		}
		
		return User.builder()
				.id((Integer)session.getAttribute("id"))
				.build();
	}

	@Override
	public void remoevAuthentication() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		
		session.invalidate();
	}

}
