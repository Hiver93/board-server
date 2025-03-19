package com.example.demo.error;

public class UsersException extends RuntimeException {
	private ErrorCode errorCode;

	public UsersException(ErrorCode errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
	
}
