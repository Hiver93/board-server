package com.example.demo.error;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

	POST_NOT_FOUND(HttpStatus.NOT_FOUND, "post not found"),
	NOT_POST_OWNER(HttpStatus.FORBIDDEN, "user is not the owner of this post"),
	
	INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "check username or password"),
	AUTHENTICATION_REQUIRED(HttpStatus.UNAUTHORIZED, "please login"),
	INVALID_JWT(HttpStatus.UNAUTHORIZED, "invalid jwt"),
	EXPIRED_JWT(HttpStatus.UNAUTHORIZED, "expired jwt"),
	
	USERNAME_CONFLICT(HttpStatus.CONFLICT, "username conflict"),
	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "user not found");
	
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
