package com.example.demo.error;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

	POST_NOT_FOUND(HttpStatus.NOT_FOUND, "post not found"),
	NOT_POST_OWNER(HttpStatus.FORBIDDEN, "user is not the owner of this post"),
	
	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "user not found. check userId"),
	USERNAME_CONFLICT(HttpStatus.CONFLICT, "username conflict"),
	INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "username or password is wrong"),
	AUTHENTICATION_REQUIRED(HttpStatus.UNAUTHORIZED, "login required");
	
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
