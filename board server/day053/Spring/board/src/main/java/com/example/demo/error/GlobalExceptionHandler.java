package com.example.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.common.BaseBody;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BoardException.class)
	public ResponseEntity<BaseBody<Void>> boardException(BoardException boardException){
		return new BaseBody<Void>(null, boardException.getErrorCode().getMessage())
				.toResponse(boardException.getErrorCode().getStatus());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<BaseBody<Void>> methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
		return new BaseBody<Void>(null, methodArgumentNotValidException.getFieldError().getDefaultMessage())
				.toResponse(HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<BaseBody<Void>> unknownError(Exception exception){
		return new BaseBody<Void>(null, "unknown error. contact admin")
				.toResponse(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
