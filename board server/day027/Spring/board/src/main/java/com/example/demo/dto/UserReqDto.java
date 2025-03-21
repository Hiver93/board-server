package com.example.demo.dto;

import com.example.demo.entity.Users;

public class UserReqDto {
	public static class Signup{
		private String username;
		private String password;
		private String nickname;
		public Signup() {
			super();
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getNickname() {
			return nickname;
		}
		public void setNickname(String nickname) {
			this.nickname = nickname;
		}
		public Users toEntity() {
			Users users = new Users();
			users.setUsername(username);
			users.setPassword(password);
			users.setNickname(nickname);
			return users;
		}
	}
	
	public static class Login{
		private String username;
		private String password;
		public Login() {
			super();
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public Users toEntity() {
			Users users = new Users();
			users.setUsername(username);
			users.setPassword(password);
			return users;
		}
	}
}
