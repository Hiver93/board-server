package com.example.board.dto;

import java.time.format.DateTimeFormatter;

import com.example.board.entity.Post;

public class PostResponseDto {

	public static record Get(
			long id,
			String title,
			String content,
			String createdAt,
			String updatedAt
			){
		public static Get fromEntity(Post post) {
			return new Get(post.getId(), post.getTitle(), post.getContent(),
					post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
					post.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
					);
		}
	}
}
