package com.example.demo.dto;

import org.springframework.validation.annotation.Validated;

import com.example.demo.entity.Post;

import jakarta.validation.constraints.NotBlank;

public class PostReqDto {

	@Validated
	public static class Create{
		@NotBlank(message = "title is required")
		String title;
		@NotBlank(message = "content is required")
		String content;
		@NotBlank(message = "password is required")
		String password;
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
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public Create() {
			super();
		}
		public Post toEntity() {
			Post post = new Post();
			post.setTitle(title);
			post.setContent(content);
			post.setPassword(password);
			return post;
		}
	}
	
	@Validated
	public static class Delete{
		@NotBlank(message = "password is required")
		private String password;

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public Delete() {
			super();
		}
		public Post toEntity(long postId) {
			Post post = new Post();
			post.setPassword(password);
			post.setId(postId);
			return post;
		}
	}
	
	@Validated
	public static class Update{
		@NotBlank(message = "title is required")
		private String title;
		@NotBlank(message = "content is required")
		private String content;
		@NotBlank(message = "password is required")
		private String password;
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
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public Update() {
			super();
		}
		public Post toEntity(long postId) {
			Post post = new Post();
			post.setTitle(title);
			post.setContent(content);
			post.setPassword(password);
			post.setId(postId);
			return post;
		}
	}
}
