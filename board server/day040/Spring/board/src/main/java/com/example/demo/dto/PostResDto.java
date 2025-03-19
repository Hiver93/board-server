package com.example.demo.dto;

import java.time.format.DateTimeFormatter;

import com.example.demo.domain.Post;

public record PostResDto(
		Integer id,
		String title,
		String content,
		Integer view,
		String nickname,
		String createdAt,
		String updatedAt
		) {
	public static PostResDto from(Post post) {
		return new PostResDto(post.getId(),post.getTitle(),post.getContent(),post.getView(),post.getUser().getNickname(),
				post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
				post.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
	}
}
