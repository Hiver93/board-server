package com.example.demo.dto;

import com.example.demo.domain.Comment;
import com.example.demo.domain.User;

public class CommentReqDto {

	public static class Create{
		private String content;

		public Create() {
			super();
		}
		public void setContent(String content) {
			this.content = content;
		}
		public Comment to(User user) {
			return Comment.builder()
					.user(user)
					.content(content)
					.build();
		}
	}
}
