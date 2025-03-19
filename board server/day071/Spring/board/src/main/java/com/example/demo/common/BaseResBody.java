package com.example.demo.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseResBody<T> {

	private final T data;
	private final String message;
	public BaseResBody(T data, String message) {
		super();
		this.data = data;
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public String getMessage() {
		return message;
	}
	public ResponseEntity<BaseResBody<T>> toResponse(HttpStatus status){
		return ResponseEntity.status(status)
				.body(this);
	}
}
