package com.example.demo.dto;

import com.example.demo.domain.User;

public class UserResDto {

	public static record Login(
			Integer id,
			String nickname
			) {
		public static Login from(User user) {
			return new Login(user.getId(),user.getNickname());
		}
	}
	
	public static record Status(
			Integer id,
			String username,
			String nickname
			) {
		public static Status from(User user) {
			return new Status(user.getId(),user.getUsername(),user.getNickname());
		}
	}
}
