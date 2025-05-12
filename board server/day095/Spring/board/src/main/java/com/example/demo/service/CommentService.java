package com.example.demo.service;

import com.example.demo.domain.Comment;
import com.example.demo.domain.Post;
import com.example.demo.domain.User;

public interface CommentService {

	public Comment createComment(Post post, String content, User user);
	public void removeComment(Integer commentId, User user);
	public void modifyComment(Integer commentId, String content, User user);
}
