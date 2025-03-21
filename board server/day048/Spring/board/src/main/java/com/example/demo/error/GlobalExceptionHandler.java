package com.example.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.common.BaseResBody;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BoardException.class)
	public ResponseEntity<BaseResBody> boardException(BoardException boardException){
		return BaseResBody.of(boardException.getErrorCode().getStatus(), boardException.getErrorCode().getMessage());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<BaseResBody> methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
		return BaseResBody.of(HttpStatus.BAD_REQUEST, methodArgumentNotValidException.getFieldError().getDefaultMessage()); 
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<BaseResBody> unknownException(Exception exception){
		exception.printStackTrace();
		return BaseResBody.of(HttpStatus.INTERNAL_SERVER_ERROR, "unknown error. contact admin");
	}
}
