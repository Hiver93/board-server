package com.example.demo.service;

import com.example.demo.domain.Post;
import com.example.demo.domain.PostLike;
import com.example.demo.domain.User;

public interface PostLikeService {

	public PostLike createPostLike(Post post, User user);
	public void removePostLike(Integer postId, User user);
}
