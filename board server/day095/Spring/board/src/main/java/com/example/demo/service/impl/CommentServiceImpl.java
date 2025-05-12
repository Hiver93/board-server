package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Comment;
import com.example.demo.domain.Post;
import com.example.demo.domain.User;
import com.example.demo.error.BoardException;
import com.example.demo.error.ErrorCode;
import com.example.demo.repository.CommentRepository;
import com.example.demo.service.CommentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{
	
	private final CommentRepository commentRepository;
	
	@Override	
	public Comment createComment(Post post, String content, User user) {
		
		return this.commentRepository.save(Comment.builder()
				.post(post)
				.content(content)
				.user(user)
				.build());
	}

	@Override
	public void removeComment(Integer commentId, User user) {
		Comment saved = this.commentRepository.findById(commentId).orElseThrow(()->{throw new BoardException(ErrorCode.CONTENT_NOT_FOUND);});
		if(!saved.getUser().isSameUser(user)) {
			throw new BoardException(ErrorCode.NOT_CONTENT_OWNER);
		}
		this.commentRepository.delete(saved);
	}

	@Override
	@Transactional
	public void modifyComment(Integer commentId, String content, User user) {
		Comment saved = this.commentRepository.findById(commentId).orElseThrow(()->{throw new BoardException(ErrorCode.CONTENT_NOT_FOUND);});
		if(!saved.getUser().isSameUser(user)) {
			throw new BoardException(ErrorCode.NOT_CONTENT_OWNER);
		}
		saved.updateContent(content);
	}

}
