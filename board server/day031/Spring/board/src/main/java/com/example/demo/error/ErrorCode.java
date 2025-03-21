package com.example.demo.error;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

	POST_NOT_FOUND(HttpStatus.NOT_FOUND, "POST_NOT_FOUND"),
	INCORRECT_PASSWORD(HttpStatus.UNAUTHORIZED, "incorrect password"),
	
	USERNAME_CONFLICT(HttpStatus.CONFLICT, "username conflict"),
	AUTHENTICATION_REQUIRED(HttpStatus.UNAUTHORIZED, "login required"),
	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "user not found"),
	INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "username or password is wrong");
	
	private final HttpStatus status;
	private final String message;
	private ErrorCode(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public HttpStatus getStatus() {
		return status;
	}
	
	
}
