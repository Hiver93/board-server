package com.example.demo.error;

public class BoardException extends RuntimeException{

	private final ErrorCode errorCode;

	public BoardException(ErrorCode errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}
}
