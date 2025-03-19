package com.example.board.dto;

public class PostRequestDto {

	public static class Post{
		private String title;
		private String content;
		private String password;
		public Post() {}
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
	}
	
	public static class Delete{
		private String password;
		public Delete() {}
		public final String getPassword() {
			return password;
		}
		public final void setPassword(String password) {
			this.password = password;
		}
		
	}
}
