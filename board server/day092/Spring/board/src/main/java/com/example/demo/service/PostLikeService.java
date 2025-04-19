package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Post;
import com.example.demo.domain.PostLike;
import com.example.demo.domain.User;
import com.example.demo.error.BoardException;
import com.example.demo.error.ErrorCode;
import com.example.demo.repository.PostLikeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostLikeService {

	private final PostLikeRepository postLikeRepository;
	
	public PostLike createPostLike(Post post, User user) {
		if(this.postLikeRepository.existsByUserIdAndPostId(user.getId(), post.getId())) {
			throw new BoardException(ErrorCode.LIKE_CONFLICT);
		}
		return this.postLikeRepository.save(PostLike.builder()
				.post(post)
				.user(user)
				.build());
	}
	
	public void removePostLike(Integer postId, User user) {
		PostLike saved = this.postLikeRepository.findByUserIdAndPostId(user.getId(), postId).orElseThrow(()->{throw new BoardException(ErrorCode.LIKE_NOT_FOUND);});
		this.postLikeRepository.delete(saved);
	}
}
