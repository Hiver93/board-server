package com.example.board.dto;

public record PostResponseDto(
		long id,
		String title,
		String content,
		String createdAt,
		String updatedAt
		) {}
