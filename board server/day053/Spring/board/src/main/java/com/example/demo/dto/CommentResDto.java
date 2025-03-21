package com.example.demo.dto;

import java.time.format.DateTimeFormatter;

import com.example.demo.domain.Comment;

public record CommentResDto(
		Integer id,
		String content,
		String nickname,
		Integer userId,
		String createdAt
		) {

	public static CommentResDto from(Comment comment) {
		return new CommentResDto(comment.getId(), comment.getContent(), comment.getUser().getNickname(), comment.getUser().getId(),
				comment.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
	}
}
