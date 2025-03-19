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
		public final String getTitle() {
			return title;
		}
		public final void setTitle(String title) {
			this.title = title;
		}
		public final String getContent() {
			return content;
		}
		public final void setContent(String content) {
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
		public final String getTitle() {
			return title;
		}
		public final void setTitle(String title) {
			this.title = title;
		}
		public final String getContent() {
			return content;
		}
		public final void setContent(String content) {
			this.content = content;
		}
		
	}
}
