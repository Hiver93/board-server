package com.example.demo.dto;

import org.springframework.validation.annotation.Validated;

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
		public final String getUsername() {
			return username;
		}
		public final void setUsername(String username) {
			this.username = username;
		}
		public final String getPassword() {
			return password;
		}
		public final void setPassword(String password) {
			this.password = password;
		}
		public final String getNickname() {
			return nickname;
		}
		public final void setNickname(String nickname) {
			this.nickname = nickname;
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
		public final String getUsername() {
			return username;
		}
		public final void setUsername(String username) {
			this.username = username;
		}
		public final String getPassword() {
			return password;
		}
		public final void setPassword(String password) {
			this.password = password;
		}
		
	}
	
	@Validated
	public static class Patch{
		private String nickname;

		public Patch() {
			super();
		}

		public final String getNickname() {
			return nickname;
		}

		public final void setNickname(String nickname) {
			this.nickname = nickname;
		}
		
	}
	
	@Validated
	public static class Delete{
		@NotBlank(message = "password is required")
		private String password;

		public Delete() {
			super();
		}

		public final String getPassword() {
			return password;
		}

		public final void setPassword(String password) {
			this.password = password;
		}
		
	}
}
