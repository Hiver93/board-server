package com.example.demo.dto;

import java.util.List;

import com.example.demo.domain.User;

public record UserResDto(
		Integer id,
		String nickname,
		List<PostResDto> postList
		) {
	public static UserResDto from(User user) {
		return new UserResDto(user.getId(), user.getNickname(),
				user.getPostList().stream().map(PostResDto::from).toList());
	}
}
