package com.example.demo.error;

public class PostException extends RuntimeException {
	private final ErrorCode errorCode;

	public PostException(ErrorCode errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}
	
}
