package com.example.demo.dto;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CommentReqDto {

	@Getter
	@Setter
	@Validated
	@NoArgsConstructor
	public static class Create{
		@NotBlank(message = "content is required")
		private String content;
	}
	
	@Getter
	@Setter
	@Validated
	@NoArgsConstructor
	public static class Put{
		@NotBlank(message = "content is required")
		private String content;
	}
}
