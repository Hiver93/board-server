package com.example.demo.error;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

	POST_NOT_FOUND(HttpStatus.NOT_FOUND,"post not found"),
	INCORRECT_PASSWORD(HttpStatus.UNAUTHORIZED,"incorrect password"),
	
	USERNAME_CONFLICT(HttpStatus.CONFLICT,"username conflict"),
	USER_NOT_FOUND(HttpStatus.NOT_FOUND,"user not found"),
	AUTHENTICATION_REQUIRED(HttpStatus.UNAUTHORIZED,"please login"),
	INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED,"username or password is wrong"),
	
	NOT_POST_OWNER(HttpStatus.FORBIDDEN, "user is not the owner of this post");
	
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
