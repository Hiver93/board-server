package com.example.demo.dto;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;

public class PostReqDto {

	@Validated
	public static class Create{
		@NotBlank(message = "title is required")
		private String title;
		@NotBlank(message = "content is required")
		private String content;
		public Create() {
			super();
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		
	}
	
	@Validated
	public static class Update{
		@NotBlank(message = "title is required")
		private String title;
		@NotBlank(message = "content is required")
		private String content;
		public Update() {
			super();
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		
	}
}
