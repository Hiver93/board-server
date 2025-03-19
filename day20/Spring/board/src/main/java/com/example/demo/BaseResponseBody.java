package com.example.demo;

public class BaseResponseBody {
	String message;

	public String getMessage() {
		return message;
	}

	protected void setMessage(String message) {
		this.message = message;
	}

	public static BaseResponseBody of(String message) {
		var b = new BaseResponseBody();
		b.setMessage(message);
		return b;
	}
	
	public static BaseResponseBody of(String message, Object data) {
		var b = new BaseResponseBody() {
			private Object data;
			public Object getData() {
				return data;
			}
			private void setData(Object data) {
				this.data = data;
			}
		};
		b.setMessage(message);
		b.setData(data);
		return b;
	}
	
}
