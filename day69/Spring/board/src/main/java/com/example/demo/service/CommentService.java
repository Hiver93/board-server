package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Comment;
import com.example.demo.domain.User;
import com.example.demo.error.BoardException;
import com.example.demo.error.ErrorCode;
import com.example.demo.repository.CommentRepository;

@Service
public class CommentService {

	private CommentRepository commentRepository;

	public CommentService(CommentRepository commentRepository) {
		super();
		this.commentRepository = commentRepository;
	}
	
	public void removeComment(Integer commentId, User user) {
		Comment saved = this.commentRepository.findById(commentId).orElseThrow(()->{throw new BoardException(ErrorCode.COMMENT_NOT_FOUND);});
		if(!user.isSameUser(saved.getUser())) {
			throw new BoardException(ErrorCode.NOT_CONTENT_OWNER);
		}
		this.commentRepository.delete(saved);
	}
}
