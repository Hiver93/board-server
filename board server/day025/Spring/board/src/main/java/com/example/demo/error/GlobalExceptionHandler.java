package com.example.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.BaseResBody;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(PostException.class)
	public ResponseEntity<BaseResBody> postException(PostException postException){
		return ResponseEntity.status(postException.getErrorCode().getStatus())
				.body(new BaseResBody(postException.getErrorCode().getMessage()));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<BaseResBody> methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new BaseResBody(methodArgumentNotValidException.getFieldError().getDefaultMessage()));
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<BaseResBody> unknownException(Exception exception){
		exception.printStackTrace();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new BaseResBody("unknown error. contact admin"));
	}
}
