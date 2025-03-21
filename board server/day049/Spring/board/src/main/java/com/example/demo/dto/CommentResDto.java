package com.example.demo.dto;

import java.time.format.DateTimeFormatter;

import com.example.demo.domain.Comment;

public record CommentResDto(
		Integer id,
		String content,
		Integer userId,
		String nickname,
		String createdAt
		) {
	public static CommentResDto from(Comment comment) {
		return new CommentResDto(comment.getId(), comment.getContent(), 
				comment.getUser().getId(), comment.getUser().getNickname(),
				comment.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
	}
}
