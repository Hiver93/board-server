package com.example.demo.dto;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;

public class CommentReqDto {

	@Validated
	public static class Create{
		@NotBlank(message = "content is required")
		private String content;

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public Create() {
			super();
		}
		
	}
	
	@Validated
	public static class Put{
		@NotBlank(message = "content is required")
		private String content;

		public Put() {
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
