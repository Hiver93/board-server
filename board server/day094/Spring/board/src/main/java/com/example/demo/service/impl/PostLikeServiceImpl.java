package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Post;
import com.example.demo.domain.PostLike;
import com.example.demo.domain.User;
import com.example.demo.error.BoardException;
import com.example.demo.error.ErrorCode;
import com.example.demo.repository.PostLikeRepository;
import com.example.demo.service.PostLikeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostLikeServiceImpl implements PostLikeService{

	public final PostLikeRepository postLikeRepository;

	@Override
	public PostLike createPostLike(Post post, User user) {
		if(this.postLikeRepository.existsByUserIdAndPostId(user.getId(), post.getId())) {
			throw new BoardException(ErrorCode.LIKE_CONFLICT);
		}
		return this.postLikeRepository.save(PostLike.builder()
				.post(post)
				.user(user)
				.build());
	}

	@Override
	public void removePostLike(Integer postId, User user) {
		PostLike saved = this.postLikeRepository.findByUserIdAndPostId(user.getId(), postId).orElseThrow(()->{throw new BoardException(ErrorCode.LIKE_NOT_FOUND);});
		this.postLikeRepository.delete(saved);
	}

}
