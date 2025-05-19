package com.example.demo.error;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {

	CONTENT_NOT_FOUND(HttpStatus.NOT_FOUND, "content not found"),
	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "user not found"),
	LIKE_NOT_FOUND(HttpStatus.NOT_FOUND, "not like yet"),
	
	USERNAME_CONFLICT(HttpStatus.CONFLICT, "username conflict"),
	LIKE_CONFLICT(HttpStatus.CONFLICT, "aleady liked"),
	
	AUTHENTICATION_REQUIRED(HttpStatus.UNAUTHORIZED, "login please"),
	INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "check username or password"),
	
	INCORRECT_PASSWORD(HttpStatus.FORBIDDEN, "incorrect password"),
	NOT_CONTENT_OWNER(HttpStatus.FORBIDDEN, "not content owner"),
	NOT_SAME_USER(HttpStatus.FORBIDDEN, "not same user"),
	
	INVALID_CONTENT_TYPE(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "invlaid content type"),
	
	PASSWORD_REQUIRED(HttpStatus.BAD_REQUEST, "password is required");
	
	private final HttpStatus status;
	private final String message;
}
