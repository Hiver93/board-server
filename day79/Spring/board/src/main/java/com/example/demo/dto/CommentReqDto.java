package com.example.demo.dto;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;

public class CommentReqDto {

	@Validated
	public static class Creaate{
		@NotBlank(message = "content is required")
		private String content;

		public Creaate() {
			super();
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}
		
	}
}
