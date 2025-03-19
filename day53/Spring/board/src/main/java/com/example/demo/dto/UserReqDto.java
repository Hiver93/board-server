package com.example.demo.dto;

import org.springframework.validation.annotation.Validated;

import com.example.demo.domain.User;

import jakarta.validation.constraints.NotBlank;

public class UserReqDto {

	@Validated
	public static class Signup{
		@NotBlank(message = "username is required")
		private String username;
		@NotBlank(message = "password is required")
		private String password;
		@NotBlank(message = "nickname is required")
		private String nickname;
		public Signup() {
			super();
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public void setNickname(String nickname) {
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
	
	@Validated
	public static class Login{
		@NotBlank(message = "username is required")
		private String username;
		@NotBlank(message = "password is required")
		private String password;
		public Login() {
			super();
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getUsername() {
			return username;
		}
		public String getPassword() {
			return password;
		}
		
	}
}
