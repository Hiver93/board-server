package com.example.demo.error;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class BoardException extends RuntimeException{

	private final ErrorCode errorCode;

	public BoardException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}
}
