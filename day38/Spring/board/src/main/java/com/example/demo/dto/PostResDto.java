package com.example.demo.dto;

import java.time.format.DateTimeFormatter;

import com.example.demo.domain.Post;

public record PostResDto(
		Integer postId,
		String title,
		String content,
		Integer view,
		Integer userId,
		String createdAt,
		String updatdAt
		) {
	public static PostResDto from(Post post) {
		return new PostResDto(post.getId(),post.getTitle(),post.getContent(),post.getView(),post.getUser().getId(),
				post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
				post.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
	}
}
