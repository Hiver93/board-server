package com.example.demo.dto;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;

public class CommentReqDto {
	@Validated
	public static class Create{
		@NotBlank(message = "content is required")
		private String content;

		public Create() {
			super();
		}

		public final String getContent() {
			return content;
		}

		public final void setContent(String content) {
			this.content = content;
		}
		
	}
}
