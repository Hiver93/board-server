package com.example.demo.dto;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CommentReqDto {

	@NoArgsConstructor
	@Setter
	@Getter
	@Validated
	public static class Create{
		@NotBlank(message = "content is required")
		private String content;
	}
	
	@NoArgsConstructor
	@Setter
	@Getter
	@Validated
	public static class Put{
		@NotBlank(message = "content is required")
		private String content;
	}
}
