package com.example.demo.error;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

	POST_NOT_FOUND(HttpStatus.NOT_FOUND, "post not found"),
	NOT_POST_OWNER(HttpStatus.FORBIDDEN, "user is not the onwer of this post"),
	
	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "user not found"),
	INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "check username or password"),
	USERNAME_CONFLICT(HttpStatus.CONFLICT, "username conflict"),
	
	AUTHENTICATION_REQUIRED(HttpStatus.UNAUTHORIZED, "please login"),
	EXPIRED_JWT(HttpStatus.UNAUTHORIZED, "expired jwt"),
	INVALID_JWT(HttpStatus.UNAUTHORIZED, "invalid jwt");
	
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
