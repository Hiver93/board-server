package com.example.demo.global;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseResBody {

	private Object data;
	private String message;
	public BaseResBody(Object data, String message) {
		super();
		this.data = data;
		this.message = message;
	}
	public BaseResBody(String message) {
		super();
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public static ResponseEntity<BaseResBody> buildResponseEntity(HttpStatus status, Object data, String message){
		return ResponseEntity.status(status)
				.body(new BaseResBody(data,message));
	}
	public static ResponseEntity<BaseResBody> buildResponseEntity(HttpStatus status, String message){
		return ResponseEntity.status(status)
				.body(new BaseResBody(message));
	}
}
