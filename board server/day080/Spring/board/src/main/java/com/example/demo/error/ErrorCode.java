package com.example.demo.error;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

	CONTENT_NOT_FOUND(HttpStatus.NOT_FOUND, "content not found"),
	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "user not found"),
	LIKE_NOT_FOUND(HttpStatus.NOT_FOUND, "not like yet"),
	
	USERNAME_CONFLICT(HttpStatus.CONFLICT, "username conflict"),
	LIKE_CONFLICT(HttpStatus.CONFLICT, "aleady like"),
	
	AUTHENTICATION_REQUIRED(HttpStatus.UNAUTHORIZED, "please login"),
	INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "check username or password"),
	
	NOT_CONTENT_OWNER(HttpStatus.FORBIDDEN, "not content owner"),
	INCORRECT_PASSWORD(HttpStatus.FORBIDDEN, "incorrect password"),
	NOT_SAME_USER(HttpStatus.FORBIDDEN, "not same user"),
	
	INVALID_IMAGE_TYPE(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "invalid image file type"),
	
	PASSWORD_REQUIRED(HttpStatus.BAD_REQUEST, "password is required");

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
