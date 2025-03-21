package com.example.board.dto;

import java.time.format.DateTimeFormatter;

import com.example.board.entity.Post;

public record PostResponseDto(
		long id,
		String title,
		String content,
		long view,
		String createdAt,
		String updatedAt
		) {
	public static PostResponseDto fromEntity(Post post) {
		return new PostResponseDto(post.getId(),post.getTitle(),post.getContent(),post.getView(),
				post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
				post.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
				);
	}
}
