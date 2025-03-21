package com.example.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.global.BaseResBody;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<BaseResBody> methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
		return BaseResBody.buildResponseEntity(HttpStatus.BAD_REQUEST, methodArgumentNotValidException.getFieldError().getDefaultMessage());
	}
	
	@ExceptionHandler(exception = {PostException.class})
	public ResponseEntity<BaseResBody> postException(PostException postException){
		return BaseResBody.buildResponseEntity(postException.getErrorCode().getStatus(), postException.getErrorCode().getMessage());
	}
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<BaseResBody> userException(UserException userException){
		return BaseResBody.buildResponseEntity(userException.getErrorCode().getStatus(), userException.getErrorCode().getMessage());
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<BaseResBody> unknownException(Exception exception){
		exception.printStackTrace();
		return BaseResBody.buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, "unknown error. contact admin");
	}
}
