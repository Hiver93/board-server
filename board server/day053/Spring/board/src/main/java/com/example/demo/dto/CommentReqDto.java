package com.example.demo.dto;

import org.springframework.validation.annotation.Validated;

import com.example.demo.domain.Comment;
import com.example.demo.domain.Post;
import com.example.demo.domain.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CommentReqDto {

	@Validated
	public static class Create{
		@NotBlank(message = "content is required")
		private String content;
		@NotNull(message = "postId is required")
		private Integer postId;
		public Create() {
			super();
		}
		
		public void setPostId(Integer postId) {
			this.postId = postId;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public Comment to(User user) {
			return Comment.builder()
					.user(user)
					.post(Post.builder().id(postId).build())
					.content(content)
					.build();
		}
	}
}
