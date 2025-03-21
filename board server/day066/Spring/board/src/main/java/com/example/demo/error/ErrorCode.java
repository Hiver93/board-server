package com.example.demo.error;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

	NOT_COTENT_OWNER(HttpStatus.FORBIDDEN, "user is not the owner"),
	
	POST_NOT_FOUND(HttpStatus.NOT_FOUND, "post not found"),
	
	INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "check username or password"),
	PASSWORD_REQUIRED(HttpStatus.UNAUTHORIZED, "password is required"),
	INCORRECT_PASSWORD(HttpStatus.UNAUTHORIZED, "incorrect password"),
	AUTHENTICATION_REQUIRED(HttpStatus.UNAUTHORIZED, "login please"),
	
	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "user not found"),
	USERNAME_CONFLICT(HttpStatus.CONFLICT, "username conflict"),
	
	COMMENT_NOT_FOUNDT(HttpStatus.NOT_FOUND, "comment not found"),
	
	LIKE_NOT_FOUND(HttpStatus.NOT_FOUND, "not like yet"),
	LIKE_CONFLICT(HttpStatus.CONFLICT, "aleady like");
	
	private final HttpStatus status;
	private final String message;
	private ErrorCode(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public String getMessage() {
		return message;
	}
	
}
