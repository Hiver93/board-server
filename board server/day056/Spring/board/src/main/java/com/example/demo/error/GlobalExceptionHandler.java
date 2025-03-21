package com.example.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.example.demo.common.BaseResBody;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BoardException.class)
	public ResponseEntity<BaseResBody<Void>> boardException(BoardException boardException){
		return new BaseResBody<Void>(null, boardException.getErrorCode().getMessage())
				.toResponse(boardException.getErrorCode().getStatus());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<BaseResBody<Void>> methodArgumentNotValidException(MethodArgumentNotValidException ex){
		return new BaseResBody<Void>(null, ex.getFieldError().getDefaultMessage())
				.toResponse(HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<BaseResBody<Void>> noResourceFoundException(NoResourceFoundException ex){
		return new BaseResBody<Void>(null, "check url")
				.toResponse(HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<BaseResBody<Void>> httpMessageNotReadableException(HttpMessageNotReadableException ex){
		return new BaseResBody<Void>(null, "invalid request body. please check the format")
				.toResponse(HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<BaseResBody<Void>> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex){
		return new BaseResBody<Void>(null, ex.getPropertyName() + " : " + ex.getRequiredType().getSimpleName() + ""
				+ "is required")
				.toResponse(HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<BaseResBody<Void>> unknownException(Exception ex){
		return new BaseResBody<Void>(null, "unknown error. contact admin")
				.toResponse(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
