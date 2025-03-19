package com.example.demo.dto;

import org.springframework.validation.annotation.Validated;

import com.example.demo.domain.Comment;
import com.example.demo.domain.Post;
import com.example.demo.domain.User;

import jakarta.validation.constraints.NotBlank;

public class CommentReqDto {

	@Validated
	public static class Create{
		@NotBlank(message = "content is required")
		private String content;

		public Create() {
			super();
		}
		public void setContent(String content) {
			this.content = content;
		}
		public Comment to(User user, Post post) {
			return Comment.builder()
					.user(user)
					.post(post)
					.content(content)
					.build();
		}
	}
}
