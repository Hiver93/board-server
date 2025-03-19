package com.example.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus.*;

public enum ErrorCode {
	
	// 401
	INCORRECT_PASSWORD(HttpStatus.UNAUTHORIZED,"incorrect password"),
	// 404
	POST_NOT_FOUND(HttpStatus.NOT_FOUND,"post not found");
	
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
