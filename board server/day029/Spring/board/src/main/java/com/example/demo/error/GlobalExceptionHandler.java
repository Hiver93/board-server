package com.example.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.BaseResBody;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<BaseResBody> customException(CustomException customException){
		return BaseResBody.buildResponseEntity(customException.getErrorCode().getStatus(), customException.getErrorCode().getMessage());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<BaseResBody> methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
		return BaseResBody.buildResponseEntity(HttpStatus.BAD_REQUEST, methodArgumentNotValidException.getFieldError().getDefaultMessage());
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<BaseResBody> unknownException(Exception exception){
		exception.printStackTrace();
		return BaseResBody.buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, "unknown error. contact admin");
	}
}
