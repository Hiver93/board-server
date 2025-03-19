package com.example.demo.dto;

import org.springframework.validation.annotation.Validated;

import com.example.demo.domain.Post;
import com.example.demo.domain.User;

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
		public Post to(Integer userId) {
			Post post = new Post();
			User user = new User();
			user.setId(userId);
			post.setUser(user);
			post.setTitle(title);
			post.setContent(content);
			return post;
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
		public Post to(Integer userId, Integer postId) {
			Post post = new Post();
			User user = new User();
			user.setId(userId);
			post.setId(postId);
			post.setUser(user);
			post.setTitle(title);
			post.setContent(content);
			return post;
		}
	}
}
