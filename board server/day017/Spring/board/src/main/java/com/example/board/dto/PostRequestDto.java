package com.example.board.dto;

import org.springframework.validation.annotation.Validated;

import com.example.board.entity.Post;

import jakarta.validation.constraints.NotBlank;

public class PostRequestDto {
	
	@Validated
	public static class Create{
		@NotBlank		
		private String title;
		
		@NotBlank
		private String content;
		
		@NotBlank
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
	
	public static class Delete{
		
		@NotBlank
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
		
		
	}
	
	public static class Update{
		
		@NotBlank
		private String title;
		
		@NotBlank
		private String content;
		
		@NotBlank
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
