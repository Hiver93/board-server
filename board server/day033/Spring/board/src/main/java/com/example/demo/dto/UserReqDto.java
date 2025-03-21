package com.example.demo.dto;

import org.springframework.validation.annotation.Validated;

import com.example.demo.domain.User;

import jakarta.validation.constraints.NotBlank;

public class UserReqDto {

	@Validated
	public static class Signup{
		@NotBlank(message ="username is required")
		private String username;
		@NotBlank(message ="password is required")
		private String password;
		@NotBlank(message ="nickname is required")
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
		public User to() {
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			user.setNickname(nickname);
			return user;
		}
	}
	
	@Validated
	public static class Login{
		@NotBlank(message ="username is required")
		private String username;
		@NotBlank(message ="password is required")
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
		public User to() {
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			return user;
		}
	}
}
