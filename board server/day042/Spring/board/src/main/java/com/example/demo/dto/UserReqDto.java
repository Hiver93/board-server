package com.example.demo.dto;

import com.example.demo.domain.User;

public class UserReqDto {

	public static class Signup{
		private String username;
		private String password;
		private String nickname;
		public Signup() {
			super();
		}
		void setUsername(String username) {
			this.username = username;
		}
		void setPassword(String password) {
			this.password = password;
		}
		void setNickname(String nickname) {
			this.nickname = nickname;
		}
		public User to() {
			return User.builder()
					.username(username)
					.password(password)
					.nickname(nickname)
					.build();
		}
	}
	
	public static class Login{
		private String username;
		private String password;
		public Login() {
			super();
		}
		void setUsername(String username) {
			this.username = username;
		}
		void setPassword(String password) {
			this.password = password;
		}
		public User to() {
			return User.builder()
					.username(username)
					.password(password)
					.build();
		}
	}
}
