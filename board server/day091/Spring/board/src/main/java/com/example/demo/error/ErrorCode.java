package com.example.demo.error;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

	CONTENT_NOT_FOUND(HttpStatus.NOT_FOUND, "content not found"),
	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "user not found"),
	LIKE_NOT_FOUND(HttpStatus.NOT_FOUND, "not liked yet"),
	
	USERNAME_CONFLICT(HttpStatus.CONFLICT, "username conflict"),
	LIKE_CONFLICT(HttpStatus.CONFLICT, "aleady liked"),
	
	AUTHENTICATION_REQUIRED(HttpStatus.UNAUTHORIZED, "login please"),
	INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "check username or password"),
	
	NOT_CONTENT_OWNER(HttpStatus.FORBIDDEN, "not content owner"),
	NOT_SAME_USER(HttpStatus.FORBIDDEN, "user not same"),
	INCORRECT_PASSWORD(HttpStatus.FORBIDDEN, "incorrect password"),
	
	PASSWORD_REQUIRED(HttpStatus.BAD_REQUEST, "password is required"),
	
	INVALID_IMAGE_TYPE(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "invalid image file type")
	;
	private final HttpStatus status;
	private final String message;
	
}
