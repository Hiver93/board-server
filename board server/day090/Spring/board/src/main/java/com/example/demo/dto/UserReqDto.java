package com.example.demo.dto;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class UserReqDto {

	@Getter
	@Setter
	@Validated
	@NoArgsConstructor
	public static class Signup{
		@NotBlank(message = "username is required")
		private String username;
		@NotBlank(message = "password is required")
		private String password;
		@NotBlank(message = "nickname is required")
		private String nickname;
	}
	
	@Getter
	@Setter
	@Validated
	@NoArgsConstructor
	public static class Login{
		@NotBlank(message = "username is required")
		private String username;
		@NotBlank(message = "password is required")
		private String password;
	}
	
	@Getter
	@Setter
	@Validated
	@NoArgsConstructor
	public static class Put{
		@NotBlank(message = "nickname is required")
		private String nickname;
	}
	
	@Getter
	@Setter
	@Validated
	@NoArgsConstructor
	public static class Delete{
		@NotBlank(message = "password is required")
		private String password;
	}
}
