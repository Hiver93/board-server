package com.example.demo.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BaseResBody<T> {

	private final T data;
	private final String message;
	public ResponseEntity<BaseResBody<T>> toResponse(HttpStatus status){
		return ResponseEntity.status(status)
				.body(this);
	}
}
