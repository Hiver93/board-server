package com.example.demo.error;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
	
	POST_NOT_FOUND(HttpStatus.NOT_FOUND, "post not found"),
	
	NOT_CONTENT_OWNER(HttpStatus.FORBIDDEN, "not content owner"),
	
	INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "check username or password"),
	AUTHENTICATION_REQUIRED(HttpStatus.UNAUTHORIZED, "please login"),
	
	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "user not found"),
	USERNAME_CONFLICT(HttpStatus.CONFLICT, "username conflict"),
	NOT_SAME_USER(HttpStatus.FORBIDDEN, "not same user"),
	
	INCORRECT_PASSWORD(HttpStatus.FORBIDDEN, "incorrect password"),
	
	COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "comment not found"),
	
	LIKE_NOT_FOUND(HttpStatus.NOT_FOUND, "not like yet"),
	LIKE_CONFLICT(HttpStatus.CONFLICT, "aleady like");
	private final HttpStatus status;
	private final String messge;
	private ErrorCode(HttpStatus status, String messge) {
		this.status = status;
		this.messge = messge;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public String getMessge() {
		return messge;
	}
	
}
