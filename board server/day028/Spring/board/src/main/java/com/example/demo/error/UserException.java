package com.example.demo.error;

public class UserException extends RuntimeException {
	private final ErrorCode errorCode;

	public UserException(ErrorCode errorCode) {
		super();
		this.errorCode = errorCode;
	}
	
	public ErrorCode getErrorCode() {
		return errorCode;
	}
}
