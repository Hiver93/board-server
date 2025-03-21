package com.example.demo.dto;

import java.time.format.DateTimeFormatter;
import java.util.List;

import com.example.demo.domain.User;

public class UserResDto {

	public static record Login(
			Integer id,
			String nickname
			) {
		public static Login from(User user) {
			return new Login(user.getId(), user.getNickname());
		}
	}
	
	public static record Status(
			String username,
			String nickname,
			List<Post> postList
			) {
		
		public static Status from(User user) {
			return new Status(user.getUsername(), user.getNickname(),
					user.getPostList().stream()
					.map((p)->
					{
						return new Post(p.getId(),p.getTitle(),p.getContent(),
								p.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
								p.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
					}
					).toList());
		}
		
		
		public static record Post(
				Integer id,
				String title,
				String content,
				String createdAt,
				String updatedAt
				){
			
		}
	}
}
