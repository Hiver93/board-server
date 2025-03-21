package com.example.demo.dto;

import java.time.format.DateTimeFormatter;
import java.util.List;

import com.example.demo.domain.Post;

public class PostResDto{
	
	public static record PostList(
			List<ListItem> postList,
			long totalSize
			) {
		public static PostList from(List<Post> postList, long totalSize) {
			return new PostList(postList.stream().map(ListItem::from).toList(),
					totalSize);
		}
		public static record ListItem(
				Integer id,
				String title,
				Integer view,
				String nickname,
				String createdAt,
				String updatedAt
				) {
			
			public static ListItem from(Post post) {
				return new ListItem(post.getId(),post.getTitle(),post.getView(),post.getUser().getNickname(),
						post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
						post.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
			}
		}
	}
	


	public static record Detail(
			Integer id,
			String title,
			String content,
			Integer view,
			String nickname,
			String createdAt,
			String updatedAt
			){
		public static Detail from(Post post) {
			return new Detail(post.getId(),post.getTitle(), post.getContent(), post.getView(),post.getUser().getNickname(),
					post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
					post.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		}
	}
}

