package com.example.board.dto;

import com.example.board.entity.Post;

public class PostRequestDto {
	
	public static class Create{
		
		private String title;
		
		private String content;
		
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
		
		public Create() {}
		
		public Post toEntity() {
			Post post = new Post();
			post.setTitle(title);
			post.setContent(content);
			post.setPassword(password);
			return post;
		}
	}
	
	public static class Delete{
		private String password;

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
		public Delete() {}
	}
	
	public static class Update{
		private String title;
		private String content;
		private String password;
		protected String getTitle() {
			return title;
		}
		protected void setTitle(String title) {
			this.title = title;
		}
		protected String getContent() {
			return content;
		}
		protected void setContent(String content) {
			this.content = content;
		}
		protected String getPassword() {
			return password;
		}
		protected void setPassword(String password) {
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
