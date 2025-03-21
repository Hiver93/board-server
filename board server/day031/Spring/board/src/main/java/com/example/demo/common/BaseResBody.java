package com.example.demo.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseResBody {

	private Object data;
	
	private String message;

	private BaseResBody(Object data, String message) {
		super();
		this.data = data;
		this.message = message;
	}

	private BaseResBody(String message) {
		super();
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public String getMessage() {
		return message;
	}
	
	public static ResponseEntity<BaseResBody> buildResponseEntity(HttpStatus status, Object data, String messgae){
		return ResponseEntity.status(status)
				.body(new BaseResBody(data, messgae));
	}
	
	public static ResponseEntity<BaseResBody> buildResponseEntity(HttpStatus status, String messgae){
		return ResponseEntity.status(status)
				.body(new BaseResBody(messgae));
	}
}
