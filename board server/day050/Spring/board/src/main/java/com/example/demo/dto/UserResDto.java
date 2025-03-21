package com.example.demo.dto;

import java.time.format.DateTimeFormatter;
import java.util.List;

import com.example.demo.domain.Post;
import com.example.demo.domain.User;


public class UserResDto {

	public static record Profile(
			Integer id,
			String nickname,
			List<PostListItem> postList
			) {
		
		public static Profile from(User user) {
			return new Profile(user.getId(), user.getNickname(), user.getPostList().stream().map(PostListItem::from).toList());
		}
		
		public static record PostListItem(
				Integer id,
				String title,
				Integer view,
				String createdAt,
				String updatedAt
				){
			
			public static PostListItem from(Post post) {
				return new PostListItem(post.getId(), post.getTitle(), post.getView(), 
						post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
						post.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
			}
		}
	}
}
