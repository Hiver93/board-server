package com.example.board.controller.dto;

public class PostControllerReqDto {

	public static class Post{
		private String title;
		private String content;
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
		public Post() {}
	}
}
