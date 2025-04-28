package com.example.demo.error;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {

	CONTENT_NOT_FOUND(HttpStatus.NOT_FOUND, "content not found"),
	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "user not found"),
	LIKE_NOT_FOUND(HttpStatus.NOT_FOUND, "not liked yet"),
	
	USERNAME_CONFLICT(HttpStatus.CONFLICT, "username conflict"),
	LIKE_CONFLICT(HttpStatus.CONFLICT, "aleady liked"),
	
	AUTHENTICATION_REQUIRED(HttpStatus.UNAUTHORIZED, "login please"),
	INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "check username or password"),
	
	USER_NOT_SAME(HttpStatus.FORBIDDEN, "not same user"),
	INCORRECT_PASSWORD(HttpStatus.FORBIDDEN, "incorrect password"),
	NOT_CONTENT_OWNER(HttpStatus.FORBIDDEN, "not content owner"),
	
	INVALID_IMAGE_TYPE(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "invalid image file type"),
	
	PASSWORD_REQUIRED(HttpStatus.BAD_REQUEST, "password is required");
	
	private final HttpStatus status;
	private final String message;
}
