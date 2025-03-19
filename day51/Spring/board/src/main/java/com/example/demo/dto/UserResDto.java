package com.example.demo.dto;

import java.time.format.DateTimeFormatter;
import java.util.List;

import com.example.demo.domain.Post;
import com.example.demo.domain.User;
import com.example.demo.dto.UserResDto.Profile.PostList;

public class UserResDto {

	public static record Profile(
			Integer id,
			String nickname,
			List<PostList> postList
			) {
		
		public static Profile from(User user) {
			return new Profile(user.getId(), user.getNickname(), user.getPostList().stream().map(PostList::from).toList());
		}
		
		public static record PostList(
				Integer id,
				String title,
				Integer view,
				String createdAt,
				String updatedAt
				) {
			
			public static PostList from(Post post) {
				return new PostList(post.getId(), post.getTitle(), post.getView(),
						post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
						post.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
			}
		}
	}
}
