package com.example.demo.dto;

import com.example.demo.entity.User;

public class UserResDto {
	public static record Login(
			String nickname
			){
		public static Login fromEntity(User user) {
			return new Login(user.getNickname());
		}
	}
	
	public static record Status(
			String username,
			String nickname
			){
		public static Status fromEntity(User user) {
			return new Status(user.getUsername(), user.getNickname());
		}
	}
}
