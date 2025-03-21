package com.example.demo.error;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

	POST_NOT_FOUND(HttpStatus.NOT_FOUND, "post not found"),
	INCORRECT_PASSWORD(HttpStatus.UNAUTHORIZED, "incorrect password"),
	
	INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "username or password is wrong"),
	USERNAME_CONFLICT(HttpStatus.CONFLICT, "username confilct")
	;
	
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
