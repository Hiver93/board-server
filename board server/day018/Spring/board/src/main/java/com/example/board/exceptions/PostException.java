package com.example.board.exceptions;

import org.springframework.http.HttpStatus;

public class PostException extends RuntimeException {
	private HttpStatus status;
	private String message;
	
	public PostException(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}
	public String getMessage() {
		return this.message;
	}
	public HttpStatus getStatus() {
		return this.status;
	}
}
