package com.example.demo.error;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

	NOT_CONTENT_OWNER(HttpStatus.FORBIDDEN, "not content owner"),
	INCORRECT_PASSWORD(HttpStatus.FORBIDDEN, "incorrect password"),
	NOT_SAME_USER(HttpStatus.FORBIDDEN, "not same user"),
	
	POST_NOT_FOUND(HttpStatus.NOT_FOUND, "post not found"),
	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "user not found"),
	COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "comment not found"),
	LIKE_NOT_FOUND(HttpStatus.NOT_FOUND, "not like yet"),
	
	USERNAME_CONFLICT(HttpStatus.CONFLICT, "username conflict"),
	LIKE_CONFLICT(HttpStatus.CONFLICT, "aleady like"),
	
	INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "check username or password"),	
	AUTHENTICATION_REQUIRED(HttpStatus.UNAUTHORIZED, "login please");
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
