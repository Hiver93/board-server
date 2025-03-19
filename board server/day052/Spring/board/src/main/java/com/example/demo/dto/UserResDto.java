package com.example.demo.dto;

import java.time.format.DateTimeFormatter;
import java.util.List;

import com.example.demo.domain.Post;
import com.example.demo.domain.User;

public class UserResDto {

	public static record Profile(
			Integer id,
			String nickname,
			List<ListItem> postList
			) {
		
		public static Profile from(User user) {
			return new Profile(user.getId(), user.getNickname(), user.getPostList().stream().map(ListItem::from).toList());
		}
		
		public static record ListItem(
				Integer id,
				String title,
				Integer view,
				String createdAt,
				String updatedAt
				) {
			public static ListItem from(Post post) {
				return new ListItem(post.getId(), post.getTitle(), post.getView(), 
						post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
						post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
			}
		}
	}
}
