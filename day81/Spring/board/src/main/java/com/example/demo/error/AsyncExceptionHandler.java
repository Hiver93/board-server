package com.example.demo.error;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler{
	
	Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public void handleUncaughtException(Throwable ex, Method method, Object... params) {
		log.error(ex.getMessage(), ex);
	}

}
