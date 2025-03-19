package com.example.demo.dto;

public class UserResDto {

	public static record Login(
			String token
			) {
		public static Login from(String token) {
			return new Login(token);
		}
	}
}
