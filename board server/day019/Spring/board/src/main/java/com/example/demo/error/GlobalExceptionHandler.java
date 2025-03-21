package com.example.demo.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(PostException.class)
	public ResponseEntity<ErrorResponseBody> postException(PostException postException){
		return ResponseEntity.status(postException.getStatus())
				.body(new ErrorResponseBody(postException.getMessage()));
	}
}
