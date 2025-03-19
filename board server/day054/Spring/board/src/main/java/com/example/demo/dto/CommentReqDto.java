package com.example.demo.dto;

import com.example.demo.domain.Comment;
import com.example.demo.domain.Post;
import com.example.demo.domain.User;

public class CommentReqDto {

	public static class Create{
		private String content;

		public void setContent(String content) {
			this.content = content;
		}

		public Create() {
			super();
		}
		public Comment to(Integer postId, User user) {
			return Comment.builder()
					.post(Post.builder().id(postId).build())
					.user(user)
					.content(content)
					.build();
		}
	}
}
