package com.example.demo.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class BaseBody<T> {

	@JsonInclude(value = Include.NON_NULL)
	private final T data;
	private final String message;
	public BaseBody(T data, String message) {
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
	public ResponseEntity<BaseBody<T>> toResponse(HttpStatus status){
		return ResponseEntity.status(status)
				.body(this);
	}
	
}
