package com.example.board.exceptions;

public class ErrorResponseBody {
	private String message;

	public ErrorResponseBody(String message) {
		super();
		this.message = message;
	}

	public final String getMessage() {
		return message;
	}

	public final void setMessage(String message) {
		this.message = message;
	}
	
	
}
