package com.example.demo.dto;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class PostReqDto {

	@Validated
	@Setter
	@Getter
	@NoArgsConstructor
	public static class Create{
		@NotBlank(message = "title is required")
		private String title;
		@NotBlank(message = "content is required")
		private String content;
		private String password;
	}

	@Validated
	@Setter
	@Getter
	@NoArgsConstructor
	public static class Put{
		@NotBlank(message = "title is required")
		private String title;
		@NotBlank(message = "content is required")
		private String content;
	}

	@Validated
	@Setter
	@Getter
	@NoArgsConstructor
	public static class Delete{
		private String password;
	}
}
