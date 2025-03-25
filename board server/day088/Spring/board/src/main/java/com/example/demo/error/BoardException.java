package com.example.demo.error;

import org.springframework.http.HttpStatus;

public class BoardException extends RuntimeException{

	private final ErrorCode errorCode;

	public BoardException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}
	public HttpStatus getStatus() {
		return this.errorCode.getStatus();
	}
}
