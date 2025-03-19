package com.example.demo.error;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

	POST_NOT_FOUND(HttpStatus.NOT_FOUND, "post not found"),
	NOT_POST_OWNER(HttpStatus.FORBIDDEN, "user is not the owner of this post"),
	
	INVALID_CREDENTAILS(HttpStatus.UNAUTHORIZED, "please check username or password"),
	AUTHENTICATION_REQUIRED(HttpStatus.UNAUTHORIZED, "login please"),
	
	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "user not found"),
	USERNAME_CONFLICT(HttpStatus.CONFLICT, "username conflict"),
	
	COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "comment not found"),
	NOT_COMMENT_OWNER(HttpStatus.FORBIDDEN, "user is not the owner of this comment"),
	
	POST_LIKE_CONFLICT(HttpStatus.CONFLICT, "aleady like");
	
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
