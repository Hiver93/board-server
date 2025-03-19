package com.example.board.dto;

import com.example.board.entity.Post;

public class PostRequestDto {
	
	public static class PostDto{
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
		public PostDto() {}
		public Post to(){
			Post post = new Post();
			post.setTitle(title);
			post.setContent(content);
			post.setPassword(password);
			return post;
		}
		@Override
		public String toString() {
			return "PostDto [title=" + title + ", content=" + content + ", password=" + password + "]";
		}
		
	}
	
	public static class DeleteDto{
		private String password;
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public DeleteDto() {}
	}
	
	public static class PutDto{
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
		public PutDto() {}
		public Post to(long postId){
			Post post = new Post();
			post.setId(postId);
			post.setTitle(title);
			post.setContent(content);
			post.setPassword(password);
			return post;
		}
	}
}
