package com.example.demo.error;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

	POST_NOT_FOUND(HttpStatus.NOT_FOUND, "post not found"),
	NOT_POST_OWNER(HttpStatus.FORBIDDEN, "not owner"),
	
	INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "check username or password"),
	AUTHENTICATION_REQUIRED(HttpStatus.UNAUTHORIZED, "please login"),
	INCORRECT_PASSWORD(HttpStatus.UNAUTHORIZED, "check password"),
	
	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "user not found"),
	USERNAME_CONFLICT(HttpStatus.CONFLICT, "username conflict"),
	NOT_SAME_USER(HttpStatus.FORBIDDEN, "not same user"),
	
	COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "comment not found"),
	NOT_COMMENT_OWNER(HttpStatus.FORBIDDEN, "not owner"),
	
	LIKE_CONFLICT(HttpStatus.CONFLICT, "aleady like"),
	LIKE_NOT_FOUND(HttpStatus.NOT_FOUND, "not like yet")
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
