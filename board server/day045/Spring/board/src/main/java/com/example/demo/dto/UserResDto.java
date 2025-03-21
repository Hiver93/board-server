package com.example.demo.dto;

import java.time.format.DateTimeFormatter;
import java.util.List;

import com.example.demo.domain.Post;
import com.example.demo.domain.User;

public record UserResDto(
		Integer id,
		String nickname,
		List<PostItem> postList
		) {
	public static UserResDto from(User user) {
		return new UserResDto(user.getId(), user.getNickname(), 
				user.getPostList().stream().map(
						(p)->{
							return PostItem.from(p, user);
						}
						).toList());
	}
	
	public static record PostItem(
			Integer id,
			String title,				
			Integer view,
			String nickname,
			String createdAt,
			String updatedAt
			){
		public static PostItem from(Post post, User user) {
			return new PostItem(post.getId(),post.getTitle(),post.getView(), user.getNickname(),
					post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
					post.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		}
	}
}
