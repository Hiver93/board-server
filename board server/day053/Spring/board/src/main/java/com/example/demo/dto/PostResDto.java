package com.example.demo.dto;

import java.time.format.DateTimeFormatter;
import java.util.List;

import com.example.demo.domain.Post;

public class PostResDto {

	public static record Detail(
			Integer id,
			String title,
			String content,
			Integer view,
			String nickname,
			Integer userId,
			String createdAt,
			String updatedAt,
			List<CommentResDto> commentList
			) {
		
		public static Detail from(Post post) {
			return new Detail(post.getId(), post.getTitle(), post.getContent(), post.getView(), post.getUser().getNickname(), post.getUser().getId(),
					post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
					post.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
					post.getCommentList().stream().map(CommentResDto::from).toList());
		}
	}
	
	public static record PostList(
			long totalSize,
			List<ListItem> postList
			) {
		
		public static PostList from(long totalSize, List<Post> postList) {
			return new PostList(totalSize, postList.stream().map(ListItem::from).toList());
		}
		
		private static record ListItem(
				Integer id,
				String title,
				Integer view,
				String nickname,
				Integer userId,
				String createdAt,
				String updatedAt
				) {
			
			private static ListItem from(Post post) {
				return new ListItem(post.getId(), post.getTitle(), post.getView(), post.getUser().getNickname(), post.getUser().getId(),
						post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
						post.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
			}
		}
	}
}
