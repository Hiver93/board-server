package com.example.demo.dto;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class PostReqDto {

	@NoArgsConstructor
	@Setter
	@Getter
	@Validated
	public static class Create{
		@NotBlank(message = "title is required")
		private String title;
		@NotBlank(message = "content is required")
		private String content;
		private String password;
	}
	
	@NoArgsConstructor
	@Setter
	@Getter
	@Validated
	public static class Put{
		@NotBlank(message = "title is required")
		private String title;
		@NotBlank(message = "content is required")
		private String content;
		private String password;
	}
	
	@NoArgsConstructor
	@Setter
	@Getter
	@Validated
	public static class Delete{
		private String password;
	}
}
