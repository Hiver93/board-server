package com.example.demo.dto;

import java.time.format.DateTimeFormatter;
import java.util.List;

import com.example.demo.domain.Comment;
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
			List<CommentItem> commentList
			) {
		
		public static Detail from(Post post) {
			return new Detail(post.getId(), post.getTitle(), post.getContent(), post.getView(), post.getUser().getNickname(), post.getUser().getId(),
					post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
					post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
					post.getCommentList().stream().map(CommentItem::from).toList());
		}
		
		private static record CommentItem(
				Integer id,
				String content,
				String nickname,
				Integer userId,
				String createdAt
				) {
			private static CommentItem from(Comment comment) {
				return new CommentItem(comment.getId(), comment.getContent(), comment.getUser().getNickname(), comment.getUser().getId(),
						comment.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
			}
		}
	}
	
	
	public static record PostList(
			long totalSize,
			List<PostItem> postList
			) {
		
		public static PostList from(long totalSize, List<Post> postList) {
			return new PostList(totalSize, postList.stream().map(PostItem::from).toList());
		}
		
		private static record PostItem(
				Integer id,
				String title,
				Integer view,
				String nickname,
				Integer userId,
				String createdAt,
				String updatedAt
				) {
			
			private static PostItem from(Post post) {
				return new PostItem(post.getId(), post.getTitle(), post.getView(), post.getUser().getNickname(), post.getUser().getId(),
						post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
						post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
			}
		}
	}
}
