package com.example.demo.dto;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.domain.Comment;
import com.example.demo.domain.Post;
import com.example.demo.domain.User;

public class PostResDto {

	public static record Detail(
			Integer id,
			String title,
			String content,
			Integer view,
			Integer comments,
			Integer likes,
			String imageName,
			Integer userId,
			String nickname,
			String createdAt,
			String updatedAt,
			List<CommentItem> commentList
			) {
		public static Detail from(Post post) {
			User user = post.getUser();
			if(post.isAnonymousPost() || !user.isActivated()) {
				user = User.builder().id(null).nickname("anonymous").build();
			}
			String imageName = post.getImage() == null ? null : post.getImage().getFileName();
			return new Detail(post.getId(), post.getTitle(), post.getContent(), post.getView(), post.getComments(), post.getPostLikes(),
					imageName,
					user.getId(), user.getNickname(),
					post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
					post.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
					post.getCommentList().stream().map(CommentItem::from).toList()
					);
		}
		
		private static record CommentItem(
				Integer id,
				String content,
				Integer userId,
				String nickname,
				String createdAt
				) {
			private static CommentItem from(Comment comment) {
				User user = comment.getUser();
				if(!user.isActivated()) {
					user = User.builder().id(null).nickname("anonymous").build();
				}
				return new CommentItem(comment.getId(), comment.getContent(), user.getId(), user.getNickname(),
						comment.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
			}
		}
	}
	
	
	public static record PostList(
			Long totalSize,
			List<PostItem> postList
			) {
		public static PostList from(Page<Post> page) {
			return new PostList(page.getTotalElements(), page.getContent().stream().map(PostItem::from).toList());
		}
		
		private static record PostItem(
				Integer id,
				String title,
				Integer view,
				Integer comments,
				Integer likes,
				Integer userId,
				String nickname,
				String createdAt,
				String updatedAt
				) {
			private static PostItem from(Post post) {
				User user = post.getUser();
				if(post.isAnonymousPost() || !user.isActivated()) {
					user = User.builder().id(null).nickname("anonymous").build();
				}
				return new PostItem(post.getId(), post.getTitle(), post.getView(), post.getComments(), post.getPostLikes(),
						user.getId(), user.getNickname(),
						post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
						post.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
						);
			}
		}
	}
}
