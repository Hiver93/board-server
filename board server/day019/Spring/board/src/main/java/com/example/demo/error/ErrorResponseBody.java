package com.example.demo.error;

public class ErrorResponseBody {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ErrorResponseBody() {
		super();
	}

	public ErrorResponseBody(String message) {
		super();
		this.message = message;
	}
	
}
