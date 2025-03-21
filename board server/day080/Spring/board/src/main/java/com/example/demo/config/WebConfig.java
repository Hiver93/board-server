package com.example.demo.config;

import java.time.Duration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowCredentials(true)
		.allowedMethods("*")
		.allowedOrigins("http://localhost:5500");
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		CacheControl cacheControl = CacheControl.maxAge(Duration.ofHours(1));
		
		registry.addResourceHandler("/images/**")
		.addResourceLocations("file:src/main/resources/static/images")
		.setCacheControl(cacheControl);
	}
}
