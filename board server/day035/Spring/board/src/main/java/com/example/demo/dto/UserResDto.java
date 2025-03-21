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
}
