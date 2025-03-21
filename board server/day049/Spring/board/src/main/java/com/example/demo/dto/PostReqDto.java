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
		public void setTitle(String title) {
			this.title = title;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public Post to(User user) {
			return Post.builder()
					.title(title)
					.content(content)
					.user(user)
					.build();
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
		public void setTitle(String title) {
			this.title = title;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public Post to(User user, Integer postId) {
			return Post.builder()
					.user(user)
					.id(postId)
					.title(title)
					.content(content)
					.build();
		}
	}
}
