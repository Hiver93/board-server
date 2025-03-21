package com.example.demo.util;

import com.example.demo.error.BoardException;
import com.example.demo.error.ErrorCode;

import jakarta.servlet.http.HttpServletRequest;

public class AuthenticationUtil {

	public static Integer authenticate(HttpServletRequest request) {
		Integer userId = (Integer)request.getSession().getAttribute("userId");
		if(userId == null) {
			throw new BoardException(ErrorCode.AUTHENTICATION_REQUIRED);
		}
		return userId;
	}
}
