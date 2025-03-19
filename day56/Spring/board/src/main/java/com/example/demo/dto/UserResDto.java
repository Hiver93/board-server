package com.example.demo.dto;

import java.time.format.DateTimeFormatter;
import java.util.List;

import com.example.demo.domain.Post;
import com.example.demo.domain.User;

public class UserResDto {

	public static record Profile(
			Integer id,
			String nickname,
			List<PostItem> postList
			) {
		
		public static Profile from(User user) {
			return new Profile(user.getId(), user.getNickname(), user.getPostList().stream().map(PostItem::from).toList());
		}
		
		private static record PostItem(
				Integer id,
				String title,
				Integer view,
				String createdAt,
				String updatedAt,
				Integer comments
				) {
			
			private static PostItem from(Post post) {
				return new PostItem(post.getId(), post.getTitle(), post.getView(), 
						post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
						post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
						post.getCommentList().size());
			}
		}
	}
}
