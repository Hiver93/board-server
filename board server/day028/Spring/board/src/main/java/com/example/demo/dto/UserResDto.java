package com.example.demo.dto;

import com.example.demo.entity.User;

public class UserResDto {

	public static record Login(
			String uuid
			){
		public static Login fromEntity(String uuid){
			return new Login(uuid);
		}
	}
	
	public static record Status(
			String username,
			String nickname
			) {
		public static Status fromEntity(User user){
			return new Status(user.getUsername(), user.getNickname());
		}
	}
}
