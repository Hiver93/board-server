package com.example.demo;

public class BaseResBody {
	private Object data;
	private String message;
	public BaseResBody(Object data, String message) {
		super();
		this.data = data;
		this.message = message;
	}
	public BaseResBody(String message) {
		super();
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public String getMessage() {
		return message;
	}
	
}
