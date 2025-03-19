package com.example.demo.error;

public class ErrorResponseBody {
	private String message;

	public ErrorResponseBody() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ErrorResponseBody(String message) {
		super();
		this.message = message;
	}
	
}
